package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.DukeException;

public class DeleteTaskCommand extends Command {

    public DeleteTaskCommand(String taskIndex) {
        super(taskIndex);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        taskList.delete(Integer.parseInt(super.getExtraInput()) - 1);
        String reply = "Task removed. You now have " + taskList.size() + " tasks remaining. ";
        if (taskList.size() > 0) {
            reply += "\n" + taskList;
        }
        return reply;
    }
}
