package duke.parser;

import java.time.format.DateTimeParseException;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.task.TaskList;

/**
 * Handles the user commands to Duke and executes commands accordingly.
 */
public class Parser {

    private static final String MISSING_TASK_NUMBER_MESSAGE =
            "Please enter the task number of your target task.";
    private static final String MISSING_TASK_DESCRIPTION_MESSAGE =
            "The description of the task cannot be empty.";
    private static final String ENTERED_NON_NUMBER_MESSAGE =
            "Please input a task number instead.";
    private static final String INVALID_DEADLINE_FORMAT =
            "You've entered the deadline command in a invalid format!"
                    + "\nIt should be in the form: deadline /by yyyy-mm-dd hrs:mins";
    private static final String INVALID_EVENT_FORMAT =
            "You've entered the event command in a invalid format!"
                    + "\nIt should be in the form: event /at yyyy-mm-dd hrs:mins";
    private static final String INVALID_COMMAND_MESSAGE =
            "You have entered an invalid command, please try again!";

    /** The task list to execute the commands to. */
    private TaskList tasks;

    /**
     * Constructs a Parser object that will handles user commands and execute
     * them accordingly.
     *
     * @param tasks The TaskList that the commands will affect.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses the description of the user's command when he wants to add a
     * new todo to the task list.
     *
     * @param desc The description of the user's command
     * @return A TodoCommand object that will be used to execute the command.
     * @throws DukeException If the user enters an empty description, a DukeException
     * will be thrown.
     */
    public ToDoCommand toDoParse(String desc) throws DukeException {
        if (desc.equals("")) {
            throw new DukeException(MISSING_TASK_DESCRIPTION_MESSAGE);
        }
        return new ToDoCommand(desc);
    }

    /**
     * Parses the description of the user's command when he wants to add a
     * new deadline to the task list.
     *
     * @param desc The description of the user's command
     * @return A DeadlineCommand object that will be used to execute the command.
     * @throws DukeException If the user enters an empty description or enters the
     * description in an invalid format, a DukeException will be thrown.
     */
    public DeadlineCommand deadlineParse(String desc) throws DukeException {
        if (desc.equals("")) {
            throw new DukeException(MISSING_TASK_DESCRIPTION_MESSAGE);
        }
        try {
            String[] descSplit = desc.split("(\\s+)/by(\\s+)");
            String dateAndTime = descSplit[1];
            String[] dateAndTimeSplit = dateAndTime.split("\\s+");
            String date = dateAndTimeSplit[0];
            String time = dateAndTimeSplit[1];
            return new DeadlineCommand(descSplit[0], date, time);
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException(INVALID_DEADLINE_FORMAT);
        }
    }

    /**
     * Parses the description of the user's command when he wants to add a
     * new event to the task list.
     *
     * @param desc The description of the user's command
     * @return An EventCommand object that will be used to execute the command.
     * @throws DukeException If the user enters an empty description or enters the
     * description in an invalid format, a DukeException will be thrown.
     */
    public EventCommand eventParse(String desc) throws DukeException {
        if (desc.equals("")) {
            throw new DukeException(MISSING_TASK_DESCRIPTION_MESSAGE);
        }
        try {
            String[] descSplit = desc.split("(\\s+)/at(\\s+)");
            String dateAndTime = descSplit[1];
            String[] dateAndTimeSplit = dateAndTime.split("\\s+");
            String date = dateAndTimeSplit[0];
            String time = dateAndTimeSplit[1];
            return new EventCommand(descSplit[0], date, time);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(INVALID_EVENT_FORMAT);
        }

    }

    /**
     * Parses the description of the user's command when he wants to delete a task.
     *
     * @param desc The description of the user's command
     * @return A DeleteCommand object that will be used to execute the command.
     * @throws DukeException If the user enters an empty description or a non-number
     * as the description, a DukeException will be thrown.
     */
    public DeleteCommand deleteParse(String desc) throws DukeException {
        if (desc.equals("")) {
            throw new DukeException(MISSING_TASK_NUMBER_MESSAGE);
        }
        try {
            int taskNumber = Integer.parseInt(desc);
            return new DeleteCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(ENTERED_NON_NUMBER_MESSAGE);
        }
    }

    /**
     * Parses the description of the user's command when he wants to mark a task as done.
     *
     * @param desc The description of the user's command
     * @return A DoneCommand object that will be used to execute the command.
     * @throws DukeException If the user enters an empty description or a non-number
     * as the description, a DukeException will be thrown.
     */
    public DoneCommand doneParse(String desc) throws DukeException {
        if (desc.equals("")) {
            throw new DukeException(MISSING_TASK_NUMBER_MESSAGE);
        }
        try {
            int taskNumber = Integer.parseInt(desc);
            return new DoneCommand(taskNumber);
        } catch (NumberFormatException e) {
            throw new DukeException(ENTERED_NON_NUMBER_MESSAGE);
        }
    }

    /**
     * Reads the user commands and executes the commands accordingly.
     *
     * @param command The user command to be read.
     * @return A String containing the message to be shown to the user.
     * @throws DukeException If the user enters an invalid input command, a DukeException
     * will be thrown.
     */
    public Command parse(String command) throws DukeException {
        String[] commandSplit = command.split("\\s+", 2);
        String commandWord = commandSplit[0].toLowerCase();
        String desc = "";
        if (commandSplit.length == 2) {
            desc = commandSplit[1];
        }

        switch (commandWord) {
        case DoneCommand.COMMAND_WORD:
            return doneParse(desc);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ToDoCommand.COMMAND_WORD:
            return toDoParse(desc);

        case DeadlineCommand.COMMAND_WORD:
            return deadlineParse(desc);

        case EventCommand.COMMAND_WORD:
            return eventParse(desc);

        case DeleteCommand.COMMAND_WORD:
            return deleteParse(desc);

        case FindCommand.COMMAND_WORD:
            return new FindCommand(desc);
        case UpdateCommand.COMMAND_WORD:
            return new UpdateParser(desc).parse();
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        default:
            throw new DukeException(INVALID_COMMAND_MESSAGE);
        }
    }
}
