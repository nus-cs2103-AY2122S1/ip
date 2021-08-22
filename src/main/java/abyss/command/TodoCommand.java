package abyss.command;

import abyss.exception.InvalidTodoException;

public class TodoCommand implements Command {
    private String description;

    protected TodoCommand(String description) throws InvalidTodoException {
        if (description.equals("")) {
            throw new InvalidTodoException();
        }
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
