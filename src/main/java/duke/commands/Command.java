package duke.commands;

/**
 * Abstract base class for commands
 */
abstract class Command {
    public static String HELP_DESCRIPTION;
    public static String HELP_USAGE;


    public String getHelpDescription(){
        return HELP_DESCRIPTION;
    }

    public String getHelpUsage(){
        return HELP_USAGE;
    }
}
