package duke.parser;

import duke.command.*;
import duke.constant.EditType;
import duke.constant.TaskType;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The Parser class encapsulates parsing operations that involve reading in data and
 * transforming it into a useful format to be utilised by other classes.
 */
public class Parser {
    /**
     * Parses a list of strings representing the contents of a save file and
     * transforms it into a list of task objects.
     *
     * @param saveFileContents A list of strings, each representing a task.
     * @return A list of task objects.
     * @throws DukeException If strings passed to the parser are not in the expected format.
     */
    public static List<Task> parseSaveFile(List<String> saveFileContents) throws DukeException {
        return saveFileContents.stream().map(task -> {
            try {
                String[] taskInfo = task.split("\\|");
                String taskType = taskInfo[0];
                boolean isDone = taskInfo[1].equals("1");
                String taskDescription = taskInfo[2];
                switch (taskType) {
                case "T":
                    return new ToDo(taskDescription, isDone);
                case "D":
                    return new Deadline(taskDescription, isDone, taskInfo[3]);
                case "E":
                    return new Event(taskDescription, isDone, taskInfo[3]);
                default:
                    throw new DukeException("Your save file is corrupted and has an invalid format.");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Your save file is corrupted and has an invalid format.");
            }
        }).collect(Collectors.toList());
    }

    /**
     * Parses a string representing the user's input and creates a
     * command object with relevant data extracted from the user input.
     *
     * @param userInput The user's input.
     * @return A command object with relevant data.
     * @throws DukeException If the string passed to the parser does not contain information in the
     * correct format for a command object to be created.
     */
    public static Command parseUserInput(String userInput) throws DukeException {
        switch (userInput) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        default:
            break;
        }

        String[] commandAndArgument = userInput.split(" ", 2);
        String userCommand = commandAndArgument[0];
        if (userCommand.equals("todo") || userCommand.equals("deadline") || userCommand.equals("event")) {
            if (commandAndArgument.length < 2) {
                throw new DukeException("The description of a task cannot be empty.\n"
                        + "Please input your task in the following manner:\n"
                        + "todo|deadline|event <task_description>");
            }
            if (commandAndArgument[1].indexOf('|') >= 0) {
                throw new DukeException("The description of a task cannot contain this character: |");
            }
            String[] descriptionAndDate;
            switch (userCommand) {
            case "todo":
                if (commandAndArgument[1].strip().equals("")) {
                    throw new DukeException("Task description cannot be empty for a todo task.\n"
                            + "Please input your todo task in the following manner:\n"
                            + "todo <task_description>");
                }
                return new AddCommand(TaskType.TODO, commandAndArgument[1], "");
            case "deadline":
                descriptionAndDate = commandAndArgument[1].split(" /by ", 2);
                if (descriptionAndDate.length < 2 || descriptionAndDate[0].strip().equals("")) {
                    throw new DukeException("Invalid format for a deadline task.\n"
                            + "Please input your deadline task in the following manner:\n"
                            + "deadline <task_description> /by <task_deadline>");
                }
                return new AddCommand(TaskType.DEADLINE, descriptionAndDate[0], descriptionAndDate[1]);
            default:
                descriptionAndDate = commandAndArgument[1].split(" /at ", 2);
                if (descriptionAndDate.length < 2 || descriptionAndDate[0].strip().equals("")) {
                    throw new DukeException("Invalid format for an event.\n"
                            + "Please input your event in the following manner:\n"
                            + "event <event_description> /at <event_date>");
                }
                return new AddCommand(TaskType.EVENT, descriptionAndDate[0], descriptionAndDate[1]);
            }
        } else if (userCommand.equals("done") || userCommand.equals("delete")) {
            try {
                int taskIndex = Integer.parseInt(commandAndArgument[1]) - 1;
                switch (userCommand) {
                case "done":
                    return new EditCommand(EditType.DONE, taskIndex);
                default:
                    return new EditCommand(EditType.DELETE, taskIndex);
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Please enter a valid task number.");
            }
        } else if (userCommand.equals("find")) {
            if (commandAndArgument.length < 2 || commandAndArgument[1].strip().equals("")) {
                throw new DukeException("Please enter a keyword to search for in the following manner:\n"
                        + "find <keyword>");
            }
            return new FindCommand(commandAndArgument[1]);
        } else {
            throw new DukeException("Invalid command. List of valid commands include:\n"
                    + "list|todo|deadline|event|done|delete|find|bye");
        }
    }
}
