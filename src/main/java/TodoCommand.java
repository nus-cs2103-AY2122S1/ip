public class TodoCommand extends Command{
    String action;
    public TodoCommand(String action) {
        super(false);
        this.action = action;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoActionException, SaveFileException{
        if (action.length() == 0) {
            throw new NoActionException("Command 'todo' requires a task action");
        }
        Task newTask = new Todo(action);
        tasks.add(newTask);
        ui.showTaskAdded(newTask, tasks);
        storage.save(tasks);
    }
}
