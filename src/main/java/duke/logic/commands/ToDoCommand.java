package duke.logic.commands;

import duke.logic.tasks.ToDo;

/**
 * Adds a new to-do task to the task list.
 */
public class ToDoCommand extends Command {
    private static final String TODO_TASK_ADDED_MSG = "I've added:\n  ";
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
        return new CommandResult(TODO_TASK_ADDED_MSG + toDo);
    }
}
