package com.example.springboot.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.dao.WorkoutNoteOldRepository;
import com.example.springboot.entity.WorkoutNoteOld;

@Service
public class WorkoutNoteOldServiceImpl implements WorkoutNoteOldService {

	private WorkoutNoteOldRepository workoutNoteOldRepository;

	@Autowired
	public WorkoutNoteOldServiceImpl(WorkoutNoteOldRepository workoutNoteOldRepository) {
		this.workoutNoteOldRepository = workoutNoteOldRepository;
	}

	@Override
	public List<WorkoutNoteOld> findAll() {
		return workoutNoteOldRepository.findAll();
	}

	@Override
	public WorkoutNoteOld findById(int id) {
		Optional<WorkoutNoteOld> result = workoutNoteOldRepository.findById(id);

		WorkoutNoteOld workoutNoteOld = null;

		if (result.isPresent()) {
			workoutNoteOld = result.get();
		} else {
			throw new RuntimeException("Did not find workout-note with id - " + id);
		}

		return workoutNoteOld;
	}

	@Override
	public void save(WorkoutNoteOld workoutNoteOld) {

		workoutNoteOldRepository.save(workoutNoteOld);

	}

	@Override
	public void deleteById(int id) {
		workoutNoteOldRepository.deleteById(id);
	}

}
