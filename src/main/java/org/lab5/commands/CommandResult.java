package org.lab5.commands;

public class CommandResult {
    private final Status status;
    private final String message;

    public CommandResult(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public static CommandResult success(String message) {
        return new CommandResult(Status.OK, message);
    }

    public static CommandResult error(String message) {
        return new CommandResult(Status.ERROR, message);
    }

    public static Builder builder() {
        return new Builder();
    }

    public Status getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public enum Status {
        OK, ERROR
    }

    public static class Builder {
        private Status status;
        private String message;

        public static Builder builder() {
            return new Builder();
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public CommandResult build() {
            return new CommandResult(status, message);
        }
    }


}

