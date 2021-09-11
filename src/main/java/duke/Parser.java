package duke;

import duke.command.AddCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.RemoveCommand;
import duke.command.RestoreCommand;
import duke.command.UpdateCommand;
import duke.exception.DukeException;
import duke.exception.InvalidTaskNumException;
import duke.exception.MissingArgumentException;
import duke.exception.MissingKeywordException;
import duke.exception.MissingTaskNumException;
import duke.exception.UnrecognisedCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskTypes;
import duke.task.ToDo;

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

        String[] strArr = splitStringBySpace(str, 2);
        String commandWord = strArr[0];

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return prepareAdd(strArr);
        case DoneCommand.COMMAND_WORD:
            return prepareDone(strArr);
        case ListCommand.COMMAND_WORD:
            return prepareList(strArr);
        case RemoveCommand.COMMAND_WORD:
            return prepareRemove(strArr);
        case ClearCommand.COMMAND_WORD:
            return prepareClear(strArr);
        case RestoreCommand.COMMAND_WORD:
            return prepareRestore(strArr);
        case ExitCommand.COMMAND_WORD:
            return prepareExit(strArr);
        case FindCommand.COMMAND_WORD:
            return prepareFind(strArr);
        case UpdateCommand.COMMAND_WORD:
            return prepareUpdate(strArr);
        default:
            throw new UnrecognisedCommandException();
        }
    }


    private static Command prepareAdd(String[] args) throws DukeException {

        checkStrArrLength(args, 2, new MissingArgumentException());
        String[] taskArgs = splitStringBySpace(args[1], 2);
        checkStrArrLength(taskArgs, 2, new MissingArgumentException());

        String taskCommandWord = taskArgs[0];
        String taskDescription = taskArgs[1];

        switch (taskCommandWord) {
        case ToDo.COMMAND_WORD:
            return new AddCommand(TaskTypes.TODO, taskDescription);
        case Deadline.COMMAND_WORD:
            return new AddCommand(TaskTypes.DEADLINE, taskDescription);
        case Event.COMMAND_WORD:
            return new AddCommand(TaskTypes.EVENT, taskDescription);
        default:
            throw new UnrecognisedCommandException();
        }
    }

    private static Command prepareDone(String[] args) throws DukeException {
        checkStrArrLength(args, 2, new MissingTaskNumException("mark done"));
        int num = parseNum(args[1]);
        return new DoneCommand(num - 1);
    }

    private static Command prepareList(String[] args) throws DukeException {
        checkOneWordCommand(args, ListCommand.COMMAND_WORD);
        return new ListCommand();
    }

    private static Command prepareRemove(String[] args) throws DukeException {
        checkStrArrLength(args, 2, new MissingTaskNumException("remove"));
        int num = parseNum(args[1]);
        return new RemoveCommand(num - 1);
    }

    private static Command prepareClear(String[] args) throws DukeException {
        checkOneWordCommand(args, ClearCommand.COMMAND_WORD);
        return new ClearCommand();
    }

    private static Command prepareRestore(String[] args) throws DukeException {
        checkOneWordCommand(args, RestoreCommand.COMMAND_WORD);
        return new RestoreCommand();
    }

    private static Command prepareExit(String[] args) throws DukeException {
        checkOneWordCommand(args, ExitCommand.COMMAND_WORD);
        return new ExitCommand();
    }

    private static Command prepareFind(String[] args) throws DukeException {
        checkStrArrLength(args, 2, new MissingArgumentException());
        return new FindCommand(args[1]);
    }

    private static Command prepareUpdate(String[] args) throws DukeException {
        checkStrArrLength(args, 2, new MissingArgumentException());
        String nameKeyword = UpdateCommand.NAME_KEYWORD;
        String timeKeyword = UpdateCommand.TIME_KEYWORD;

        String params = args[1];
        boolean hasNameKeyword = params.contains(nameKeyword);
        boolean hasTimeKeyword = params.contains(timeKeyword);

        if (!hasNameKeyword && !hasTimeKeyword) {
            throw new MissingKeywordException(nameKeyword + " or " + timeKeyword);
        }

        String[] paramArr = params.split(UpdateCommand.SPLIT_REGEX);

        int taskNum = parseNum(paramArr[0]) - 1;

        if (hasNameKeyword && hasTimeKeyword) {
            boolean isNameFirst = params.indexOf(nameKeyword) < params.indexOf(timeKeyword);
            if (isNameFirst) {
                return new UpdateCommand(taskNum, paramArr[1], paramArr[2]);
            } else {
                return new UpdateCommand(taskNum, paramArr[2], paramArr[1]);
            }
        } else if (hasNameKeyword) {
            return new UpdateCommand(taskNum, paramArr[1], true);
        } else {
            return new UpdateCommand(taskNum, paramArr[1], false);
        }

    }

    private static int parseNum(String args) throws DukeException {
        try {
            int num = Integer.parseInt(args);
            if (num < 1) {
                throw new InvalidTaskNumException(num);
            }
            return num;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumException(e.getMessage());
        }
    }

    private static String[] splitStringBySpace(String str, int limit) {
        return str.split("\\s", limit);
    }

    private static void checkStrArrLength(String[] input, int minLength, DukeException exception) throws DukeException {
        if (input.length < minLength || input[minLength - 1].isBlank()) {
            throw exception;
        }
    }

    private static void checkOneWordCommand(String[] input, String didYouMean) throws DukeException {
        if (input.length > 1) {
            throw new UnrecognisedCommandException(didYouMean);
        }
    }

}
