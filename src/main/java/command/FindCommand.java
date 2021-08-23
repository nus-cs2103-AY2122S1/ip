package command;

import duke.TaskList;
import duke.Ui;

import java.time.LocalDate;

public class FindCommand extends Command {
    private final LocalDate desiredDate;

    public FindCommand(LocalDate desiredDate) {
        super();
        this.desiredDate = desiredDate;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.findTasks(desiredDate);
    }
}
