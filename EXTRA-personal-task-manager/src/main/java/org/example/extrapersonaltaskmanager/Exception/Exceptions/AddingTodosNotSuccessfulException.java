package org.example.extrapersonaltaskmanager.Exception.Exceptions;

public class AddingTodosNotSuccessfulException extends RuntimeException {

    public AddingTodosNotSuccessfulException(String message) {
        super(message);
    }

    public AddingTodosNotSuccessfulException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddingTodosNotSuccessfulException(Throwable cause) {
        super(cause);
    }
    
}
