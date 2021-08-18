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
    EMPTY(""),
    INVALID("\0"); // dummy value

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
     * @param input source string to obtain command
     * @return associate Command, else INVALID as default
     */
    public static Command parseFromInput(String input) {
        String[] instructionArray = input.trim().split(" ", 2); // split first word
        String instruction = instructionArray[0];

        for (Command command : Command.values()) {
            if (instruction.equals(command.text)) {
                return command;
            }
        }
        return Command.INVALID; // default
    }
}
