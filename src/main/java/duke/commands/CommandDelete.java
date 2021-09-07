package duke.commands;

public class CommandDelete extends Command {
    public static final String HELP_COMMAND = "delete";
    public static final String HELP_DESCRIPTION = "Delete a task from the list";
    public static final String HELP_USAGE = "Usage: delete task_number\n"
            + "Delete a task\n"
            + "\ttask_number\ttask number of the task to delete";
}
