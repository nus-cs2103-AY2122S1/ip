package commands;

import tasks.Todo;

public class AddTodoCommand extends AddTaskCommand {

    public AddTodoCommand(String desc, boolean isDone) {
        super(new Todo(desc, isDone));
    }

}
