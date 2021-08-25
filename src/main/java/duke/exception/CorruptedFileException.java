package duke.exception;

public class CorruptedFileException extends DukeException{
    public CorruptedFileException() {
        super("Corrupted File");
    }
}