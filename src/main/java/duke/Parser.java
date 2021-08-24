package duke;

import duke.command.AddCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.RemoveCommand;
import duke.command.RestoreCommand;
import duke.task.TaskTypes;

/**
 * The Parser class encapsulates methods related to parsing user input.
 */
public class Parser {

    /**
     * Parses a given string representing user input and returns the related Command.
     * Throws a DukeException when an invalid command is received.
     *
     * @param str The given user input.
     * @return a Command corresponding to the user input.
     * @throws DukeException when the given user input is invalid.
     */
    public static Command parse(String str) throws DukeException {
        String[] strArr = str.split("\\s", 2);

        switch (strArr[0]) {
        case AddCommand.COMMAND_WORD:
            if (strArr.length < 2) {
                throw new DukeException("you are missing arguments for the add command!");
            }
            return prepareAdd(strArr[1]);
        case DoneCommand.COMMAND_WORD:
            if (strArr.length < 2) {
                throw new DukeException("you did not specify a task number to mark done!");
            }
            return prepareDone(strArr[1]);
        case ListCommand.COMMAND_WORD:
            if (strArr.length > 1) {
                throw new DukeException("you typed in something i cannot recognise!\ndid you mean to type list?");
            }
            return new ListCommand();
        case RemoveCommand.COMMAND_WORD:
            if (strArr.length < 2) {
                throw new DukeException("you did not specify a task number to remove!");
            }
            return prepareRemove(strArr[1]);
        case ClearCommand.COMMAND_WORD:
            if (strArr.length > 1) {
                throw new DukeException("you typed in something i cannot recognise!\ndid you mean to type clear?");
            }
            return new ClearCommand();
        case RestoreCommand.COMMAND_WORD:
            if (strArr.length > 1) {
                throw new DukeException("you typed in something i cannot recognise!\ndid you mean to type restore?");
            }
            return new RestoreCommand();
        case ExitCommand.COMMAND_WORD:
            if (strArr.length > 1) {
                throw new DukeException("you typed in something i cannot recognise!\ndid you mean to type bye?");
            }
            return new ExitCommand();
        default:
            throw new DukeException("you typed in something i cannot recognise!");
        }
    }

    private static Command prepareAdd(String args) throws DukeException {
        String[] argArr = args.split("\\s", 2);

        if (argArr.length < 2) {
            throw new DukeException("the description of a task cannot be empty!");
        }

        String taskArgs = argArr[1];

        switch (argArr[0]) {
        case "todo":
            return new AddCommand(TaskTypes.TODO, taskArgs);
        case "deadline":
            if (!taskArgs.contains("/by")) {
                throw new DukeException("you are missing the /by keyword");
            }
            return new AddCommand(TaskTypes.DEADLINE, taskArgs);
        case "event":
            if (!taskArgs.contains("/at")) {
                throw new DukeException("you are missing the /at keyword");
            }
            return new AddCommand(TaskTypes.EVENT, taskArgs);
        default:
            throw new DukeException("you typed in a task type i cannot recognise!");
        }
    }

    private static Command prepareDone(String args) throws DukeException {
        int num = parseNum(args);
        return new DoneCommand(num - 1);
    }

    private static Command prepareRemove(String args) throws DukeException {
        int num = parseNum(args);
        return new RemoveCommand(num - 1);
    }

    private static int parseNum(String args) throws DukeException {
        try {
            int num = Integer.parseInt(args);
            if (num < 1) {
                throw new DukeException("you typed an invalid number: " + num);
            }
            return num;
        } catch (NumberFormatException e) {
            throw new DukeException("you typed in an invalid input! " + e.getMessage());
        }
    }
}
