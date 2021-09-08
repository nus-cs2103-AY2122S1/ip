package duke.exceptions;

public class DukeSyntaxErrorException extends DukeException {
    public DukeSyntaxErrorException(String command) {
        super("Yo what is that? I cannot recognize '" + command + "'\n");
    }
}
