public class DeleteCommand extends Command {
    private String input;
    private int taskNumber;

    public DeleteCommand(String input) throws DukeException {
        this.input = input;
        try {
            if (input.equals("delete") || input.equals("delete ")) {
                throw new DukeException("A number must follow after the command word 'delete'.");
            }
            this.taskNumber = Integer.valueOf(input.substring(7)) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS! Please enter a valid task number.");
        }
    }

    @Override
    public void execute(TaskList ls, Ui ui, Storage storage) throws DukeException {
        if (taskNumber < 0 || taskNumber >= ls.getSize()) {
            throw new DukeException("Item does not exist in the list.");
        }
        ls.removeTask(taskNumber);
        storage.rewriteFile(ls);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
