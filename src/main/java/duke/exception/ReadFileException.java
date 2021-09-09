package duke.exception;

public class ReadFileException extends DukeException {
    @Override
    public String getMessage() {
        return "Oh no! Duke cannot retrieve data from the file :(";
    }
}
