package duke.commands;

import duke.tasks.ToDo;

/**
 * Adds a new to-do task to the task list.
 */
public class ToDoCommand extends Command {
    private final ToDo toDo;

    /**
     * Creates a command to add a new to-do task.
     *
     * @param taskDescription Description of the to-do task to be added.
     */
    public ToDoCommand(String taskDescription) {
        toDo = new ToDo(taskDescription);
    }

    /**
     * Adds the new to-do task to the task list.
     *
     * @return The execution result.
     */
    @Override
    public CommandResult execute() {
        getTaskList().add(toDo);
        return new CommandResult("I've added:\n  " + toDo);
    }
}
