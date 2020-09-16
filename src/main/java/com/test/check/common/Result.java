package com.test.check.common;

public class Result {
    private String message;
    private boolean state;

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", state=" + state +
                '}';
    }

    public Result() {
    }

    public Result(String message, boolean state) {
        this.message = message;
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
