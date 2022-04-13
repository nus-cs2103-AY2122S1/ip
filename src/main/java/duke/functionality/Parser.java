package duke.functionality;

import duke.commands.AddCommand;
import duke.commands.ArchiveCommand;
import duke.commands.ArchiveListCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.RestoreCommand;
import duke.commands.UnknownCommand;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

/**
 * Creates a parser that makes sense of user inputs.
 */
public class Parser {

    /**
     * Returns the parsed Command input by the user.
     *
     * @param userInput The unparsed input from the user.
     * @return Parsed Command.
     * @throws DukeException If illegal/missing arguments are found.
     */
    public static Command parseInput(String userInput) throws DukeException {
        String[] inputSplit = userInput.split(" ", 2);
        String command = inputSplit[0];

        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals(Todo.taskTag())) {
            return prepareAddCommandTodo(inputSplit);
        } else if (command.equals(Deadline.taskTag())) {
            return prepareAddCommandDeadline(inputSplit);
        } else if (command.equals(Event.taskTag())) {
            return prepareAddCommandEvent(inputSplit);
        } else if (command.equals("done")) {
            return prepareDoneCommand(inputSplit);
        } else if (command.equals("delete")) {
            return prepareDeleteCommand(inputSplit);
        } else if (command.equals("find")) {
            return prepareFindCommand(inputSplit);
        } else if (command.equals("archive")) {
            return prepareArchiveCommand(inputSplit);
        } else if (command.equals("listarchive")) {
            return new ArchiveListCommand();
        } else if (command.equals("restore")) {
            return prepareRestoreCommand(inputSplit);
        } else {
            return new UnknownCommand();
        }
    }

    private static AddCommand prepareAddCommandTodo(String[] inputSplit) throws DukeException {
        if (checkInputLength(inputSplit)) {
            throw DukeException.missingInput("todo");
        }
        String taskName = inputSplit[1];
        return new AddCommand(Todo.taskTag(), taskName, null);
    }

    private static AddCommand prepareAddCommandDeadline(String[] inputSplit) throws DukeException {
        if (checkInputLength(inputSplit)) {
            throw DukeException.missingInput("deadline");
        }
        String args = inputSplit[1];
        String[] argsSplit = args.split(" /by ", 2);
        if (checkInputLength(argsSplit)) {
            throw new DukeException("OOPS!!! Please indicate when the deadline for the task is.");
        }
        checkTimeFormat(argsSplit[1]);
        return new AddCommand(Deadline.taskTag(), argsSplit[0], argsSplit[1]);
    }

    private static AddCommand prepareAddCommandEvent(String[] inputSplit) throws DukeException {
        if (checkInputLength(inputSplit)) {
            throw DukeException.missingInput("event");
        }
        String args = inputSplit[1];
        String[] argsSplit = args.split(" /at ", 2);
        if (checkInputLength(argsSplit)) {
            throw new DukeException("OOPS!!! Please indicate the date of the event.");
        }
        checkTimeFormat(argsSplit[1]);
        return new AddCommand(Event.taskTag(), argsSplit[0], argsSplit[1]);
    }

    private static DoneCommand prepareDoneCommand(String[] inputSplit) throws DukeException {
        if (checkInputLength(inputSplit)) {
            throw new DukeException("OOPS!!! Please indicate which task you want to mark as done.");
        }
        String strTaskNum = inputSplit[1].split(" ")[0];
        try {
            int taskNum = Integer.parseInt(strTaskNum) - 1;
            return new DoneCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please indicate a valid index.");
        }
    }

    private static DeleteCommand prepareDeleteCommand(String[] inputSplit) throws DukeException {
        if (checkInputLength(inputSplit)) {
            throw new DukeException("OOPS!!! Please indicate which task you want to delete.");
        }
        String strTaskNum = inputSplit[1].split(" ")[0];
        try {
            int taskNum = Integer.parseInt(strTaskNum) - 1;
            return new DeleteCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please indicate a valid index.");
        }
    }

    private static FindCommand prepareFindCommand(String[] inputSplit) throws DukeException {
        if (checkInputLength(inputSplit)) {
            throw DukeException.missingInput("find");
        }
        String args = inputSplit[1];
        String keyword = args.contains(" ") ? args.split(" ", 2)[0] : args;
        return new FindCommand(keyword);
    }

    private static ArchiveCommand prepareArchiveCommand(String[] inputSplit) throws DukeException {
        if (checkInputLength(inputSplit)) {
            throw new DukeException("OOPS!!! Please indicate which task you want to archive.");
        }
        String strTaskNum = inputSplit[1].split(" ")[0];
        try {
            int taskNum = Integer.parseInt(strTaskNum) - 1;
            return new ArchiveCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please indicate a valid index.");
        }
    }

    private static RestoreCommand prepareRestoreCommand(String[] inputSplit) throws DukeException {
        if (checkInputLength(inputSplit)) {
            throw new DukeException("OOPS!!! Please indicate which task you want to restore.");
        }
        String strTaskNum = inputSplit[1].split(" ")[0];
        try {
            int taskNum = Integer.parseInt(strTaskNum) - 1;
            return new RestoreCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! Please indicate a valid index.");
        }
    }

    private static boolean checkInputLength(String[] inputSplit) {
        return inputSplit.length < 2;
    }

    private static void checkTimeFormat(String inputString) throws DukeException {
        if (!inputString.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new DukeException("OOPS!!! Please enter a valid format for the date/time of your task.");
        }
    }
}
