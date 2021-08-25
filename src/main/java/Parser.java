import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    private static final String invalidCommandText = "I'm sorry, but I don't know what that means :-(";
    // ArrayList of all valid commands and tasks
    private static final String[] strCommands = {"bye", "list", "done", "deadline", "todo", "event", "delete"};
    private static final ArrayList<String> commands = new ArrayList<>(Arrays.asList(strCommands));
    private static final String[] strTasks = {"deadline", "todo", "event"};
    private static final ArrayList<String> validTasks = new ArrayList<>(Arrays.asList(strTasks));

    // parses dates in form dd-mm-yyyy to localdate
    private static LocalDate parseDate(String dateString) throws KermitException{
        String[] components = dateString.split("-");
        try {
            String day = components[0];
            String month = components[1];
            String year = components[2];
            LocalDate parsedDate = LocalDate.parse(String.join("-", year, month, day));
            return parsedDate;
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new KermitException("That is an invalid date!");
        }
    }

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
        if (argument.equals("") && validTasks.contains(command)) {
            throw new KermitException("The argument of the " + command + " command cannot be empty");
        }

        // Get the flags provided for task and error check
        for (int i = 1; i < flagArr.length; i++) {
            word = flagArr[i];
            if (i != 1) {
                flagBuilder.append(" ");
            }
            flagBuilder.append(word);
        }
        dateString = flagBuilder.toString();
        // flag arguments for these tasks should not be empty
        if (dateString.equals("")) {
            switch (command) {
                case "event":
                    throw new KermitException("Events should be formatted as:\nevent <description> /at <time of event>");
                case "deadline":
                    throw new KermitException("Deadlines should be formatted as:\ndeadline <description> /by <deadline>");

            }
        }

        switch (command) {
            case "bye":
                return new Exit();
            case "list":
                return new ListTasks();
            case "done":
                return new CompleteTask(Integer.parseInt(argument));
            case "delete":
                return new DeleteTask(Integer.parseInt(argument));
            case "todo":
                return new AddToDo(argument);
            case "event":
                date = parseDate(dateString);
                return new AddDateDependentTask("event", argument, date);
            case "deadline":
                date = parseDate(dateString);
                return new AddDateDependentTask("deadline", argument, date);
            default:
                throw new KermitException(invalidCommandText);
        }
    }
}