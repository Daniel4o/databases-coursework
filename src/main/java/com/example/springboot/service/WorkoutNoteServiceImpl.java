package com.example.springboot.service;

import java.util.Comparator;
import java.util.Date;
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
	public WorkoutNoteServiceImpl(WorkoutNoteRepository workoutNoteRepository,
			WorkoutNoteOldRepository workoutNoteOldRepository) {
		this.workoutNoteRepository = workoutNoteRepository;
		this.workoutNoteOldRepository = workoutNoteOldRepository;
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

		if (workoutNote.getDeactive() != null) {
			List<WorkoutNote> workoutNotes = workoutNoteRepository.findAll();
			// Filter all the notes which ObjectId is the same 
			// and sort by date (from most recent to oldest)
			List<WorkoutNote> filteredSortedNotes = workoutNotes.stream()
					.filter(note -> note.getObjectId() == workoutNote.getObjectId())
					.sorted(Comparator.comparing(WorkoutNote::getDeactive, Comparator.nullsLast(Date::compareTo)))
					.collect(Collectors.toList());

			// Check if Updated Record was with NULL Date
			if (filteredSortedNotes.stream()
					.anyMatch(note -> workoutNote.getId() == note.getId() && note.getDeactive() == null)) {
				WorkoutNote nullDateWorkoutNote = new WorkoutNote(workoutNote.getObjectId(),
						workoutNote.getDescription(), null);
				workoutNoteRepository.save(nullDateWorkoutNote);
			}
			// Check if there is already stored value with same ObjectId
			// and it's less than 6 values
			if (filteredSortedNotes.size() != 0 && filteredSortedNotes.size() > 5) {
				WorkoutNote oldestNote = filteredSortedNotes.get(0);

				// If Updated record is Later THAN the most earlier record
				//
				if (workoutNote.getDeactive().compareTo(oldestNote.getDeactive()) >= 0) {
					workoutNoteRepository.save(workoutNote);
					WorkoutNoteOld saveOldestNote = new WorkoutNoteOld(oldestNote.getId(), oldestNote.getObjectId(),
							oldestNote.getDescription(), oldestNote.getDeactive());
					workoutNoteOldRepository.save(saveOldestNote);
					workoutNoteRepository.delete(oldestNote);
					return;

					// If updated's value date is older than the oldest
					// in the filteredSortedNotes, store it in other table
				} else {
					WorkoutNoteOld saveOldestNote = new WorkoutNoteOld(workoutNote.getId(), workoutNote.getObjectId(),
							workoutNote.getDescription(), workoutNote.getDeactive());
					workoutNoteOldRepository.save(saveOldestNote);
					workoutNoteRepository.delete(workoutNote);
					return;
				}
			}

			// Create new value or update current one
			workoutNoteRepository.save(workoutNote);
		}

		// If new note is created
		else {
			workoutNoteRepository.save(workoutNote);
		}
	}

	@Override
	public void deleteById(int id) {
		workoutNoteRepository.deleteById(id);
	}

}
