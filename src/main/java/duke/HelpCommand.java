package duke;

import java.util.ArrayList;
import java.util.Arrays;

public class HelpCommand {

    private static String generalHelp = "You require my assistance! \n Dukebot is a app that helps "
            + "you create many basic tasks and complete them. Here are some basic commands that "
            + "are supported as of this current version"
            + "\n \n"
            + "1. Todo creation\n"
            + "2. Deadline creation\n"
            + "3. Event creation\n"
            + "4. Bye command"
            + "5. Done command"
            + "6. Find command"
            + "7. Delete command"
            + "8. List command"
            + "\n \n"
            + "For in depth details for each command enter help followed by the number above or help "
            + "and lowercase action"
            + "\n e.g help todo or help 1";

    private static String todo = "Todo is a place to create quick tasks. This is the simplest form of task."
            + "\n The format for todo is: todo *insert name of task here."
            + "\n e.g todo first task creates a todo task that appears as [T][X] first task";

    private static String deadline = "Deadlines are tasks with a specific date to be completed. "
            + "They created with the"
            + "deadline keyword. The date specified should come after a /by indicator and the "
            + "format should be DD/MM/YYYY HH:MM"
            + "\n \n The syntax for the command is: deadline *insert task name* /by DD/MM/YYYY HH:MM"
            + "\n \n e.g deadline /by 23/01/2030 23:00";

    private static String event = "Events are also tasks with a specific date to be completed. They are created with "
            + "the event keyword. The date specified should come after a /at idnicator and the "
            + "format should be DD/MM/YYYY HH:MM"
            + "\n \n The syntax for the command is: event *insert task name* /by DD/MM/YYYY HH:MM"
            + "\n \n e.g event some event /at 23/01/2030 23:00";

    private static String bye = "Bye command quits stops the bot and quits the GUI. Simply type bye without "
            + "anything else";

    private static String done = "The done command completes a certain task. Task completed can be seen by entering"
            + "the command list but will be removed from history, as such will be automatically cleared the next time"
            + "duke bot runs."
            + "\n \n The syntax for the command is: done **insert task number here**"
            + "e.g done 1";

    private static String find = "Find allows for searching of tasks with the specified keyword. "
            + "This is essentially a filter. "
            + "\n \n The syntax of the command is: find **insert keyword here";

    private static String delete = "Delete command allows for deleting of files. Simple enter delete followed by the"
            + "file number";

    private static String list = "List command allows for seeing the current log of the file. Simply type list";


    private static String[] helpCommands = {
        generalHelp,
        todo,
        deadline,
        event,
        bye,
        done,
        find,
        delete,
        list
    };

    private static ArrayList<String> helpCommandString = new ArrayList<>(
            Arrays.asList(
                    "generalHelp",
                    "todo",
                    "deadline",
                    "event",
                    "bye",
                    "done",
                    "find",
                    "list"
            )
    );


    public static String getCommand(int number) {
        return helpCommands[number];
    }

    public static String getCommand(String command) {
        return getCommand(helpCommandString.indexOf(command));
    }
}
