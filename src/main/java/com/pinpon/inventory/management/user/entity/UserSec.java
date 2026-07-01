package com.pinpon.inventory.management.user.entity;

import com.pinpon.inventory.management.inventoryMovement.entity.InventoryMovement;
import com.pinpon.inventory.management.role.entity.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserSec {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String password;
    private boolean enabled;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;

    //se usa eager porque queremos todos los roles rapido
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) //eager me va cargar todos los roles
    //jointable define tabla intermedia
    //joincolumns representa la fk hacia user
    //inversejoincolumns representa la fk hacia role
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();


    @OneToMany (mappedBy = "user")
    private List<InventoryMovement> inventoryMovements;
}
