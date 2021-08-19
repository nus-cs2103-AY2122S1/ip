
public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }

}

class TaskIndexNotInteger extends DukeException {

    TaskIndexNotInteger(int length) {
        super(Duke.Box("Meow? That's not an integer... Please enter a task number between 0 and " + length));
    }

}

class TaskIndexOutOfBounds extends DukeException {

    TaskIndexOutOfBounds(int length) {
        super(Duke.Box("Meow? I can't find that task... Please enter a task number between 0 and " + length));
    }

}

class MissingParams extends DukeException {

    MissingParams(String params) {
        super(Duke.Box("Meow? Missing params... Please use /" + params + " params."));
    }

}

class NothingAfterCommand extends DukeException {

    NothingAfterCommand(Duke.Commands command) {
        super(Duke.Box("Meow? There's nothing after your command " + command.toString() + "... Meow meow meow?"));
    }

}

class MissingArguments extends DukeException {

    MissingArguments(Duke.Commands command) {
        super(Duke.Box("Meow? There's missing arguments in your command " + command.toString() + "... Meow meow meow?"));
    }

}

class EmptyListException extends DukeException {

    EmptyListException(Duke.Commands command) {
        super(Duke.Box("You have nothing in your list to " + command.toString() + ", meow!"));
    }

}

class IllegalCommandException extends DukeException {

    IllegalCommandException(String commandStr) {
        super(Duke.Box("Meow? There is no command " + commandStr + "!"));
    }

}

