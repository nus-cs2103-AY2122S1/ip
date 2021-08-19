public class DukeUnknownCommandException extends DukeException {
    private String command;

    public DukeUnknownCommandException(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return String.format("%s I do not know how to handle the command '%s'!",
                super.toString(),
                this.command
        );
    }
}
