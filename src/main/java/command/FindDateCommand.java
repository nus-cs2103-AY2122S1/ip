package command;

import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class FindDateCommand extends Command {
    private final LocalDate desiredDate;

    public FindDateCommand(LocalDate desiredDate) {
        super();
        this.desiredDate = desiredDate;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.findTasksByDate(desiredDate);
    }
}
