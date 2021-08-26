public class DeleteCommand extends Command {
    
    private int taskIndex;

    public DeleteCommand(String fullCommand) {
        String taskIndexString = fullCommand.replace("delete", "");
        this.taskIndex = Integer.parseInt(taskIndexString.trim());
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.remove(taskIndex - 1);
        System.out.println(String.format("Task deleted.\n %s", t));
        storage.save(tasks);
    }

    public Boolean isExit() {
        return false;
    }
}
