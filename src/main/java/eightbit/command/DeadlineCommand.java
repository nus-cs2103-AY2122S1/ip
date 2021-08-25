package eightbit.command;

import eightbit.task.Deadline;
import eightbit.util.Storage;
import eightbit.util.TaskList;
import eightbit.util.Ui;

public class DeadlineCommand extends Command {

    private final Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(deadline);
        storage.appendToFile(deadline);
        ui.printWithLines("Got it. I've added this task:\n  " + deadline
                + "\nNow you have " + taskList.size() + " tasks in the list.");
    }
}
