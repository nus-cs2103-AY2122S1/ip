package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.Task;
import duke.ui.TextUi;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            this.index = index;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Indicate which task that you want to delete.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The number is out of range. "
                    + "Indicate the correct task number that you want to delete.");
        }
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.deleteTask(index);
        String response = TextUi.showTaskRemoved(task);
        response += TextUi.showUpdatedNumberOfTasks(tasks);
        return response;
    }
}
