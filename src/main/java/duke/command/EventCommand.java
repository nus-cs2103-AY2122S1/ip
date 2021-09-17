package duke.command;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.storage.Storage;
import duke.task.Event;
import duke.tasklist.TaskList;
import duke.utils.DukeException;

/**
 * EventCommand is a command which adds an event task to the task list.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S1
 */
public class EventCommand extends Command {

    /**
     * Constructor.
     *
     * @param description it should contain the event description and the date
     */
    public EventCommand(String description) {
        super(description);
    }

    /**
     * Adds the event into the task list.
     *
     * @param tasks   the task list
     * @param storage the storage for the saved task list
     * @throws DukeException if the date/time format is wrong or if the saved data is deleted midway /
     *                       the data file is missing by other factors
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        try {
            String[] info = DESCRIPTION.split("/at|/p", 3);
            if (info.length < 3) {
                throw new DukeException("Your add event command is incomplete.");
            }
            String desc = info[0].trim();
            String date = info[1].trim();
            String priority = info[2].trim();
            if (desc.equals("") || date.equals("") || priority.equals("")) {
                throw new DukeException("Your add event command is incomplete.");
            }
            int priorityInt = Integer.parseInt(priority);
            if (priorityInt < 1 || priorityInt > 3) {
                throw new DukeException("Duke only allows priority of 1, 2 and 3!");
            }
            LocalDateTime.parse(date.replace(" ", ""),
                    DateTimeFormatter.ofPattern("yyyy-MM-ddHHmm"));
            storage.add("E", desc, date, priorityInt);
            return tasks.addTask(new Event(false, desc, date, priorityInt));
        } catch (IOException e) {
            throw new DukeException("There is an error in adding the Event task to your saved data.");
        } catch (DateTimeException e) {
            throw new DukeException("Please provide the time in YYYY-MM-DD HHMM format instead.");
        }
    }
}
