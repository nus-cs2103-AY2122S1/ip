public class ListCommand extends Command{

    public static final String COMMAND_WORD = "list";

    public ListCommand() {

    }

    @Override
    public void execute(Tasklist list, Ui ui, Store storage) throws DukeException{
        ui.printMessage("Here are the tasks in your list:\n" + list.toString());

    }
}
