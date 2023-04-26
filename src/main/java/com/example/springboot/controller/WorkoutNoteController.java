package com.example.springboot.controller;

import com.example.springboot.entity.WorkoutNote;
import com.example.springboot.entity.WorkoutNoteOld;
import com.example.springboot.service.WorkoutNoteOldService;
import com.example.springboot.service.WorkoutNoteService;

import jakarta.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/workout-notes")
public class WorkoutNoteController {

	private WorkoutNoteService workoutNoteService;
	private WorkoutNoteOldService  workoutNoteOldService;

	public WorkoutNoteController(WorkoutNoteService workoutNoteService) {
		this.workoutNoteService = workoutNoteService;
	}

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	

	@GetMapping("/list")
	public String listWorkoutNotes(Model model) {

		List<WorkoutNote> workoutNotes = workoutNoteService.findAll();
		List<WorkoutNoteOld> workoutNotesOld = workoutNoteOldService.findAll();

		model.addAttribute("workoutNotes", workoutNotes);
		model.addAttribute("workoutNotesOld", workoutNotesOld);

		return "workout-notes/list-workout-notes";
	}

	@GetMapping("/add")
	public String addWorkoutNote(Model model) {
		WorkoutNote workoutNote = new WorkoutNote();

		model.addAttribute("workoutNote", workoutNote);

		return "workout-notes/workout-note-form";
	}

	@PostMapping("/save")
	public String saveWorkoutNote(@Valid @ModelAttribute("workoutNote") WorkoutNote workoutNote,
								BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "workout-notes/workout-note-form";
		} else {
			workoutNoteService.save(workoutNote);

			return "workout-notes/workout-note-confirmation";
		}
	}

	@GetMapping("/update")
	public String updateWorkoutNote(@RequestParam("id") int id, Model model) {
		WorkoutNote workoutNote = workoutNoteService.findById(id);

		model.addAttribute("workoutNote", workoutNote);

		return "workout-notes/workout-note-form";
	}

	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id) {

		workoutNoteService.deleteById(id);

		return "redirect:/workout-notes/list";
	}
}
