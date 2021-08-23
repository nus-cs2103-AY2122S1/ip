public class CommandList extends Command{
    public static final String KEYWORD = "list";

    public CommandList() {
    }

    @Override
    public void execute(TaskList tl, Storage st, Ui ui) {
        tl.printAllTasks();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
