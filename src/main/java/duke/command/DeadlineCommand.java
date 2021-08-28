package duke.command;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.tasklist.TaskList;

/**
 * DeadlineCommand is a command which adds a deadline task to the task list.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class DeadlineCommand extends Command {

    /**
     * Constructor.
     *
     * @param description it should contain the deadline description and the date
     */
    public DeadlineCommand(String description) {
        super(description);
    }

    /**
     * Adds the deadline into the task list.
     *
     * @param tasks   the task list
     * @param storage the storage for the saved task list
     * @throws DukeException if the date/time format is wrong or if the saved data is deleted midway /
     *                       the data file is missing by other factors
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            String[] deadlinePair = description.split("/by", 2);
            if (deadlinePair.length < 2 || deadlinePair[0].equals("") || deadlinePair[1].equals("")) {
                throw new DukeException("Your add deadline command is incomplete.");
            }
            String desc = deadlinePair[0].trim();
            String date = deadlinePair[1].trim();
            LocalDateTime.parse(date.replace(" ", ""),
                    DateTimeFormatter.ofPattern("yyyy-MM-ddHHmm"));
            storage.add("D", desc, date);
            return tasks.addTask(new Deadline(false, desc, date));
        } catch (IOException e) {
            throw new DukeException("There is an error in adding the Deadline task to your saved data.");
        } catch (DateTimeException e) {
            throw new DukeException("Please provide the time in YYYY-MM-DD HHMM format instead.");
        }
    }
}
