package duke.commands;

public class CommandEvent extends Command {
    public static final String HELP_COMMAND = "event";
    public static final String HELP_DESCRIPTION = "Add a new event task";
    public static final String HELP_USAGE = "Usage: event task_name </at event_time>\n"
            + "Add a new event task\n"
            + "\ttask_name\tname of the task to add\n"
            + "\t /at event_time\ttime that event occurs at";
}
