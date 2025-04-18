package org.example.extrapersonaltaskmanager.controller;

import org.example.extrapersonaltaskmanager.Exception.Exceptions.AddingTodosNotSuccessfulException;
import org.example.extrapersonaltaskmanager.Exception.Exceptions.TodoNotFoundException;
import org.example.extrapersonaltaskmanager.entity.TaskEntity;
import org.example.extrapersonaltaskmanager.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }



    @PostMapping("/addtodo")
    public ResponseEntity<TaskEntity> addTask(@RequestBody TaskEntity task) {
        TaskEntity savedTask = taskService.addTask(task);
        return ResponseEntity.status(201).body(savedTask);
    }

    @GetMapping("/gettodos")
    public ResponseEntity<Iterable<TaskEntity>> getTasks() {
        Iterable<TaskEntity> taskEntities = taskService.getAllTasks();
        return ResponseEntity.status(200).body(taskEntities);

    }

    @PostMapping("/addmultipletodos")
    public ResponseEntity<Iterable<TaskEntity>> addTasks(@RequestBody List<TaskEntity> tasks) {

        for (TaskEntity task : tasks) {
            if (task.getTaskName() == null || task.getTaskName().isEmpty()) {
                throw new AddingTodosNotSuccessfulException("Task name for the task with id *" + task.getId() + "* cannot be empty or null");
            }
            if (task.getTaskDescription() == null || task.getTaskDescription().isEmpty()) {
                throw new AddingTodosNotSuccessfulException("Task description for the task with id *" + task.getId() + "* cannot be empty or null");
            }
        }

        Iterable<TaskEntity> savedTasks = taskService.addMultipleTasks(tasks);
        return ResponseEntity.status(201).body(savedTasks);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<TaskEntity> getTask(@PathVariable int id) {
        TaskEntity task = taskService.getTaskById(id);
        if (task == null) {
            throw new TodoNotFoundException("Todo id not found - " + id);
        }

        return ResponseEntity.status(201).body(task);
    }
}