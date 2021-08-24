package duke.commands;

import duke.DateTimeHandler;
import duke.Task;
import duke.Storage;
import duke.TaskList;
import duke.UI;

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
    public void execute(TaskList tl, Storage s, UI ui, DateTimeHandler dth) {
        String args = super.getArguments();
        if (args.length() == 0) {
            ui.print("Please enter a number after done");
            return;
        }
        try {
            int index = Integer.parseInt(args);
            if (index > tl.size()) {
                ui.print("There are only " + tl.size() + " tasks");
                return;
            } else if (index == 0) {
                ui.print("There is no task 0");
                return;
            }
            Task t = tl.getTask(index - 1);
            t.completeTask();
            ui.print(tl.taskCompletedMessage(t));

        } catch (NumberFormatException e) {
            ui.print("Please enter a number after done");
        }
    }

    @Override
    public String startsWith() {
        return "done";
    }
}
