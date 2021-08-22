public class DeleteCommand extends Command{
    private TaskList myTasks;
    private String next;
    public DeleteCommand(TaskList myTasks, String next) {
        this.myTasks = myTasks;
        this.next = next;
    }
    @Override
    void execute() {
        if (next.length() > 7) {
            myTasks.getDelete(next);
        } else {
            Ui.myPrint("☹ OOPS!!! Please enter a valid number, such as delete 3");
        }
    }
}
