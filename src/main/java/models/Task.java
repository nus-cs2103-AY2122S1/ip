package models;

import java.io.Serializable;

public class Task implements Serializable {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Task) {
            Task temp = (Task) obj;
            return temp.toString() == this.toString();
        }

        return false;
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        }
        return "[ ] " + this.description;
    }
}