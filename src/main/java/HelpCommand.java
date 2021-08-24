public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + " - display the list of available commands";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printHorizontalLine();
        System.out.println("Available commands:\n" +
                TodoCommand.MESSAGE_USAGE + "\n" +
                DeadlineCommand.MESSAGE_USAGE + "\n" +
                EventCommand.MESSAGE_USAGE + "\n" +
                DoneCommand.MESSAGE_USAGE + "\n" +
                DeleteCommand.MESSAGE_USAGE + "\n" +
                ListCommand.MESSAGE_USAGE + "\n" +
                HelpCommand.MESSAGE_USAGE + "\n" +
                ExitCommand.MESSAGE_USAGE);
        ui.printHorizontalLine();
    }
}
