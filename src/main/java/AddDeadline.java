package main.java;

import java.io.IOException;
import java.time.DateTimeException;

/**
 * AddDeadline is a command which adds a deadline task to the task list.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class AddDeadline extends Command {

    /**
     * Constructor.
     *
     * @param description it should contain the deadline description and the date
     */
    AddDeadline(String description) {
        super(description);
    }

    /**
     * Adds the deadline into the task list.
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage for the saved task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String[] deadlinePair = description.split("/by", 2);
            if (deadlinePair[0] == "" || deadlinePair[1] == "") {
                throw new DukeException("Your add deadline command is incomplete.");
            }
            tasks.addTask(new Deadline(false, deadlinePair[0], deadlinePair[1]));
            storage.add("D", deadlinePair[0], deadlinePair[1]);
        } catch (IOException e) {
            throw new DukeException("There is an error in adding the Deadline task to your saved data.");
        } catch (DateTimeException e) {
            throw new DukeException("Please provide the time in YYYY-MM-DD HHMM format instead.");
        }
    }
}
