public class AddCommand extends Command {
    private final Tasklist.TaskTypes taskType;
    private final String input;

    public AddCommand(Tasklist.TaskTypes taskType, String input) {
        this.taskType = taskType;
        this.input = input;
    }

    @Override
    public void execute(Tasklist tasklist, Storage storage, Ui ui) throws AisuException {
        switch (this.taskType) {
        case T:
            tasklist.addTask(this.input, Tasklist.TaskTypes.T);
            break;
        case D:
            tasklist.addTask(this.input, Tasklist.TaskTypes.D);
            break;
        case E:
            tasklist.addTask(this.input, Tasklist.TaskTypes.E);
            break;
        }
        storage.save(tasklist);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
