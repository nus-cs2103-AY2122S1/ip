public enum Commands {
    EXIT ("bye"),
    LIST ("list"),
    TODO ("todo"),
    DEADLINE ("deadline"),
    EVENT ("event"),
    DONE ("done"),
    DELETE ("delete");

    private final String command;
    Commands(String command) {
        this.command = command;
    }

    public boolean isCommand(String input) {
        return input.equals(command);
    }

    @Override
    public String toString() {
        return command;
    }
}
