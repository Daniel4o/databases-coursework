package com.example.springboot.service;

import java.util.List;

import com.example.springboot.entity.WorkoutNote;

public interface WorkoutNoteService {

	List<WorkoutNote> findAll();
	
	WorkoutNote findById(int id);
	
	void save(WorkoutNote workoutNotes);
	
	void deleteById(int id);
	
}
