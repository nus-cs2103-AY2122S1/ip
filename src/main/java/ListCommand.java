public class ListCommand implements Command {

    private TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        this.taskList.listHistory();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void invalidArguments() {
        // No arguments are needed for a list command
    }
}
