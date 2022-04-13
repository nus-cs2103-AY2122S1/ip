package duke.command;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

/** The Command class responsible for getting tasks that occur on a certain date. */
public class DateCommand extends Command {

    private String date;

    /**
     * Initializes the query date.
     *
     * @param date The query date.
     */
    public DateCommand(String date) {
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) {
        return tasks.getTasksOnDate(this.date, responseLogic);
    }
}
