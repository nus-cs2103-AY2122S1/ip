package duke;

public class FileParseErrorException extends DukeException {

    public FileParseErrorException() {
        super("☹ OOPS!!! The file contained tasks that were not readable. File may have been tampered.");
    }
}
