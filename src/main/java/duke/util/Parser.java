package duke.util;

import duke.task.TaskType;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
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
            case "done":
                if (userInput.matches("^done [0-9]+")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    return new DoneCommand(taskNumber);
                } else {
                    throw new DukeException(">:( follow format below:\n"
                            + "done <number between 1 and 100>");
                }
            case "delete":
                if (userInput.matches("^delete [0-9]+")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    return new DeleteCommand(taskNumber);
                } else {
                    throw new DukeException(">:( follow format below:\n"
                            + "done <number between 1 and 100>");
                }
            case "deadline":
                if (userInput.matches("^deadline .+ /by .+")) {
                    String[] deadlineDetails = userInput.substring(indexOfSpace + 1).split(" /by ");
                    String name = deadlineDetails[0];
                    String dateTime = deadlineDetails[1];
                    return new AddCommand(TaskType.DEADLINE, name, parseDate(dateTime));
                } else {
                    throw new DukeException(">:( Follow format below:\n"
                            + "deadline <taskname...> /by <dd-mm-yyyy HHmm>");
                }
            case "event":
                if (userInput.matches("^event .+ /at .+")) {
                    String[] deadlineDetails = userInput.substring(indexOfSpace + 1).split(" /at ");
                    String name = deadlineDetails[0];
                    String dateTime = deadlineDetails[1];
                    return new AddCommand(TaskType.EVENT, name, parseDate(dateTime));
                } else {
                    throw new DukeException(">:( Follow format below:\n"
                            + "deadline <taskname...> /at <dd-mm-yyyy HHmm>");
                }
            case "todo":
                if (userInput.matches("^todo .+")) {
                    String name = userInput.substring(indexOfSpace + 1);
                    return new AddCommand(TaskType.TODO, name, null);
                } else {
                    throw new DukeException(">:( include task name after todo:\n"
                            + "todo <some task name>");
                }
            default:
                throw new DukeException("Say something I can understand!! >:(");
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
