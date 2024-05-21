package com.wallet.Rest.storageExceptions;

public class Storage {
    String message;
    String debugMessage;

    public Storage() {
    }

    public Storage(String message) {
        this.message = message;
    }

    public Storage(String message, String debugMessage) {
        this.message = message;
        this.debugMessage = debugMessage;
    }
    public String getDebugMessage() {
        return debugMessage;
    }

    public void setDebugMessage(String debugMessage) {
        this.debugMessage = debugMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
