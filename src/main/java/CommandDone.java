public class CommandDone extends Command {
    private String taskName;
    public CommandDone(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui) {
        Task task = taskList.find(taskName);
        if (task == null){
            ui.displayError("No such file found: " + taskName);
        } else {
            task.markDone();
            ui.print("Yay :) This task is done:\n" + task.toString());
        }
    }
}
