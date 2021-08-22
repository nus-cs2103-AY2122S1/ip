public class DeleteCommand extends Command{
    private final int toDelete;
    public static final String COMMAND_WORD = "delete";

    public DeleteCommand(int toDelete) {

        this.toDelete = toDelete;

    }

    @Override
    public void execute(Tasklist list, Ui ui, Store storage) throws DukeException{
        String successMessage = list.deleteTask(toDelete);
        ui.printMessage(successMessage);

    }
}
