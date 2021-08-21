public class AddCommand extends Command {
    private TaskList taskList;
    private String task;
    private String type;

    public AddCommand(TaskList taskList, String task, String type) {
        this.taskList = taskList;
        this.task = task;
        this.type = type;
    }

    /**
     * Adds the given task into the task list and prints the outcome using displayMessage.
     *
     * @param task description of task
     * @param typeOfTask type of task from ToDo, Deadline, Event. Handled by switch case.
     */
    @Override
    public void execute() {
        Ui.displayMessage(this.taskList.addToList(this.task, this.type));
    }
}