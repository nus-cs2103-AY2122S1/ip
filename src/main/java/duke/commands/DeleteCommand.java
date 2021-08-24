package duke.commands;

import duke.*;

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
    public void execute(TaskList tl, Storage s, UI ui, DateTimeHandler dth) {
        String args = super.getArguments();
        if (args.length() == 0) {
            ui.print("Please enter a number after delete");
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
            tl.removeTask(index - 1);
            ui.print(tl.taskDeletedMessage(t));

        } catch (NumberFormatException e) {
            ui.print("Please enter a number after delete");
        }
    }

    @Override
    public String startsWith() {
        return "delete";
    }
}
