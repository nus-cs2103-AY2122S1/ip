package duke.util;

public class Help {

    private static String information = "";
    private static String format = "";
    private static String example = "";
    private static String extraHelp = "";
    private final static String mainHelp = "Type in \"help\" to go back to the main help page.\n\n";
    private final static String defaultHelp = "For full documentation of my abilities, type the following url into your browser: \n\n" +
            "https://necrowolf28.github.io/ip/\n\nand then press enter.";

    public static String format(Help type) {
        return information + format + example + extraHelp + defaultHelp;
    }

    public static Help generalHelp() {
        information = "You can search for help for any of the following commands: \n" +
                "- list\n- todo\n- deadline\n- event\n- done\n- delete\n- find\n- exit\n\n";
        format = "Just type \"help\" followed by a space and any of the above keywords, then hit enter.";
        example = "Example: \"help deadline\"\n\n";
        return null;
    }

    public static Help listHelp() {
        information = "The List command lists out all your current tasks.\n\n";
        format = "Simply type \"list\" and hit enter.\n\n";
        extraHelp = "Take note that there should be items in your list before using this command.";
        return null;
    }

    public static Help toDoHelp() {
        information = "A ToDo creates a new task without any deadline or event timing.\n\n";
        format = "To create a new ToDo, simply type \"todo\" followed by a space, then the description of your task.\n\n";
        example = "Example: \"todo homework\"\n\n";
        extraHelp = "For help on how to manage tasks, type \"help done\", \"help delete\" or \"help find\".\n\n" + mainHelp;
        return null;
    }

    public static Help deadlineHelp() {
        information = "A Deadline creates a new task with a date and time that it has to be completed by.\n\n";
        format = "To create a new Deadline, type \"deadline\" followed by a space, then the description of your task," +
                "followed by the deadline it has to be completed by.\n\n";
        example = "Example: \"deadline assignment 1 /by 24/09/2021 2359\"\n\n";
        extraHelp = "For help on how to manage tasks, type \"help done\", \"help delete\" or \"help find\".\n\n" + mainHelp;
        return null;
    }

    public static Help eventHelp() {
        information = "A Event creates a new task with a date and time that it is happening at.\n\n";
        format = "To create a new Event, type \"event\" followed by a space, then the description of your task," +
                "followed by the day and time that the event is happening at. The format is not as strict as a Deadline.\n\n";
        example = "Example: \"event project meeting /at Monday 20th Sept 2-4pm\"\n\n";
        extraHelp = "For help on how to manage tasks, type \"help done\", \"help delete\" or \"help find\".\n\n" + mainHelp;
        return null;
    }

    public static Help doneHelp() {
        information = "The Done command marks a task as \"Done\", indicated by an \"[X]\" in its description.\n\n";
        format = "To mark a task as \"done\", type \"done\" followed by a space, then the task number of the completed task.\n\n";
        example = "Example: \"done 4\"\n\n";
        extraHelp = "Take note that the number must be a valid number, i.e. the task exists.\n\n";
        return null;
    }

    public static Help deleteHelp() {
        information = "The Delete command removes a task from the list \"Done\".\n\n";
        format = "To delete a task, type \"delete\" followed by a space, then the task number of the task you want to remove.\n\n";
        example = "Example: \"delete 6\"\n\n";
        extraHelp = "Take note that the number must be a valid number, i.e. the task exists.\n\n";
        return null;
    }

    public static Help findHelp() {
        information = "The Find command finds all tasks which contain a specified keyword.\n\n";
        format = "To find tasks, type \"find\" followed by a space, then the keyword you want to search for.\n\n";
        example = "Example: \"find homework\"\n\n";
        extraHelp = "Take note that the keyword is used in at least one task.\n\n";
        return null;
    }

}
