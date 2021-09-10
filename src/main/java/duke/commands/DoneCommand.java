package duke.commands;

import duke.DateTimeHandler;
import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * Encapsulates the done command, used to complete tasks
 */
public class DoneCommand extends Command {

    public DoneCommand(String arguments) {
        super(arguments);
    }

    @Override
    public Command of(String arguments) {
        return new DoneCommand(arguments);
    }

    @Override
    public String execute(TaskList tl, Storage s, Ui ui, DateTimeHandler dth) throws DukeException {
        String args = super.getArguments();
        if (args.length() == 0) {
            throw new DukeException("Please enter a number after done");
        }
        try {
            int index = Integer.parseInt(args);
            if (index > tl.size()) {
                throw new DukeException("There are only " + tl.size() + " tasks");
            } else if (index == 0) {
                throw new DukeException("There is no task 0");
            }
            Task t = tl.getTask(index - 1);
            t.completeTask();
            return ui.formatMessage(tl.taskCompletedMessage(t));

        } catch (NumberFormatException e) {
            throw new DukeException("Please enter a number after done");
        }
    }

    @Override
    public String getCommandPrefix() {
        return "done";
    }
}
