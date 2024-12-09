package com.todo.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.todo.app.model.Task;
import com.todo.app.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	
	@Autowired
	 private final TaskService taskService;

	    public TaskController(TaskService taskService) {
	        this.taskService = taskService;
	    }

	    @GetMapping
	    public List<Task> getAllTasks() {
	        return taskService.getAllTasks();
	    }

	    @PostMapping
	    public Task addTask(@RequestParam String title) {
	        return taskService.addTask(title);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestParam boolean completed) {
	        Task updatedTask = taskService.updateTask(id, completed);
	        return updatedTask != null ? ResponseEntity.ok(updatedTask) : ResponseEntity.notFound().build();
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
	        return taskService.deleteTask(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	    }
}


