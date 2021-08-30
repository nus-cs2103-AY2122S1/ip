public abstract class DukeException extends Exception {
    private Ui ui;

    protected DukeException(Ui ui) {
        this.ui = ui;
    }

    protected String errorMessage(String errorMessage) {
        return ui.formatErrorMessage(errorMessage);
    }
}
