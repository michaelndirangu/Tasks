package com.mike.Tasks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mike.Tasks.model.Category;

public interface CategoryRepo extends JpaRepository<Category,Long>{}