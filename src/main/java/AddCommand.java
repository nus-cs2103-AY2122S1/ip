public class AddCommand extends Command{
    private TaskList myTasks;
    private String next;

    public AddCommand(TaskList myTasks, String next) {
        this.myTasks = myTasks;
        this.next = next;
    }

    @Override
    void execute() {
        try {
            myTasks.addTask(next);
        } catch (DukeException dukeException) {
            Ui.showError(dukeException);
        }
    }
}
