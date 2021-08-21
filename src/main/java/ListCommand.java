public class ListCommand extends Command {
    private TaskList taskList;
    private String dateString;

    public ListCommand(TaskList taskList, String dateString) {
        this.taskList = taskList;
        this.dateString = dateString;
    }

    @Override
    public void execute() {
        taskList.printList(dateString);
    }
}