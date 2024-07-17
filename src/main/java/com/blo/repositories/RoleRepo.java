package com.blo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blo.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer>{

}
