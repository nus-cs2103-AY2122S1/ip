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

    /**
     * Executes the command of finding tasks on the given task list that take place on the given date.
     *
     * @param ui Ui not used in this execution.
     * @param taskList The task list from which tasks taking place on the given date are taken to be listed.
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        taskList.findTasks(desiredDate);
    }
}
