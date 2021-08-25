import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    private static final String invalidCommandText = "I'm sorry, but I don't know what that means :-(";
    // ArrayList of all valid commands and tasks
    private static final String[] strCommands = {"bye", "list", "done", "deadline", "todo", "event", "delete"};
    private static final ArrayList<String> commands = new ArrayList<>(Arrays.asList(strCommands));

    public static Command parse(String fullCommand) throws KermitException {
        String command = "";
        String flag;
        String word;

        StringBuilder argumentBuilder = new StringBuilder();
        StringBuilder flagBuilder = new StringBuilder();

        // Task description and flag should be separated by some /command
        String[] userInput = fullCommand.split("/");
        String commandString = userInput[0];
        String flagString = userInput.length > 1 ? userInput[1] : "";

        String[] commandArr = commandString.split(" ");
        String[] flagArr = flagString.split(" ");

        // first item is command
        command = commandArr[0];
        flag = flagArr[0];

        // Check if command is valid
        if (!commands.contains(command)) {
            throw new KermitException(invalidCommandText);
        }
        String argument = "";
        String dateString = "";
        LocalDate date;

        // Clear contents of string builders
        argumentBuilder.setLength(0);
        flagBuilder.setLength(0);

        // Get description of task and error check
        for (int i = 1; i < commandArr.length; i++) {
            word = commandArr[i];
            if (i != 1) {
                argumentBuilder.append(" ");
            }
            argumentBuilder.append(word);
        }
        argument = argumentBuilder.toString();

        // Get the flags provided for task and error check
        for (int i = 1; i < flagArr.length; i++) {
            word = flagArr[i];
            if (i != 1) {
                flagBuilder.append(" ");
            }
            flagBuilder.append(word);
        }
        flag = flagBuilder.toString();

        switch (command) {
            case "bye":
                return new Exit();
            case "list":
                return new ListTasks();
            case "done":
                return new CompleteTask(argument);
            case "delete":
                return new DeleteTask(argument);
            case "todo":
                return new AddTask("todo", argument, flag);
            case "event":
                return new AddTask("event", argument, flag);
            case "deadline":
                return new AddTask("deadline", argument, flag);
            default:
                throw new KermitException(invalidCommandText);
        }
    }
}