package com.example.springboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springboot.entity.WorkoutNoteOld;


public interface WorkoutNoteOldRepository extends JpaRepository<WorkoutNoteOld,Integer> {
    List<WorkoutNoteOld> findAll();

}
