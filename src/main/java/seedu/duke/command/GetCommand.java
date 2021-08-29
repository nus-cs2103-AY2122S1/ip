package seedu.duke.command;

import seedu.duke.DateTimeManager;
import seedu.duke.DukeException;
import seedu.duke.Storage;
import seedu.duke.Ui;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class GetCommand extends Command {
    private static final String GET_MESSAGE = "These are the tasks happening on that date:\n";
    private LocalDate date;
    private HashMap<LocalDate, ArrayList<Task>> dateTasks;

    public GetCommand(Ui ui, TaskList taskList, LocalDate date,
                      HashMap<LocalDate, ArrayList<Task>> dateTasks) {
        super(ui, taskList);
        this.date = date;
        this.dateTasks = dateTasks;
    }

    @Override
    public String getUsageMessage() {
        return "get dd/MM/yyyy | get tasks on given date";
    }

    /**
     * Returns tasks happening or due on the given date.
     */
    @Override
    public void execute() throws DukeException {
        ui.divide();
        ui.outputMessage(dateTasks.getOrDefault(date, new ArrayList<>())
                .toString());
        ui.divide();
    }

}

