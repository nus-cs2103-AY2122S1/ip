package duke.commands;

import duke.gui.Ui;
import duke.storage.Storage;
import duke.tasks.Deadline;

/**
 * Class to handle the deadline command.
 */
public class DeadlineCommand extends Command {
    private final Deadline deadline;

    /**
     * Constructor for a new DeadlineCommand instance with the specified deadline task stored.
     *
     * @param deadline The deadline task.
     */
    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    /**
     * Add the task to the list, print out reply, and save the change to file.
     *
     * @param ui The Ui instance for printing.
     * @param storage The Storage instance to add the task to.
     * @return String to represent the reply of Duke.
     */
    @Override
    public String execute(Ui ui, Storage storage) {
        storage.addToList(deadline);
        storage.save();
        String dukeReply = String.format("Got it! I've added this deadline to the list: \n"
                + "%s\nNow you have %d tasks in the list",
                deadline.toString(), storage.getSize());
        return ui.reply(dukeReply);
    }

}
