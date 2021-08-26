public class DoneCommand extends Command {

    private int taskIndex;

    public DoneCommand(String fullCommand) {
        String taskIndexString = fullCommand.replace("done", "");
        this.taskIndex = Integer.parseInt(taskIndexString.trim());
    }
    
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task t = tasks.get(taskIndex - 1);
        t.finishTask();
        System.out.println(String.format("Congratulations on finishing this task!\n %s", t));
        storage.save(tasks);
    }

    public Boolean isExit() {
        return false;
    }

}
