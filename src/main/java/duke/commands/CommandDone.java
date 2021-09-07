package duke.commands;

public class CommandDone extends Command {
    public static final String HELP_COMMAND = "done";
    public static final String HELP_DESCRIPTION = "Mark a task as done";
    public static final String HELP_USAGE = "Usage: done task_number\n"
            + "Mark a task as done\n"
            + "\ttask_number\ttask number of the task to mark as done";
}
