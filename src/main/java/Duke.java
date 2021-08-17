import java.util.Scanner;
import commands.*;
import exceptions.NoSuchCommandException;

public class Duke {
    //private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String GREETING_MESSAGE = 
    "Hello! I'm Duke" + "\n" + "What can I do for you?";

    private static final CommandConverter ComConvert = new CommandConverter();
    private static final TasksHandler TasksHandler = new TasksHandler();

    static String dukeStart() {
        return GREETING_MESSAGE;
    }

    static void runDukeBot(Scanner sc) {
        while (sc.hasNextLine()) {
            String instructions = sc.nextLine();
            try {
                Command command_given = ComConvert.convertInputToCommand(instructions);
                TasksHandler.storeTasks(command_given);
                if (TasksHandler.handleTasks(command_given)) {
                    break;
                }
            } catch (NoSuchCommandException e) {
                System.err.println(e);
            }
        }
    }
}
