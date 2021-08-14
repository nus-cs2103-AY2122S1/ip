public abstract class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    public DukeOutputMessage getOutputMessage() {
        return new DukeOutputMessage(this.getMessage());
    }
}
