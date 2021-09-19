package duke.exception;

/**
 * Throws exception when expected parameters are missing.
 */
public class MissingParams extends DukeException {

    public MissingParams(String params) {
        super("Meow? Missing params... Please use /" + params + " params.");
    }

}
