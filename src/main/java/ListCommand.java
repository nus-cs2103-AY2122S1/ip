public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public ListCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String msg = tasks.listTaskArr();
        if (msg.isBlank()) {
            msg = "No tasks in the list!";
        }
        ui.printMsg(msg);
    }

}
