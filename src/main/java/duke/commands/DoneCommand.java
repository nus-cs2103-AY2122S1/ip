package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.Task;
import duke.ui.TextUi;

public class DoneCommand extends Command {
    private int index;

    public DoneCommand(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input.split(" ")[1]) - 1;
            this.index = index;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Indicate which task you want to mark as done.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The number is out of range. "
                    + "Indicate the correct task number that you want to mark as done.");
        }
    }


    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task = tasks.markAsDone(index);
        String response = TextUi.showTaskDone(task);
        return response;
    }
}
