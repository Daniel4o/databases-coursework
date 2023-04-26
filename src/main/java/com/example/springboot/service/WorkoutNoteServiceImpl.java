package com.example.springboot.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.dao.WorkoutNoteOldRepository;
import com.example.springboot.dao.WorkoutNoteRepository;
import com.example.springboot.entity.WorkoutNote;
import com.example.springboot.entity.WorkoutNoteOld;

@Service
public class WorkoutNoteServiceImpl implements WorkoutNoteService {

	private WorkoutNoteRepository workoutNoteRepository;

	private WorkoutNoteOldRepository workoutNoteOldRepository;

	@Autowired
	public WorkoutNoteServiceImpl(WorkoutNoteRepository workoutNoteRepository) {
		this.workoutNoteRepository = workoutNoteRepository;
	}

	@Override
	public List<WorkoutNote> findAll() {
		return workoutNoteRepository.findAll();
	}

	@Override
	public WorkoutNote findById(int id) {
		Optional<WorkoutNote> result = workoutNoteRepository.findById(id);

		WorkoutNote workoutNote = null;

		if (result.isPresent()) {
			workoutNote = result.get();
		} else {
			throw new RuntimeException("Did not find workout-note with id - " + id);
		}

		return workoutNote;
	}

	@Override
	public void save(WorkoutNote workoutNote) {
		List<WorkoutNote> workoutNotes = workoutNoteRepository.findAll();
		List<WorkoutNote> filteredSortedNotes = workoutNotes.stream()
				.filter(note -> note.getObjectId() == workoutNote.getObjectId())
				.sorted(Comparator.comparing(WorkoutNote::getDeactive))
				.collect(Collectors.toList());

		System.out.println("What is: " + filteredSortedNotes);

		if (filteredSortedNotes.size() != 0 && filteredSortedNotes.size() > 5) {
			WorkoutNote oldestNote = filteredSortedNotes.get(0);
			if (workoutNote.getDeactive().compareTo(oldestNote.getDeactive()) >= 0) {
				workoutNoteRepository.save(workoutNote);
				WorkoutNoteOld saveOldestNote = new WorkoutNoteOld(oldestNote.getId(),oldestNote.getObjectId(),oldestNote.getDescription(),oldestNote.getDeactive());
				workoutNoteOldRepository.save(saveOldestNote);
				workoutNoteRepository.delete(oldestNote);

			}
		}
	}

	@Override
	public void deleteById(int id) {
		workoutNoteRepository.deleteById(id);
	}

}
