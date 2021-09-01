public class CommandAdd extends Command {
    private Task task;
    public CommandAdd(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui) {
        taskList.add(task);
        ui.print("Added task: " + task);
    }
}
