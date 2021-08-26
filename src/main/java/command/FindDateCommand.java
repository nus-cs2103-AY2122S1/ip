package command;

import duke.TaskList;

import java.time.LocalDate;

public class FindDateCommand extends Command {
    private final LocalDate desiredDate;

    public FindDateCommand(LocalDate desiredDate) {
        super();
        this.desiredDate = desiredDate;
    }

    /**
     * Executes the command of finding tasks on the given task list that take place on the given date.
     *
     * @param taskList The task list from which tasks taking place on the given date are taken to be listed.
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.findTasksByDate(desiredDate);
    }
}
