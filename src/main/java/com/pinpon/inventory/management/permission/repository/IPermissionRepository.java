package com.pinpon.inventory.management.permission.repository;

import com.pinpon.inventory.management.permission.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPermissionRepository extends JpaRepository<Permission, Long> {
}
