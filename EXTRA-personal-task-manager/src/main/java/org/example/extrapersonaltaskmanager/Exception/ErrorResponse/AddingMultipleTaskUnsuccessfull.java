package org.example.extrapersonaltaskmanager.Exception.ErrorResponse;

public class AddingMultipleTaskUnsuccessfull {
    private String message;
    private long timestamp;
    private int status;

    public AddingMultipleTaskUnsuccessfull(String message, long timestamp, int status) {
        this.message = message;
        this.timestamp = timestamp;
        this.status = status;
    }

    public AddingMultipleTaskUnsuccessfull() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    
}
