public class CommandDelete extends Command{
    private String taskName;

    public CommandDelete(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui) {
        Task deletedTask = taskList.delete(taskName);
        if (deletedTask != null) {
            ui.print("Noted. This task has been deleted");
            ui.print(deletedTask.toString());
        } else {
            ui.print("Oops! That task was not found.");
        }
    }
}
