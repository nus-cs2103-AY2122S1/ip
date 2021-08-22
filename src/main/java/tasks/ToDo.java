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

    public ToDo(String description, boolean isDone) {
        super.setDescription(description);
        super.setIsDone(isDone);
    }

    @Override
    public String getTaskRepresentation() {
        return TaskType.TODO + "," + super.getTaskRepresentation();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
