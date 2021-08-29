package duke.command;

import duke.ResponseLogic;
import duke.Storage;
import duke.task.TaskList;

public class DateCommand extends Command {

    private String date;

    public DateCommand(String date) {
        this.date = date;
    }

    @Override
    public String execute(TaskList tasks, ResponseLogic responseLogic, Storage storage) {
        return tasks.getTasksOnDate(this.date, responseLogic);
    }
}
