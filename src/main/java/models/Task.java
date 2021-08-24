package models;

import java.io.Serializable;

public class Task implements Serializable {

    protected String description;
    protected boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    public void setDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }
}