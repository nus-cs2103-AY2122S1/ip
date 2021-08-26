package duke.commands;

import duke.Ui;
import duke.storage.Storage;
import duke.tasks.Deadline;

public class DeadlineCommand extends Command {
    Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean execute(Ui ui, Storage storage) {
        storage.addToList(deadline);
        ui.print("Got it! I've added this deadline to the list: \n"
                + "  " + deadline.toString() + '\n'
                + String.format("Now you have %d tasks in the list", storage.getSize()));
        storage.save();
        return false;
    }

}
