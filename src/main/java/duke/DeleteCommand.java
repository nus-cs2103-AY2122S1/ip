package duke;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String input) throws DukeException {
        try {
            this.index = Integer.parseInt(input.substring(7));
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Invalid Task. Please try again.\n");
        }
    }

    @Override
    public void runCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.taskDeleted(taskList.getTask(index - 1));
        taskList.deleteTask(index - 1);
        ui.showTaskListSize(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
