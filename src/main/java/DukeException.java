abstract class DukeException extends Exception {
    public DukeException (String errorMessage) {
        super(errorMessage);
    }

    abstract void printErrorMessage();
}
