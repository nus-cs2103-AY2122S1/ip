package duke.command;

public enum Commands {
    EXIT (new ExitCommand()),
    LIST (new ListCommand()),
    TODO (new ToDoCommand()),
    DEADLINE (new DeadlineCommand()),
    EVENT (new EventCommand()),
    DONE (new DoneCommand()),
    DELETE (new DeleteCommand()),
    FIND (new FindCommand());

    private final Command command;
    Commands(Command command) {
        this.command = command;
    }

    /**
     * Checks if the first word of the user input matches this command.
     *
     * @param firstWord first word of user input
     * @return true if the first word of the user input matches this command
     */
    public boolean isCommand(String firstWord) {
        return firstWord.equals(command.getCommandString());
    }

    /**
     * Returns the command of this type
     *
     * @return the Command
     */
    public Command getCommand() {
        return command;
    }
}
