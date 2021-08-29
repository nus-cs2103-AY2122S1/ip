package duke.command;


import java.time.LocalDate;

import duke.Ui;
import duke.task.TaskList;

public class FindByDateCommand extends Command {

    private LocalDate date;
    private TaskList tasks;
    private Ui ui;

    public FindByDateCommand(LocalDate date, TaskList tasks, Ui ui) {
        this.date = date;
        this.ui = ui;
        this.tasks = tasks;
    }

    @Override
    public void execute() {
        TaskList foundTasks = tasks.findByDate(this.date);
        ui.findByDate(foundTasks);
    }

}
