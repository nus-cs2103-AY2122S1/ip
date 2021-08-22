public class ExitCommand extends Command{

    public static final String COMMAND_WORD = "exit";

    public ExitCommand() {

    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(Tasklist list, Ui ui, Store storage) throws DukeException {

        storage.saveTasksToStore(list);
        ui.printGoodByeMessage();

    }
}
