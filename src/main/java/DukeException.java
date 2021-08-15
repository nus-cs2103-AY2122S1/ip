public class DukeException extends Exception {

    public DukeException(String message) {
        super(message);
    }
}

class UnknownTagException extends DukeException {
    public UnknownTagException() {
        super("No such tag! Please input the correct tag!");
    }
}

class NoSuchTaskException extends DukeException {
    public NoSuchTaskException() {
        super("You do not have this many task added yet, please check and try again.");
    }
}

class IllegalFormatException extends DukeException {
    public IllegalFormatException() {
        super("Wrong Format used! Please you the correct format!");
    }
}

