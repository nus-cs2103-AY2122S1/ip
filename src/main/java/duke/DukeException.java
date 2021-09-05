package duke;

/**
 * DukeException specifies the message to print when an exception is thrown in Duke.
 *
 * @author Gabriel Goh
 */
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

}

/**
 * Throws exception when Task index input is not an integer.
 */
class TaskIndexNotInteger extends DukeException {

    TaskIndexNotInteger(int length) {
        super("Meow? That's not an integer... Please enter a task number between 1 and " + length);
    }

}

/**
 * Throws exception when Task cannot be found in task list.
 */
class TaskNotFound extends DukeException {

    TaskNotFound(String taskStr) {
        super("Meow? I can't find " + taskStr);
    }

}

/**
 * Throws exception when task of same name exists.
 */
class TaskExistsException extends DukeException {

    TaskExistsException(Duke.TaskTypes taskTypes, String taskStr) {
        super("Meow? " + taskTypes + " " + taskStr + " already exists.");
    }

}

/**
 * Throws exception when task is integer but is out of bounds of task list.
 */
class TaskIndexOutOfBounds extends DukeException {

    TaskIndexOutOfBounds(int i, int length) {
        super("Meow? I can't find task " + i + "... Please enter a task number between 1 and " + length);
    }

}

/**
 * Throws exception when expected parameters are missing.
 */
class MissingParams extends DukeException {

    MissingParams(String params) {
        super("Meow? Missing params... Please use /" + params + " params.");
    }

}

/**
 * Throws exception when no arguments are passed after command.
 */
class NothingAfterCommand extends DukeException {

    NothingAfterCommand(Duke.Commands command) {
        super("Meow? There's nothing after your command " + command.toString() + "... Meow meow meow?");
    }

}

/**
 * Throws exception when some expected arguments are missing.
 */
class MissingArguments extends DukeException {

    MissingArguments(Duke.Commands command) {
        super("Meow? There's missing arguments in your command " + command.toString() + "... Meow meow meow?");
    }

}

/**
 * Throws exception when list is empty.
 */
class EmptyListException extends DukeException {

    EmptyListException(Duke.Commands command) {
        super("You have nothing in your list to " + command.toString() + ", meow!");
    }

}

/**
 * Throws exception when input is not a command.
 */
class IllegalCommandException extends DukeException {

    IllegalCommandException(String commandStr) {
        super("Meow? There is no command " + commandStr + "!");
    }

}

/**
 * Throws exception when task type does not exist.
 */
class IllegalTaskTypeException extends DukeException {

    IllegalTaskTypeException(String taskTypeStr) {
        super(("Meow? There is no task type " + taskTypeStr + "!"));
    }

}

/**
 * Throws exception when date and time are not in parsable format.
 */
class DateTimeFormatException extends DukeException {

    DateTimeFormatException(String datetime) {
        super(("Meow? You entered " + datetime
                + ".\n   Please input date in 31/12/2021 or 2021-12-31 or 31Dec2021 format."
                + "\n   Please input time in 2300 or 11pm or 11.00pm format."));
    }
}

/**
 * Throws exception when load file is corrupted or does not exist.
 */
class LoadFileCorrupted extends DukeException {

    LoadFileCorrupted() {
        super("Meow? File could not be loaded as file was corrupted.");
    }
}
