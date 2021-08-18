package tasks;

import exceptions.EmptyTodoBodyException;

public class ToDo extends Task {
    public ToDo(String description) throws EmptyTodoBodyException {
        if (description == null || description.isEmpty()) {
            throw new EmptyTodoBodyException();
        } else {
            super.setDescription(description);
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
