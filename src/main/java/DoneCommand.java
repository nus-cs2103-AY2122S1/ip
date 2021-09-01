public class DoneCommand extends Command {
    private String input;
    private int taskNumber;

    public DoneCommand(String input) throws DukeException {
        this.input = input;
        try {
            if (input.equals("done") || input.equals("done ")) {
                throw new DukeException("A number must follow after the command word 'done'.");
            }
            this.taskNumber = Integer.valueOf(input.substring(5)) - 1;;
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS! Please enter a valid task number.");
        }
    }

    @Override
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        if (taskNumber < 0 || taskNumber >= ls.getSize()) {
            throw new DukeException("Item does not exist in the list.");
        }
        Task task = ls.getTask(taskNumber);
        task.markAsDone();
        storage.rewriteFile(ls);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
