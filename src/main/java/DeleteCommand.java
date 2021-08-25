import java.io.IOException;

public class DeleteCommand extends Command { //DeleteCommand class to handle the deletion of task from the list
    private final int index;

    public DeleteCommand(String input) throws DukeException {
        super(true);
        if (input == null) {
            //Catch if there is no number after the command
            throw new DukeException("☹ OOPS!!! delete will require a task number to update.");
        }
        this.index = Integer.parseInt(input.trim());
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        if (index > taskList.getCount() || index <= 0) {
            //Catches if the number is > than the number of task or if its negative
            throw new DukeException("☹ OOPS!!! The number is not in within the number of tasks!");
        } else {
            Ui.deleteMessage(taskList.delete(index - 1), taskList.getCount());
            Storage.updateText(taskList);
        }
    }
}
