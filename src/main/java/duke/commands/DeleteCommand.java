package duke.commands;

import duke.DateTimeHandler;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates the delete command, used to delete tasks from the task list
 */
public class DeleteCommand extends Command {

    public DeleteCommand(String arguments) {
        super(arguments);
    }

    @Override
    public Command of(String arguments) {
        return new DeleteCommand(arguments);
    }

    @Override
    public String execute(TaskList tl, Storage s, Ui ui, DateTimeHandler dth) {
        String args = super.getArguments();
        if (args.length() == 0) {
            return "Please enter a number after delete";
        }
        try {
            int index = Integer.parseInt(args);
            if (index > tl.size()) {
                return "There are only " + tl.size() + " tasks";
            } else if (index == 0) {
                return "There is no task 0";
            }
            Task t = tl.getTask(index - 1);
            tl.removeTask(index - 1);
            return ui.formatMessage(tl.taskDeletedMessage(t));

        } catch (NumberFormatException e) {
            return "Please enter a number after delete";
        }
    }

    @Override
    public String getCommandPrefix() {
        return "delete";
    }
}
