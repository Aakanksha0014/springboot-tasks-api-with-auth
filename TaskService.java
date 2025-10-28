package com.example.tasksapi.service;

import com.example.tasksapi.dto.TaskRequest;
import com.example.tasksapi.exception.ResourceNotFoundException;
import com.example.tasksapi.model.Task;
import com.example.tasksapi.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(TaskRequest req) {
        Task t = new Task();
        t.setTitle(req.getTitle());
        t.setDescription(req.getDescription());
        if (req.getStatus() != null) t.setStatus(req.getStatus());
        return taskRepository.save(t);
    }

    public List<Task> getAllTasks(String statusFilter) {
        if (statusFilter == null) return taskRepository.findAll();
        try {
            Task.Status s = Task.Status.valueOf(statusFilter.toUpperCase());
            return taskRepository.findAllByStatus(s);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid status filter: " + statusFilter);
        }
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));
    }

    public Task updateTask(Long id, TaskRequest req) {
        Task existing = getTaskById(id);
        if (req.getTitle() != null && !req.getTitle().isBlank()) existing.setTitle(req.getTitle());
        existing.setDescription(req.getDescription());
        if (req.getStatus() != null) existing.setStatus(req.getStatus());
        return taskRepository.save(existing);
    }

    public void deleteTask(Long id) {
        Task existing = getTaskById(id);
        taskRepository.delete(existing);
    }
}
