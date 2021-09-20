package duke.exceptions;

public class DukeDataLoadException extends DukeExceptions {

    public DukeDataLoadException(String message) {
        super("There was an error loading the save file!: " + message);
    }
}
