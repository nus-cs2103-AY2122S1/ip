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

class TaskIndexNotInteger extends DukeException {

    TaskIndexNotInteger(int length) {
        super("Meow? That's not an integer... Please enter a task number between 1 and " + length);
    }

}

class TaskNotFound extends DukeException {

    TaskNotFound(String taskStr) {
        super("Meow? I can't find " + taskStr);
    }

}

class TaskExistsException extends DukeException {

    TaskExistsException(Duke.TaskTypes taskTypes, String taskStr) {
        super("Meow? " + taskTypes + " " + taskStr + " already exists.");
    }

}

class TaskIndexOutOfBounds extends DukeException {

    TaskIndexOutOfBounds(int i, int length) {
        super("Meow? I can't find task " + i + "... Please enter a task number between 1 and " + length);
    }

}

class MissingParams extends DukeException {

    MissingParams(String params) {
        super("Meow? Missing params... Please use /" + params + " params.");
    }

}

class NothingAfterCommand extends DukeException {

    NothingAfterCommand(Duke.Commands command) {
        super("Meow? There's nothing after your command " + command.toString() + "... Meow meow meow?");
    }

}

class MissingArguments extends DukeException {

    MissingArguments(Duke.Commands command) {
        super("Meow? There's missing arguments in your command " + command.toString() + "... Meow meow meow?");
    }

}

class EmptyListException extends DukeException {

    EmptyListException(Duke.Commands command) {
        super("You have nothing in your list to " + command.toString() + ", meow!");
    }

}

class IllegalCommandException extends DukeException {

    IllegalCommandException(String commandStr) {
        super("Meow? There is no command " + commandStr + "!");
    }

}

class IllegalTaskTypeException extends DukeException {

    IllegalTaskTypeException(String taskTypeStr) {
        super(("Meow? There is no task type " + taskTypeStr + "!"));
    }

}

class DateTimeFormatException extends DukeException {

    DateTimeFormatException(String datetime) {
        super(("Meow? You entered " + datetime
                + ".\n   Please input date in 31/12/2021 or 2021-12-31 or 31Dec2021 format."
                + "\n   Please input time in 2300 or 11pm or 11.00pm format."));
    }
}
