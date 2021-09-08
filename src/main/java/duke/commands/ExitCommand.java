package duke.commands;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String MESSAGE_EXIT = "Exiting Duke. See you again!";

    /** Empty constructor */
    public ExitCommand() {
    }

    @Override
    public CommandResult execute() {
       return new CommandResult(MESSAGE_EXIT);
    }
}
