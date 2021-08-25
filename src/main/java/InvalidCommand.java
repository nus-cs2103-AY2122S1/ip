public class InvalidCommand extends Command{

    public InvalidCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printErrorMessage("Invalid command!\n" +
                "Try TODO, DEADLINE or EVENT follow by task description.");
        return true;
    }
}
