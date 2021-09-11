package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UndoCommand;
import duke.command.UndoneCommand;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeException;
import duke.exception.NoDateTimeException;
import duke.exception.NoPreviousCommandException;
import duke.exception.NoSuchCommandException;
import duke.exception.NoSuchTaskException;
import duke.exception.NoTaskDescriptionException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskType;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Parser takes in all the user input and
 * parses the information before executing the
 * respective commands.
 *
 * @author Cheong Yee Ming
 * @version Duke Level-10
 */
public class Parser {
    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final ArrayList<String> previousCommand;

    /**
     * Constructor for a Parser.
     *
     * @param taskList Handles all operations regarding tasks.
     * @param storage  Save and load data from local directory.
     * @param ui       Prints message with respect to user input.
     */
    public Parser(TaskList taskList, Storage storage, Ui ui) {
        assert taskList != null : "There should be a task list.";
        assert storage != null : "There should be a storage.";
        assert ui != null : "There should be a ui.";
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
        this.previousCommand = new ArrayList<>();
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
        assert !userInput.isBlank() : "There is no user input to be parsed.";
        String commandType = getCommandType(userInput);
        String commandDetails = getCommandDetails(userInput);
        if (!(commandType.equals("list") || commandType.equals("bye") || commandType.equals("undo"))
                && (commandDetails.isBlank() || !userInput.contains(" "))) {
            throw new NoTaskDescriptionException();
        }
        storePreviousCommand(userInput);

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
            return new DoneCommand(taskList, storage, ui, getTaskNumber(commandDetails));

        case "delete":
            return new DeleteCommand(taskList, storage, ui, getTaskNumber(commandDetails));

        case "find":
            return new FindCommand(taskList, storage, ui, commandDetails);

        case "undo":
            return new UndoCommand(taskList, storage, ui, parseUndoInput(getPreviousCommand()));

        default:
            throw new NoSuchCommandException();
        }
    }

    /**
     * Method to determine what type of task is to be added.
     *
     * @param taskType  The type of task.
     * @param userInput User's input on what they want duke to do.
     * @return The respective Task.
     * @throws DukeException If description of task is invalid.
     */
    public Task parseTaskInput(TaskType taskType, String userInput) throws DukeException {
        assert !userInput.isBlank() : "There is no user input to be parsed.";
        String commandDetails = getCommandDetails(userInput);
        if (commandDetails.isBlank() || !userInput.contains(" ")) {
            throw new NoTaskDescriptionException();
        }

        switch (taskType) {
        case DEADLINE:
            try {
                return createDeadline(commandDetails);
            } catch (StringIndexOutOfBoundsException e) {
                throw new NoTaskDescriptionException();
            }

        case EVENT:
            try {
                return createEvent(commandDetails);
            } catch (StringIndexOutOfBoundsException e) {
                throw new NoTaskDescriptionException();
            }

        default:
            return new ToDo(commandDetails, false);
        }
    }

    /**
     * Method to create Deadline
     *
     * @param commandDetails Deadline of this task.
     * @return Deadline task if command details are valid.
     * @throws DukeException if command details are invalid.
     */
    public Deadline createDeadline(String commandDetails) throws DukeException {
        try {
            int byIndex = commandDetails.indexOf("/by") - 1;
            if (byIndex <= 0) {
                throw new NoDateTimeException();
            }
            String deadlineDetails = commandDetails.substring(0, byIndex);
            if (deadlineDetails.isBlank()) {
                throw new NoTaskDescriptionException();
            }
            try {
                LocalDateTime by = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("by") + 3),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                return new Deadline(deadlineDetails, false, by);
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoTaskDescriptionException();
        }
    }

    /**
     * Method to create Event
     *
     * @param commandDetails Event task.
     * @return Event task if command details are valid.
     * @throws DukeException if command details are invalid.
     */
    public Event createEvent(String commandDetails) throws DukeException {
        try {
            int atIndex = commandDetails.indexOf("/at") - 1;
            if (atIndex <= 0) {
                throw new NoDateTimeException();
            }
            String eventDetails = commandDetails.substring(0, atIndex);
            if (eventDetails.isBlank()) {
                throw new NoTaskDescriptionException();
            }
            try {
                LocalDateTime at = LocalDateTime.parse(commandDetails.substring(commandDetails.indexOf("at") + 3),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                return new Event(eventDetails, false, at);
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException();
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new NoTaskDescriptionException();
        }
    }

    /**
     * Method to determine the command for undo.
     *
     * @param previousUserInput User's input on what they want duke to do.
     * @return The respective Command object.
     */
    public Command parseUndoInput(String previousUserInput) {
        assert !previousUserInput.isBlank() : "There is no user input to be parsed.";
        System.out.println(previousUserInput);
        String commandType = getCommandType(previousUserInput);
        String commandDetails = getCommandDetails(previousUserInput);

        switch (commandType) {
        case "deadline": case "event": case "todo":
            return new DeleteCommand(taskList, storage, ui, taskList.size());

        case "done":
            return new UndoneCommand(taskList, storage, ui, getTaskNumber(commandDetails));

        case "delete":
            Task task = TaskList.stringToTask(getDeletedTask(commandDetails));
            return new AddCommand(taskList, storage, ui , task);
        default:
            return null;
        }
    }

    /**
     * Method to return the deleted task in string form.
     *
     * @param commandDetails The task number to execute command
     *                       on and task in string representation.
     * @return String representation of task previously deleted.
     */
    public String getDeletedTask(String commandDetails) {
        int index = commandDetails.indexOf(" ");
        return commandDetails.substring(index + 1);
    }

    /**
     * Method to retrieve the type of command.
     *
     * @param userInput The user's input command.
     * @return String representation of command type.
     */
    private String getCommandType(String userInput) {
        return userInput.split(" ")[0];
    }

    /**
     * Method to retrieve the details of command.
     *
     * @param userInput The user's input command.
     * @return String representation of command details.
     */
    private String getCommandDetails(String userInput) {
        int index = userInput.indexOf(" ");
        return userInput.substring(index + 1);
    }

    /**
     * Method to retrieve task number from user input.
     *
     * @param commandDetails The task number to execute command on.
     * @return Task number of task.
     */
    private int getTaskNumber(String commandDetails) {
        return Integer.parseInt(commandDetails);
    }

    /**
     * Undo the previous user command.
     *
     * @return Previously executed user command that has yet to be undone.
     * @throws NoPreviousCommandException if there are no more commands to undo.
     */
    private String getPreviousCommand() throws NoPreviousCommandException {
        try {
            return previousCommand.remove(previousCommand.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new NoPreviousCommandException();
        }
    }

    /**
     * Stores only commands that can be undone.
     *
     * @param userInput The user's input command.
     * @throws NoSuchTaskException when task does not exist.
     */
    private void storePreviousCommand(String userInput) throws NoSuchTaskException {
        assert !userInput.isBlank() : "There is no user input to be stored.";
        String commandType = getCommandType(userInput);
        String commandDetails = getCommandDetails(userInput);

        switch (commandType) {
        case "deadline": case "done": case "event": case "todo":
            previousCommand.add(userInput);
            break;
        case "delete":
            try {
                previousCommand.add(userInput + " " + taskList.getTask(getTaskNumber(commandDetails)).toString());
            } catch (NoSuchTaskException e) {
                throw new NoSuchTaskException();
            }
            break;
        default:
        }
    }
}
