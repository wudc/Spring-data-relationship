package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
    // Additional query methods can be added here if needed
}
