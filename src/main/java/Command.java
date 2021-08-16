import java.util.Objects;

public enum Command {
    LIST("list"),
    ADD("add"),
    DELETE("delete"),
    DONE("done"),
    EVENT("event"),
    DEADLINE("deadline"),
    TODO("todo");

    private final String text;

    Command(String text) {
        this.text = text;
    }

    /**
     * Returns a Command enum from the command string.
     *
     * @param text the command text
     * @return the Command enum
     * @throws UnknownCommandException if the command text is an unknown command
     */
    public static Command fromString(String text) throws UnknownCommandException {
        for (Command command : Command.values()) {
            if (Objects.equals(command.text, text)) {
                return command;
            }
        }
        throw new UnknownCommandException();
    }

    @Override
    public String toString() {
        return text;
    }
}
