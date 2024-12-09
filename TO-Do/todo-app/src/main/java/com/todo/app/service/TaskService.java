package com.todo.app.service;

import com.todo.app.model.Task;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


import org.springframework.stereotype.Service;

@Service
public class TaskService {
	private final List<Task> tasks = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger(1);

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task addTask(String title) {
        Task newTask = new Task(counter.getAndIncrement(), title, false);
        tasks.add(newTask);
        return newTask;
    }

    public Task updateTask(int id, boolean completed) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setCompleted(completed);
                return task;
            }
        }
        return null;
    }

    public boolean deleteTask(int id) {
        return tasks.removeIf(task -> task.getId() == id);
    }
}
