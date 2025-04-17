package org.example.extrapersonaltaskmanager.Exception;

import org.example.extrapersonaltaskmanager.Exception.ErrorResponse.AddingMultipleTaskUnsuccessfull;
import org.example.extrapersonaltaskmanager.Exception.ErrorResponse.TaskErrorResponse;
import org.example.extrapersonaltaskmanager.Exception.Exceptions.AddingTodosNotSuccessfulException;
import org.example.extrapersonaltaskmanager.Exception.Exceptions.TodoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TaskControllerAdvice {
        @ExceptionHandler
    public ResponseEntity<TaskErrorResponse> handleTaskErrorException(TodoNotFoundException exception) {
        TaskErrorResponse error = new TaskErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<AddingMultipleTaskUnsuccessfull> handleAddingTasksException(AddingTodosNotSuccessfulException exception) {
        AddingMultipleTaskUnsuccessfull error = new AddingMultipleTaskUnsuccessfull();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
