package com.pinpon.inventory.management.supplier.entity;

import com.pinpon.inventory.management.inventoryMovement.entity.InventoryMovement;
import com.pinpon.inventory.management.purchaseOrder.entity.PurchaseOrder;
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
@Table(name = "suppliers")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String phone;

    @OneToMany (mappedBy = "supplier")
    private List<PurchaseOrder> purchaseOrders;

    @OneToMany (mappedBy = "supplier")
    private List<InventoryMovement> inventoryMovements;
}
