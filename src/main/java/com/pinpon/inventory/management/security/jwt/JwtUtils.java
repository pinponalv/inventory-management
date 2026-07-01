package com.pinpon.inventory.management.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    @Value("${security.jwt.private.key}")
    private String privateKey;
    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    //create token
    public String createToken(Authentication authentication) {
        Algorithm algorithm = Algorithm.HMAC256(privateKey);

        //username queda en el security context holder
        //obtengo el usuario que se quiere registrar
        String email = authentication.getPrincipal().toString();


        //obtengo los permisos
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        //genero el token
        String jwtToken = JWT.create()
                .withIssuer(this.userGenerator)//usuario que genera el token
                .withSubject(email)//usuario al que se le genera el token
                .withClaim("authorities", authorities)//datos traidos del JWT
                .withIssuedAt(new Date()) //fecha de creacion
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000)) //vence en 30 minutos
                .withJWTId(UUID.randomUUID().toString())//genero id random y lo paso a string
                .withNotBefore(new Date()) //valido para usar ahora
                .sign(algorithm); //nuestra firma es la que creamos con la clave secreta
        return jwtToken;
    }

    //decodificar y validar nuestros tokens
    public DecodedJWT verifyToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(privateKey);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();

            //si esta ok, no genera ningun error nos devuelve el jwt decodificado
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT;
        }catch (JWTVerificationException e){
            throw new JWTVerificationException("Invalid token. Not authorized");
        }
    }

    //metodo para obtener el username del token
    public String extractEmail(DecodedJWT decodedJWT) {
        //subject es el usuario que establecimos para generar el token
        return decodedJWT.getSubject().toString();
    }

    //metodo para obtener los claims
    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claim) {
        return decodedJWT.getClaim(claim);
    }

    //metodo para obtener un claim en particular
    public Map<String, Claim> returnAllClaims(DecodedJWT decodedJWT) {
        return decodedJWT.getClaims();
    }
}
