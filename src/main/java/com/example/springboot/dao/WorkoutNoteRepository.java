package com.example.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entity.WorkoutNote;


public interface WorkoutNoteRepository extends JpaRepository<WorkoutNote,Integer> {
    List<WorkoutNote> findAll();

}
