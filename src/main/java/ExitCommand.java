public class ExitCommand extends Command{

    public ExitCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) {
        return false;
    }


}
