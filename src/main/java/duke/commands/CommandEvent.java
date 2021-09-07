package duke.commands;

public class CommandEvent extends Command {
    public static String HELP_COMMAND = "event";
    public static String HELP_DESCRIPTION = "Add a new event task";
    public static String HELP_USAGE = "Usage: event task_name </at event_time>\n"
            + "Add a new event task\n"
            + "\ttask_name\tname of the task to add\n"
            + "\t /at event_time\ttime that event occurs at";
}
