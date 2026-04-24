package com.pinpon.inventory.management.warehouse.entity;

import com.pinpon.inventory.management.inventory.entity.Inventory;
import com.pinpon.inventory.management.inventoryMovement.entity.InventoryMovement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String address;

    @OneToMany (mappedBy = "warehouse")
    private List<Inventory> inventory;

    @OneToMany (mappedBy = "warehouse")
    private List<InventoryMovement> inventoryMovement;

    
}
