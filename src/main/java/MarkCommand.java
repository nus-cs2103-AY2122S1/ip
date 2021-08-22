public class MarkCommand extends Command{

    private final int toMark;
    public static final String COMMAND_WORD = "add";

    public MarkCommand(int toMark) {

        this.toMark = toMark;

    }

    @Override
    public void execute(Tasklist list, Ui ui, Store storage) throws DukeException{
        String successMessage = list.markTask(toMark);
        ui.printMessage(successMessage);

    }

}
