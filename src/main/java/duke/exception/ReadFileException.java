package duke.exception;

/**
 * The exception thrown when there are issues reading the myTasks.txt file.
 */
public class ReadFileException extends DukeException {
    @Override
    public String getMessage() {
        return "Oh no! Duke cannot retrieve data from the file :(";
    }
}
