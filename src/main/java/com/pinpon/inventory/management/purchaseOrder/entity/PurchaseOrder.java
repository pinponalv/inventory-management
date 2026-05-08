package com.pinpon.inventory.management.purchaseOrder.entity;

import com.pinpon.inventory.management.product.entity.Product;
import com.pinpon.inventory.management.supplier.entity.Supplier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "buys")
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product products;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private BigDecimal purchasePrice;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDate purchaseDate;
}
