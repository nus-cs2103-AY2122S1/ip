public class NoSuchTaskException extends DukeException {
    public NoSuchTaskException(Ui ui) {
        super(ui);
    }

    @Override
    public String getMessage() {
        return super.errorMessage("The task does not exist.");
    }
}
