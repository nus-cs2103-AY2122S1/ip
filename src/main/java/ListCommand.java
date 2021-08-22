public class ListCommand extends Command{
    private TaskList myTasks;
    public ListCommand(TaskList myTasks) {
        super();
        this.myTasks = myTasks;
    }
    @Override
    void execute() {
        myTasks.printTaskList();
    }
}
