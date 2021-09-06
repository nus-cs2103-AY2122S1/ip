package duke.commands;

public class CommandFind extends Command {
    public static String HELP_COMMAND = "find";
    public static String HELP_DESCRIPTION = "Find matching tasks from the list";
    public static String HELP_USAGE = "Usage: find keyphrase\n"
            + "Search for tasks matching the keyphrase\n" +
            "\tkeyphrase\tkeyphrase to search for among tasks";
}
