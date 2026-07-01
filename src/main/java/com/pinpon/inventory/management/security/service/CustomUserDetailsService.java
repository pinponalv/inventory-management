package com.pinpon.inventory.management.security.service;

import com.pinpon.inventory.management.user.dto.AuthLoginRequestDTO;
import com.pinpon.inventory.management.user.dto.AuthResponseDTO;
import com.pinpon.inventory.management.user.entity.UserSec;
import com.pinpon.inventory.management.user.repository.IUserRepository;
import com.pinpon.inventory.management.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //tenemos que traer nuestro usuario que esta en formato user
        //y necesitamos retornar un userdetails entonces toca hacer una conversion


        //UserSec user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        UserSec user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found"));


        //creamos la lista para los permisos
        //del tipo simplegrantedauthority porque es la clase que usa spring security para manejar los permisos
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

        //traemos roles y convertimos a simplegrantedauthority
        user.getRoles().forEach(role -> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(role.getRole()))));

        /**
         * traer permisos y convertirlos en SimpleGrantedAuthority
         * a la lista de roles lo transformo en un stream para usar flatmap que permite hacer un mapeo de lista de permisos
         * que tienen el rol y al mismo tiempo las transformo a un stream para usar el forEach
         * que por cada permiso que encuentre lo agregue al authorityList haciendo una conversion a
         * SimpleGrantedAuthority apartir del nombre del permiso que trae
         * **/
        user.getRoles().stream()
                .flatMap(role -> role.getPermissionList().stream())
                .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getPermissionName())));


        //retornar usuario en el formato de spring security
        return new User(
                user.getName(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),
                user.isCredentialsNonExpired(),
                user.isAccountNonLocked(),
                authorityList //le pasamos el authorityList que traen los roles y permisos
        );
    }

    //method login
    public AuthResponseDTO loginUser(AuthLoginRequestDTO authLoginRequestDTO) {
        //obtener datos
        String email = authLoginRequestDTO.email();
        String password = authLoginRequestDTO.password();

        Authentication authentication = this.authenticate(email, password);

        //guardar data en el security context holder
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String accessToken = jwtUtils.createToken(authentication);
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(email, "Login successful", accessToken, true);
        return authResponseDTO;
    }

    //method authenticate
    private Authentication authenticate(String email, String password) {
        //get user
        //TODO: EDITAR EL NOMBRE DEL METODO PARA BUSCAR POR EMAIL
        UserDetails userDetails = this.loadUserByUsername(email);

        if(userDetails == null) {
            throw new BadCredentialsException("Invalid email or password");
        }else if(!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }
        return new UsernamePasswordAuthenticationToken(email, userDetails.getPassword(), userDetails.getAuthorities());
    }
}
