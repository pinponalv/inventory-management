package com.pinpon.inventory.management.user.repository;

import com.pinpon.inventory.management.user.entity.UserSec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserSec, Long> {
    UserSec findByName(String name);
    UserSec findByEmail(String email);
    Optional<UserSec> findUserByEmail(String email);
    //Optional<UserSec> findByUsername(String name);
}
