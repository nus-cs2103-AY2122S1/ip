package duke;

public class DukeException extends Exception {

    protected String errorMessage;

    public DukeException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return ":(( sorry bud but " + this.errorMessage;
    }
}
