public class DeleteCommand extends Command {
    private final int id;
    DeleteCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(TaskManager taskManager, Ui ui) {
        Task deletedTask = taskManager.deleteTask(id);
        ui.reply(String.format("Noted. I've removed this task: \n" +
                "%s\n" +
                "Now you have %d tasks in the list.", deletedTask.toString(), taskManager.taskCount())
        );
    }
}
