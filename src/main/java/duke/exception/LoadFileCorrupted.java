package duke.exception;

/**
 * Throws exception when load file is corrupted.
 */
public class LoadFileCorrupted extends DukeException {

    public LoadFileCorrupted() {
        super("Meow? File could not be loaded as file was corrupted.");
    }
}
