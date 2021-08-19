package tasks;

import exceptions.EmptyTaskException;

public class ToDo extends Task {
    private ToDo(String description) {
        super(description);
    }

    public static ToDo addToDo(String input) throws EmptyTaskException {
        if (input.equals("")) {
            throw new EmptyTaskException("todo");
        }

        ToDo item = new ToDo(input);

        return item;
    }

    @Override
    public String toString() {
        String res = "[T] [" + this.getStatus() + "] " + this.description;

        return res;
    }
}
