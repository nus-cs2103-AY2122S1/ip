package duke.exceptions;

public class CorruptedFileException extends DukeException {
    public CorruptedFileException() {
        super("☹ Sorry! Your duke.txt file is corrupted and cannot be loaded!");
    }
}