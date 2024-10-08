package com.collector.my_collector_inventory.repositories;

import com.collector.my_collector_inventory.entities.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInformation, Long> {

    UserInformation findUserByUsername(String name);
}
