package duke.exceptions;

public class DucSyntaxErrorException extends DucException {
    public DucSyntaxErrorException(String command) {
        super("I don't know what you are saying bruh, what the hell is '" + command + "?'\n");
    }
}
