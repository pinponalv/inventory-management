package com.pinpon.inventory.management.role.repository;

import com.pinpon.inventory.management.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Long> {
}
