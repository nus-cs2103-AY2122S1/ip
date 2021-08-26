package duke;

import duke.commands.*;
import duke.exceptions.*;

import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Deals with making sense of the user command to Duck Chatbot.
 */
public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        String commandWord;
        String taskDescription = null;
        Command command = null;
        if (fullCommand.contains(" ")) {
            String[] commandAndDescription = fullCommand.split(" ", 2);
            commandWord = commandAndDescription[0];
            taskDescription = commandAndDescription[1];
        } else {
            commandWord = fullCommand;
        }
        try {
            switch (commandWord) {
                case "bye":
                    command = new ExitCommand();
                    break;
                case "list":
                    command = new ListCommand();
                    break;
                case "done":
                    if (taskDescription == null) {
                        throw new TaskOutOfRangeException();
                    }
                    int taskNo = Integer.parseInt(taskDescription);
                    command = new MarkDoneCommand(taskNo);
                    break;
                case "delete":
                    if (taskDescription == null) {
                        throw new TaskOutOfRangeException();
                    }
                    taskNo = Integer.parseInt(taskDescription);
                    command = new DeleteCommand(taskNo);
                    break;
                case "todo":
                    if (taskDescription.isEmpty()) {
                        throw new EmptyDescriptionException();
                    }
                    command = new AddCommand(Duke.TaskType.TODO, new String[]{taskDescription});
                    break;
                case "deadline":
                    if (taskDescription.isEmpty()) {
                        throw new EmptyDescriptionException();
                    }
                    String[] descriptionDate;
                    if (taskDescription.contains("/")) {
                        descriptionDate = taskDescription.split(" /by ");
                    } else {
                        throw new MissingDateException();
                    }
                    command = new AddCommand(Duke.TaskType.DEADLINE, descriptionDate);
                    break;
                case "event":
                    if (taskDescription.isEmpty()) {
                        throw new EmptyDescriptionException();
                    }
                    if (taskDescription.contains("/")) {
                        descriptionDate = taskDescription.split(" /at ");
                    } else {
                        throw new MissingDateException();
                    }
                    command = new AddCommand(Duke.TaskType.EVENT, descriptionDate);
                    break;
                default:
                    throw new InvalidInputException();
            }
        } catch (DateTimeParseException e) {
            System.out.println("OOPS! Please input date in this format: yyyy-mm-dd");
        }
        return command;
    }

}
