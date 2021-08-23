package duke;

import duke.command.*;
import duke.exceptions.*;
import duke.tasks.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    public Command parseCommand(String command) throws DukeException {
        String inputFirst;
        int breakPoint = command.indexOf(" ");
        if (breakPoint == -1) {
            inputFirst = command;
        } else {
            inputFirst = command.substring(0, command.indexOf(" "));
        }
        try {
            switch (inputFirst) {
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
            case "bye":
                return new ExitCommand();
            default:
                throw new InvalidCommandDukeException();
            }
        } catch (DukeException e) {
            throw new DukeException("Error for command: \"" + inputFirst + "\"\n" + e.getMessage());
        }
    }

    private Command addTodo(String args) {
        return new AddTaskCommand(new Todo(args));
    }

    private Command addDeadline(String args) throws WrongArgumentDukeException {
        String[] strArr = args.split(" /by ", 2);
        if (strArr.length >= 2) {
            LocalDate date = getLocalDate(strArr[1]);
            return new AddTaskCommand(new Deadline(strArr[0], date));
        } else {
            throw new WrongArgumentDukeException("Did you forget to use /by");
        }

    }

    private Command addEvent(String args) throws WrongArgumentDukeException {
        String[] strArr = args.split(" /at ", 2);
        if (strArr.length >= 2) {
            LocalDate date = getLocalDate(strArr[1]);
            return new AddTaskCommand(new Event(strArr[0], date));
        } else {
            throw new WrongArgumentDukeException("Did you forget to use /at");
        }

    }

    /**
     * Get argument to command if it exists.
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
}
