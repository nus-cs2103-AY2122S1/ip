public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "bye";

    ExitCommand(TaskList taskList) {
        super(taskList);
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("Exiting now...", true);
    }
}
