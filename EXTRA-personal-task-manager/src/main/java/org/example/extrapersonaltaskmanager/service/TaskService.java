package org.example.extrapersonaltaskmanager.service;

import org.example.extrapersonaltaskmanager.entity.TaskEntity;
import org.example.extrapersonaltaskmanager.repository.TaskRepositoryInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepositoryInterface taskRepository;

    public TaskService(TaskRepositoryInterface taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional
    public TaskEntity addTask(TaskEntity taskEntity) {
        return taskRepository.save(taskEntity);
    }

    @Transactional
    public void deleteTask(Integer id) {
        Optional<TaskEntity> taskToDelete = taskRepository.findById(id);
        if (taskToDelete.isPresent()) {
            taskRepository.delete(taskToDelete.get());
        }
    }

    public Iterable<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    public Iterable<TaskEntity> addMultipleTasks(List<TaskEntity> taskEntities) {
        return taskRepository.saveAll(taskEntities);
    }

    public TaskEntity getTaskById(Integer id) {
        return taskRepository.findById(id).orElse(null);
    }

}
