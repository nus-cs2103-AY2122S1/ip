package duke.exception;

/**
 * Throws exception when load file does not exist.
 */
public class LoadFileMissing extends DukeException {

    public LoadFileMissing() {
        super("Meow? File to load does not exist.");
    }
}
