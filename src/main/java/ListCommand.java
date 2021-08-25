public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String USAGE_MESSAGE =
            "To list all tasks, use 'list' or 'list <date>' (to show tasks on certain date).";

    private final String commandArguments;

    public ListCommand(String commandArguments) {
        this.commandArguments = commandArguments;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui, Storage storage) throws DukeException {
        if (commandArguments.isEmpty()) {
            ui.print(taskManager.list());
        } else {
            DukeDateTime date = DukeDateTime.parseUserInputDate(commandArguments);
            ui.print(taskManager.list(date));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
