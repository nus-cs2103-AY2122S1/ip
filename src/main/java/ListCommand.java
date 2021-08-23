public class ListCommand extends Command {
    
    public ListCommand() {
        super();
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.showList();
    }
}
