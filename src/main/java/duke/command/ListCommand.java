package duke.command;

import duke.date.Date;
import duke.exception.BadInputFormatException;
import duke.exception.InvalidDateException;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {
    private Date date;

    public ListCommand() {
        super();
    }

    private ListCommand(Date date) {
        this.date = date;
    }

    public static ListCommand of(String content) throws BadInputFormatException, InvalidDateException {
        if (content.trim().length() < 1) {
            return new ListCommand();
        }
        return new ListCommand(Date.of(content));
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        if (tasks.size() < 1) {
            ui.print("No tasks yet!");
            return;
        }
        if (date == null) {
            ui.print("Here are the tasks in your list:");
            ui.print(tasks.toStringArray());
            return;
        }
        ui.print("Here are the tasks happening on " + date + ":");
        ui.print(tasks.toStringArray(date));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
