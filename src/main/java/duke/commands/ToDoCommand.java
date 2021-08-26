package duke.commands;

import duke.tasks.ToDo;

public class ToDoCommand extends Command {
    private final ToDo toDo;

    public ToDoCommand(String taskDescription) {
        toDo = new ToDo(taskDescription);
    }

    @Override
    public CommandResult execute() {
        taskList.add(toDo);
        return new CommandResult("I've added:\n  " + toDo);
    }
}
