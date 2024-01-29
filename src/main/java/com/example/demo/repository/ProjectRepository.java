package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    // Additional query methods can be added here if needed
}
