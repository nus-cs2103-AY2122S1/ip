package models;

import models.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj instanceof Todo) {
            Task temp = (Todo) obj;
            return temp.toString() == this.toString();
        }

        return false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[T][X] " + this.description;
        }
        return "[T][ ] " + this.description;
    }
}
