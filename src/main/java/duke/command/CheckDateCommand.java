package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class CheckDateCommand extends Command {
    LocalDate dateToCheck;

    public CheckDateCommand(LocalDate dateToCheck) {
        this.dateToCheck = dateToCheck;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.checkDate(taskList, dateToCheck);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
