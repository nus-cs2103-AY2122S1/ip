public class AddCommand extends Command {
    private final Tasklist.TaskTypes taskType;
    private final String input;

    public AddCommand(Tasklist.TaskTypes taskType, String input) {
        this.taskType = taskType;
        this.input = input;
    }

    @Override
    public void execute(Tasklist tasklist, Storage storage, Ui ui) throws AisuException {
        Task newTask = new Todo("dummy");
        switch (this.taskType) {
        case T:
            newTask = tasklist.addTask(this.input, Tasklist.TaskTypes.T);
            break;
        case D:
            newTask = tasklist.addTask(this.input, Tasklist.TaskTypes.D);
            break;
        case E:
            newTask = tasklist.addTask(this.input, Tasklist.TaskTypes.E);
            break;
        }
        storage.save(tasklist);
        ui.showToUser(" Got it! I've added this task:",
                " - " + newTask,
                " Now you have " + tasklist.getListSize() + " task(s) in the list.\n");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
