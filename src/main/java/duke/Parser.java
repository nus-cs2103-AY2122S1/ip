package duke;

import duke.command.AddTaskCommand;
import duke.command.Command;
import duke.command.DeleteTaskCommand;
import duke.command.FindCommand;
import duke.command.MarkDoneCommand;
import duke.command.PrintListCommand;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandDukeException;
import duke.exceptions.NoArgumentDukeException;
import duke.exceptions.WrongArgumentDukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.RecurringTask;
import duke.tasks.Todo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    /** For separation of name and date in deadline input */
    private static final String DEADLINE_DELIMITER = " /by ";

    /** For separation of name and date in event input */
    private static final String EVENT_DELIMITER = " /at ";

    /** For separation of name and date in recurring task input */
    private static final String RECURRING_DELIMITER_1 = " /by ";

    /** For separation of date and interval in recurring task input */
    private static final String RECURRING_DELIMITER_2 = " /every ";

    /**
     * Reads and returns the corresponding command from a given string.
     *
     * @param command Command in String format.
     * @return Command that can execute the actions required.
     * @throws DukeException If command is invalid
     */
    public Command parseCommand(String command) throws DukeException {
        String inputFirst;
        int breakPoint = command.indexOf(" ");
        if (breakPoint == -1) {
            inputFirst = command;
        } else {
            inputFirst = command.substring(0, command.indexOf(" "));
        }
        try {
            switch(inputFirst) {
            case "list":
                return new PrintListCommand();
            case "done":
                return markDone(getArgs(command));
            case "todo":
                return addTodo(getArgs(command));
            case "deadline":
                return addDeadline(getArgs(command));
            case "event":
                return addEvent(getArgs(command));
            case "delete":
                return delete(getArgs(command));
            case "find":
                return find(getArgs(command));
            case "recurring":
                return addRecurring(getArgs(command));
            default:
                throw new InvalidCommandDukeException();
            }
        } catch (DukeException e) {
            throw new DukeException("Error for command: \"" + inputFirst + "\"\n" + e.getMessage());
        }
    }

    /**
     * Returns a Command that adds a Todo task
     * @param args String containing Todo task details
     * @return Command that adds the given Todo task when executed
     */
    private Command addTodo(String args) {
        return new AddTaskCommand(new Todo(args));
    }

    private Command addDeadline(String args) throws WrongArgumentDukeException {
        String[] strArr = args.split(DEADLINE_DELIMITER, 2);
        if (strArr.length >= 2) {
            LocalDate date = getLocalDate(strArr[1]);
            return new AddTaskCommand(new Deadline(strArr[0], date));
        } else {
            throw new WrongArgumentDukeException(String.format("Did you forget to use%s",
                    DEADLINE_DELIMITER));
        }

    }

    /**
     * Returns a Command that adds an Event task
     * @param args String containing Event task details
     * @return Command that adds the given Event task when executed
     * @throws WrongArgumentDukeException if argument is wrongly formatted.
     */
    private Command addEvent(String args) throws WrongArgumentDukeException {
        String[] strArr = args.split(EVENT_DELIMITER, 2);
        if (strArr.length >= 2) {
            LocalDate date = getLocalDate(strArr[1]);
            return new AddTaskCommand(new Event(strArr[0], date));
        } else {
            throw new WrongArgumentDukeException(String.format("Did you forget to use%s",
                    EVENT_DELIMITER));
        }
    }

    /**
     * Returns a Command that adds a Recurring task
     * @param args String containing Recurring task details
     * @return Command that adds the given Recurring task when executed
     * @throws WrongArgumentDukeException if argument is wrongly formatted.
     */
    private Command addRecurring(String args) throws WrongArgumentDukeException {
        String[] strArr = args.split(RECURRING_DELIMITER_1);
        if (strArr.length < 2) {
            throw new WrongArgumentDukeException(String.format("Did you forget to use%s",
                    RECURRING_DELIMITER_1));
        }
        String[] strArr2 = strArr[1].split(RECURRING_DELIMITER_2);
        if (strArr2.length < 2) {
            throw new WrongArgumentDukeException(String.format("Did you forget to use%s",
                    RECURRING_DELIMITER_2));
        }
        LocalDate date = getLocalDate(strArr2[0]);
        int intervalInDays = getInt(strArr2[1]);
        return new AddTaskCommand(new RecurringTask(strArr[0], date, intervalInDays));
    }

    /**
     * Gets argument to command if it exists.
     *
     * @param str String full command
     * @return get argument to command
     * @throws NoArgumentDukeException if no arguments
     */
    private String getArgs(String str) throws NoArgumentDukeException {
        int index = str.indexOf(" ");
        if (index == -1) {
            throw new NoArgumentDukeException();
        }
        String argument = str.substring(str.indexOf(" ") + 1);
        return argument;
    }

    /**
     * Marks a Task as done.
     *
     * @param indexStr string format of index
     * @throws WrongArgumentDukeException if argument is wrongly formatted.
     */
    private Command markDone(String indexStr) throws WrongArgumentDukeException {
        try {
            int index = Integer.parseInt(indexStr);
            return new MarkDoneCommand(index);
        } catch (NumberFormatException e) {
            throw new WrongArgumentDukeException("Not a number specified");
        }
    }

    /**
     * Deletes an entry in this Duke.
     *
     * @param indexStr string format of index
     * @throws WrongArgumentDukeException if argument is wrongly formatted.
     */

    private Command delete(String indexStr) throws WrongArgumentDukeException {
        try {
            int index = Integer.parseInt(indexStr);
            return new DeleteTaskCommand(index);
        } catch (NumberFormatException e) {
            throw new WrongArgumentDukeException("Not a number specified");
        }

    }

    /**
     * Finds an entry containing the specified string.
     *
     * @param toFind String to find in all entries.
     * @return A command that will find the entries when executed.
     */
    private Command find(String toFind) {
        return new FindCommand(toFind);
    }

    /**
     *
     * Converts a string to a LocalDate
     *
     * @param str string representation of a date
     * @return LocalDate object represented by string
     * @throws WrongArgumentDukeException if argument is wrongly formatted.
     */
    private LocalDate getLocalDate(String str) throws WrongArgumentDukeException {
        try {
            return LocalDate.parse(str);
        } catch (DateTimeParseException e) {
            throw new WrongArgumentDukeException("Cannot parse date.");
        }
    }

    /**
     * Returns an int from string.
     *
     * @param str String representation of a number.
     * @return Int value represented by string.
     * @throws WrongArgumentDukeException If str does not represent a number.
     */
    private int getInt(String str) throws WrongArgumentDukeException {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            throw new WrongArgumentDukeException("Cannot parse number");
        }
    }
}
