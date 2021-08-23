package duke.parser;

import duke.DukeException;

public class UnableToParseException extends DukeException {
    public UnableToParseException(String description) {
        super("Unable to parse " + description + "!");
    }
}
