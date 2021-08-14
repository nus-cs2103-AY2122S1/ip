public class NoDescriptionError extends DukeException{
    private String command;

    NoDescriptionError(String error) {
        this.command = error;
    }

    @Override
    public String toString() {
        return super.toString() + "The description of a " + command + " cannot be empty.";
    }
}
