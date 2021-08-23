public enum Command {
    BYE,
    LIST,
    DONE,
    DELETE,
    DEADLINE,
    EVENT,
    TODO;

    private static final String INCOHERENT_INPUT_MESSAGE = "I'm sorry, but I don't know what that means :-(";

    public static Command initialiseCommand(String commandString) throws DukeException {
        try {
            return Command.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException err) {
            throw new DukeException(INCOHERENT_INPUT_MESSAGE);
        }
    }
}
