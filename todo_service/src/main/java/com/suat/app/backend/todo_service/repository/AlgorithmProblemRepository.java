package com.suat.app.backend.todo_service.repository;

import com.suat.app.backend.todo_service.entity.AlgorithmProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlgorithmProblemRepository extends JpaRepository<AlgorithmProblem, String> {
}