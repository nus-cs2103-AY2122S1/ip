package duke.commands;

public class CommandDeadline extends Command {
    public static final String HELP_COMMAND = "deadline";
    public static final String HELP_DESCRIPTION = "Add a new deadline task";
    public static final String HELP_USAGE = "Usage: deadline task_name </by end_time>\n"
            + "Add a new deadline task\n"
            + "\ttask_name\tname of the task to add\n"
            + "\t /by end_time\tdeadline of the deadline task";
}
