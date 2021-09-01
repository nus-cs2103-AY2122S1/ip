public class TodoCommand extends Command {
    private String input;
    private String taskDesc;

    public TodoCommand(String input) {
        this.input = input;
        this.taskDesc = input.replaceFirst("^todo", "");
    }

    @Override
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        Todo tTask = new Todo(taskDesc);
        ls.addTask(tTask);
        storage.rewriteFile(ls);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
