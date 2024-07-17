package com.blo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blo.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
