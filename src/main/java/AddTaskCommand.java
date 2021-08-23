public class AddTaskCommand extends Command {
    private Task taskToAdd;

    public AddTaskCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    @Override
    public String runAndGenerateDescription(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(taskToAdd);
        return String.format("Got it. I've added this task:\n  %s\n%s", taskToAdd.toString(), taskList.getOverview());
    }
}
