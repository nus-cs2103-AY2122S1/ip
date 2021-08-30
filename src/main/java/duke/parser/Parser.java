package duke.parser;

import duke.command.*;
import duke.exception.*;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parser takes in all the user input and
 * parses the information before executing the
 * respective commands.
 *
 * @author Cheong Yee Ming
 * @version Duke A-JavaDoc
 */
public class Parser {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructor for a Parser.
     *
     * @param taskList Handles all operations regarding tasks.
     * @param storage  Save and load data from local directory.
     * @param ui       Prints message with respect to user input.
     */
    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Returns a Command based on user input.
     * Throws a DukeException if the user input is invalid.
     *
     * @param userInput User's input on what they want duke to do.
     * @return The respective Command object.
     * @throws DukeException If user input is invalid.
     */
    public Command parseUserInput(String userInput) throws DukeException {
        String[] splitUserInput = userInput.split(" ");
        String commandType = splitUserInput[0];
        String commandDetails = userInput.substring(userInput.indexOf(" ") + 1);
        if (!(commandType.equals("list") || commandType.equals("bye")) &&
                (commandDetails.isBlank() || userInput.indexOf(" ") == -1)) {
            throw new NoTaskDescriptionException(ui);
        }

        switch (commandType) {
        case "list":
            return new ListCommand(taskList, storage, ui);

        case "bye":
            return new ExitCommand(taskList, storage, ui);

        case "deadline":
            return new AddCommand(taskList, storage, ui, parseTaskInput(TaskType.DEADLINE, userInput));

        case "event":
            return new AddCommand(taskList, storage, ui, parseTaskInput(TaskType.EVENT, userInput));

        case "todo":
            return new AddCommand(taskList, storage, ui, parseTaskInput(TaskType.TODO, userInput));

        case "done":
            return new DoneCommand(taskList, storage, ui, Integer.parseInt(splitUserInput[1]));

        case "delete":
            return new DeleteCommand(taskList, storage, ui, Integer.parseInt(splitUserInput[1]));

        default:
            throw new NoSuchCommandException(ui);
        }
    }

    /**
     *
     * @param taskType  The type of task.
     * @param userInput User's input on what they want duke to do.
     * @return The respective Task.
     * @throws DukeException If description of task is invalid.
     */
    public Task parseTaskInput(TaskType taskType, String userInput) throws DukeException {
        String[] splitUserInput = userInput.split(" ");
        String commandDetails = userInput.substring(userInput.indexOf(" ") + 1);
        if (commandDetails.isBlank() || userInput.indexOf(" ") == -1) {
            throw new NoTaskDescriptionException(ui);
        }

        switch (taskType) {
        case DEADLINE:
            try {
                int byIndex = commandDetails.indexOf("/by") - 1 ;
                if (byIndex <= 0) {
                    throw new NoDateTimeException(ui);
                }
                String deadlineDetails = commandDetails.substring(0, byIndex);
                if (deadlineDetails.isBlank()) {
                    throw new NoTaskDescriptionException(ui);
                }
                try {
                    LocalDateTime by = LocalDateTime.parse(commandDetails.substring(deadlineDetails.indexOf("by") + 3),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    return new Deadline(deadlineDetails, false, by);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException(ui);
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new NoTaskDescriptionException(ui);
            }


        case EVENT:
            try {
                int atIndex = commandDetails.indexOf("/at") - 1 ;
                if (atIndex <= 0) {
                    throw new NoDateTimeException(ui);
                }
                String eventDetails = commandDetails.substring(0, atIndex);
                if (eventDetails.isBlank()) {
                    throw new NoTaskDescriptionException(ui);
                }
                try {
                    LocalDateTime at = LocalDateTime.parse(commandDetails.substring(eventDetails.indexOf("at") + 3),
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    return new Event(eventDetails, false, at);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateTimeException(ui);
                }
            } catch (StringIndexOutOfBoundsException e) {
                throw new NoTaskDescriptionException(ui);
            }

        default:
            return new ToDo(commandDetails, false);
        }
    }
 }
