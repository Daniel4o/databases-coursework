package com.example.springboot.service;

import java.util.List;

import com.example.springboot.entity.WorkoutNoteOld;

public interface WorkoutNoteOldService {

	List<WorkoutNoteOld> findAll();
	
	WorkoutNoteOld findById(int id);
	
	void save(WorkoutNoteOld workoutNotesOld);
	
	void deleteById(int id);
	
}
