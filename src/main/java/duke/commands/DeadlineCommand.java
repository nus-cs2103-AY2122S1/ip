package duke.commands;

import duke.Ui;
import duke.storage.Storage;
import duke.tasks.Deadline;

/**
 * Class to handle the deadline command.
 */
public class DeadlineCommand extends Command {
    Deadline deadline;

    /**
     * Public constructor for the DeadlineCommand class
     *
     * @param deadline The deadline task.
     * @return A new DeadlineCommand instance with the specified deadline task stored.
     */
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Add the task to the list, print out reply, and save the change to file
     *
     * @param ui The Ui instance for printing
     * @param storage The Storage instance to add the task to
     * @return A boolean of false to indicate the main while loop should not be broken
     */
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
