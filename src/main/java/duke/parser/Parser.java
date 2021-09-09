package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.DukeCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.SortCommand;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeException;
import duke.exception.NoCommandDescriptionException;
import duke.exception.NoDateTimeException;
import duke.exception.UnknownCommandException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;



/**
 * Encapsulates the processing of user inputs to duke.
 *
 * @author Zhi Bin
 * @version Duke Level 10
 */
public class Parser {
    private final TaskList list;
    private final Storage storage;
    private final Ui ui;

    /**
     * Creates a Parser that handles user input and turn it into respective
     * commands and execute the required functions that user demands.
     *
     * @param list    The TaskList handler that is handling the list of task.
     * @param storage The Storage handler that is in-charged of saving and loading files on local directory.
     * @param ui      The Ui handler that handles printing of output, if required.
     */
    public Parser(TaskList list, Storage storage, Ui ui) {
        this.list = list;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Returns the respective DukeCommand that user want duke to do.
     * If the command is not supported
     *
     * @param userInput User's input on what they want duke to do.
     * @return The respective command object.
     * @throws DukeException If some error occurred in the processing of user input,
     *                       like the user inputted some command that duke does not support.
     */
    public DukeCommand processInput(String userInput) throws DukeException {
        String commandType = getCommandType(userInput).toLowerCase();
        String commandDescription = getCommandDescription(userInput, " ");

        if (checkDescriptionExist(commandDescription, commandType)) {
            switch (commandType) {
            case "list":
                return new ListCommand(ui, storage, list);

            case "bye":
                return new ExitCommand(ui, storage, list);

            case "done":
                return new DoneCommand(ui, storage, list, getTaskNumber(commandDescription));

            case "todo": case "deadline": case "event":
                return new AddCommand(ui, storage, list, processTaskDescriptions(commandType, userInput));

            case "delete":
                return new DeleteCommand(ui, storage, list, getTaskNumber(commandDescription));

            case "find":
                return new FindCommand(ui, storage, list, commandDescription);

            case "sort":
                return new SortCommand(ui, storage, list);

            default:
                throw new UnknownCommandException();
            }
        }
        throw new NoCommandDescriptionException();
    }

    private Task processTaskDescriptions(String taskType, String userInput) throws InvalidDateTimeException,
            NoDateTimeException, NoCommandDescriptionException {
        if (checkDescriptionExist(userInput, taskType)) {
            String commandDescription = getCommandDescription(userInput, " ");
            switch (taskType) {
            case "deadline": case "event":
                return createTaskWithDateTime(commandDescription, taskType);

            default:
                return new ToDo(commandDescription, false);
            }
        }
        throw new NoCommandDescriptionException();
    }

    private String getCommandType(String userInput) {
        return userInput.split(" ")[0];
    }

    private boolean checkDescriptionExist(String commandDescription, String commandType)
            throws NoCommandDescriptionException {
        if (!(commandType.equals("list") || commandType.equals("bye"))) {
            if (commandDescription.isBlank()) {
                throw new NoCommandDescriptionException();
            }
        }
        return true;
    }

    private Task createTaskWithDateTime(String commandDescription, String taskType) throws NoDateTimeException,
            InvalidDateTimeException {
        LocalDateTime dt = getDateTime(commandDescription, taskType);
        String taskDescription = getTaskDescription(commandDescription);
        if (taskType.equals("deadline")) {
            return new Deadline(taskDescription, false, dt);
        } else {
            return new Event(taskDescription, false, dt);
        }

    }

    private String getCommandDescription(String userInput, String substring) {
        int index = userInput.indexOf(substring);
        String description = userInput.substring(index + 1);
        return description;
    }

    private String getTaskDescription(String commandDescription) throws NoDateTimeException {
        int slashIndex = commandDescription.indexOf("/");
        if (slashIndex < 0) {
            throw new NoDateTimeException();
        }
        return commandDescription.substring(0, slashIndex - 1);
    }

    private int getTaskNumber(String commandDescription) {
        return Integer.parseInt(commandDescription.split(" ")[0]);
    }

    private LocalDateTime getDateTime(String taskDescription, String taskType) throws NoDateTimeException,
            InvalidDateTimeException {
        String indicator;
        if (taskType.equals("deadline")) {
            indicator = "/by";
        } else {
            indicator = "/at";
        }

        int index = taskDescription.indexOf(indicator);
        if (index >= 0) {
            try {
                return LocalDateTime.parse(taskDescription.substring(index + 4),
                        DateTimeFormatter.ofPattern("yyyy-M-d H:m"));
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException();
            }
        }

        throw new NoDateTimeException();
    }
}
