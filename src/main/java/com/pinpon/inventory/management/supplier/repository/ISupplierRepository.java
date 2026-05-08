package com.pinpon.inventory.management.supplier.repository;

import com.pinpon.inventory.management.supplier.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findByEmail(String email);

}
