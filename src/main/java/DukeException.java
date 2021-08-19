
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

    NothingAfterCommand(String command) {
        super(Duke.Box("Meow? There's nothing after your command " + command + "... Meow meow meow?"));
    }

}

class MissingArguments extends DukeException {

    MissingArguments(String command) {
        super(Duke.Box("Meow? There's missing arguments in your command " + command + "... Meow meow meow?"));
    }

}

class EmptyListException extends DukeException {

    EmptyListException(String command) {
        super(Duke.Box("You have nothing in your list to " + command + ", meow!"));
    }

}

