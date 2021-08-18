package utils;

/**
 * Command enum class.
 *
 * This enum class is used by the service.Parser and helps direct the TaskManager
 * to the right operations. They follow the user inputs used to trigger such commands.
 */
public enum Command {
    BYE("bye"),
    LIST("list"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    EVENT("event"),
    DEADLINE("deadline"),
    INVALID("\n"); // dummy value

    private final String text;

    Command(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return this.text;
    }

    /**
     * Provides a Command from the source string, only examines the first word in source.
     *
     * @param source source string to obtain command
     * @return Command
     */
    public static Command parseFromFirstWord(String source) {
        for (Command command: Command.values()) {
            if (source.startsWith(command.text)) {
                return command;
            }
        }
        return Command.INVALID; // default
    }
}
