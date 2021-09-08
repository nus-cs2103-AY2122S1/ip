package duke.exceptions;

/**
 * Class that handles loading file error exceptions.
 */
public class LoadingFileError extends DukeException {
    /**
     * Occurs when there's an error in loading and reading file.
     */
    public LoadingFileError() {
        super("Uwu! There's something wrong withw the existing file! "
                + "Did you meddle witwh it?\n"
                + "Creating new file for you. . .");
    }
}