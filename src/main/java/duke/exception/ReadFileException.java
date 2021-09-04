package duke.exception;

public class ReadFileException extends DukeException {
    @Override
    public String getMessage() {
        return "Oh no! duke.Duke cannot retrieve data from the file :(";
    }
}
