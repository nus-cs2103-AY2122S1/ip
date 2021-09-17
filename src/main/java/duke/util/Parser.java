package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UpdateDateTimeCommand;
import duke.command.UpdateNameCommand;
import duke.task.TaskType;

/**
 * Makes sense of user inputs.
 */
public class Parser {
    /**
     * Transform user input into Command that can be executed by duke.
     *
     * @param userInput command sentence.
     * @return A Command that matches the user input.
     * @throws DukeException user input does not match any known format.
     */
    public static Command parseInputs(String userInput) throws DukeException {
        int indexOfSpace = userInput.indexOf(' ');
        String firstWord = indexOfSpace == -1
                ? userInput
                : userInput.substring(0, indexOfSpace);

        switch (firstWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "find":
            return getFindCommand(userInput, indexOfSpace);
        case "done":
            return getDoneCommand(userInput);
        case "delete":
            return getDeleteCommand(userInput);
        case "deadline":
            return getAddDeadlineCommand(userInput, indexOfSpace);
        case "event":
            return getAddEventCommand(userInput, indexOfSpace);
        case "todo":
            return getAddTodoCommand(userInput, indexOfSpace);
        case "update":
            return getUpdateCommand(userInput, indexOfSpace);
        default:
            throw new DukeException("Meeseeks are not born fumbling in this world for meaning Jerry! \n"
                    + "Say something I can understand!! >:(");
        }
    }

    private static Command getUpdateCommand(String userInput, int indexOfSpace) throws DukeException {
        if (userInput.matches("^update name [0-9]+ .+")) {
            String[] inputDetails = userInput.split(" ");
            int taskNumber = Integer.parseInt(inputDetails[2]);
            StringBuilder newName = new StringBuilder();
            for (int i = 3; i < inputDetails.length; i++) {
                newName.append(inputDetails[i]).append(" ");
            }
            return new UpdateNameCommand(taskNumber, newName.toString());
        } else if (userInput.matches("^update datetime [0-9]+ .+ .+")) {
            String[] inputDetails = userInput.split(" ");
            int taskNumber = Integer.parseInt(inputDetails[2]);
            String dateTimeAsString = inputDetails[3] + " " + inputDetails[4];
            return new UpdateDateTimeCommand(taskNumber, parseDate(dateTimeAsString));
        } else {
            throw new DukeException(">:( follow format below:\n"
                    + "update <name/datetime> <task number 1 - 100> <text/dd-mm-yyyy HHmm>");
        }
    }

    private static FindCommand getFindCommand(String userInput, int indexOfSpace) throws DukeException {
        if (userInput.matches("^find .+")) {
            String searchTerm = userInput.substring(indexOfSpace + 1);
            return new FindCommand(searchTerm);
        } else {
            throw new DukeException(">:( include search term after find:\n"
                    + "find <some search term>");
        }
    }

    private static AddCommand getAddTodoCommand(String userInput, int indexOfSpace) throws DukeException {
        if (userInput.matches("^todo .+")) {
            String name = userInput.substring(indexOfSpace + 1);
            return new AddCommand(TaskType.TODO, name, null);
        } else {
            throw new DukeException(">:( include task name after todo:\n"
                    + "todo <some task name>");
        }
    }

    private static Command getAddEventCommand(String userInput, int indexOfSpace) throws DukeException {
        if (userInput.matches("^event .+ /at .+")) {
            String[] deadlineDetails = userInput.substring(indexOfSpace + 1).split(" /at ");
            String name = deadlineDetails[0];
            String dateTime = deadlineDetails[1];
            return new AddCommand(TaskType.EVENT, name, parseDate(dateTime));
        } else {
            throw new DukeException(">:( Follow format below:\n"
                    + "deadline <taskname...> /at <dd-mm-yyyy HHmm>");
        }
    }

    private static Command getAddDeadlineCommand(String userInput, int indexOfSpace) throws DukeException {
        if (userInput.matches("^deadline .+ /by .+")) {
            String[] deadlineDetails = userInput.substring(indexOfSpace + 1).split(" /by ");
            String name = deadlineDetails[0];
            String dateTime = deadlineDetails[1];
            return new AddCommand(TaskType.DEADLINE, name, parseDate(dateTime));
        } else {
            throw new DukeException(">:( Follow format below:\n"
                    + "deadline <taskname...> /by <dd-mm-yyyy HHmm>");
        }
    }

    private static DeleteCommand getDeleteCommand(String userInput) throws DukeException {
        if (userInput.matches("^delete [0-9]+")) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            return new DeleteCommand(taskNumber);
        } else {
            throw new DukeException(">:( follow format below:\n"
                    + "done <number between 1 and 100>");
        }
    }

    private static DoneCommand getDoneCommand(String userInput) throws DukeException {
        if (userInput.matches("^done [0-9]+")) {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
            return new DoneCommand(taskNumber);
        } else {
            throw new DukeException(">:( follow format below:\n"
                    + "done <number between 1 and 100>");
        }
    }

    private static LocalDateTime parseDate(String string) throws DukeException {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            return LocalDateTime.parse(string, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new DukeException(">:( follow datetime format of dd-mm-yyyy HHmm");
        }

    }
}
