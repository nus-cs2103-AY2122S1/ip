/**
 * CS2103T Individual Project AY 21/22 Sem 1
 * Project Duke
 *
 * Current Progress: Level 6. Delete
 *
 * Description:
 * Class to encapsulates all possible exceptions that will be
 * raised whilst interacting with chatbot, Duke.
 *
 * @author Keith Tan
 */
public class DukeException extends Exception {

    private String message;

    public DukeException(String message) {

        this.message = message;

    }

    @Override
    public String toString() {

        return this.message;

    }

}

class MissingArgumentException extends DukeException {

    public MissingArgumentException(String missingCondition, String origin) {

        super("Hi, you are missing the " + missingCondition + " for the " + origin +  "!");

    }

}

class InvalidArgumentException extends DukeException {

    public InvalidArgumentException(String invalidCondition, String origin) {

        super("Hi, the " + invalidCondition + " for the " + origin + " is invalid!");

    }

}

class InvalidCommandException extends DukeException {

    public InvalidCommandException() {

        super("Hi I don't understand your command :(");

    }

}

class IndexOutOfRangeException extends DukeException {

    public IndexOutOfRangeException(int enteredIndex, int listSize) {

        super("Hi, " + enteredIndex + " is not a valid index. List has currently " + listSize + " items.");

    }

}

