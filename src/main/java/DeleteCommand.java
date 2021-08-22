public class DeleteCommand extends Command {
    private int index;

    /**
     * Returns true if a valid delete operation is entered.
     * False otherwise.
     *
     * @param input User input of the commands.
     * @return If the input contains a valid done operations.
     */
    public static boolean isDeleteOps(String input) {
        int length = input.length();
        if (length < 8) {
            return false;
        }
        try {
            Integer.parseInt(input.substring(7));
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public DeleteCommand(String command) throws DukeException {
        if (isDeleteOps(command)) {
            this.index = Integer.parseInt(command.substring(7));
        } else {
            throw new DukeException("☹ Would you specify the task for me my dear?");
        }
    }
    
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index > taskList.size()) {
            throw new DukeException("☹ oopsie!!! The specified task does not exit.");
        }
        taskList.deleteTask(index);
        ui.showDelete(taskList.getTask(index), taskList.size());
        storage.updateStorage(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
