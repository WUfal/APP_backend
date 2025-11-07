package com.suat.app.backend.todo_service.repository;

import com.suat.app.backend.todo_service.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {
    // JpaRepository 提供的 findById() 和 findAll() 已经够用了
}