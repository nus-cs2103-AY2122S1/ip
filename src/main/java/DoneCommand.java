public class DoneCommand extends Command {
    private TaskList taskList;
    private int index;

    public DoneCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    /**
     * Marks task in the given index as completed (done) and prints the outcome using displayMessage.
     *
     * @param index index of task to be mark as completed (indexing starts from 1)
     */
    @Override
    public void execute() {
        Ui.displayMessage(this.taskList.markTaskAsDone(this.index));
    }
}