public class DeleteCommand extends Command{
    private final String action;
    public DeleteCommand(String action) {
        super(false);
        this.action = action;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws TaskNotFoundException,
            InvalidInputException, SaveFileException{
        try {
            int taskNumber = Integer.parseInt(action);
            if (taskNumber <= tasks.size() && taskNumber > 0) {
                Task taskToDelete = tasks.get(taskNumber - 1);
                tasks.remove(taskNumber - 1);
                ui.showTaskRemoved(taskToDelete, tasks);
                storage.save(tasks);
            } else {
                throw new TaskNotFoundException("The task chosen does not exist. Use 'list' to see all your tasks.");
            }
        } catch (NumberFormatException e){
            throw new InvalidInputException("Command 'delete' require an integer as the second parameter");
        }
    }
}
