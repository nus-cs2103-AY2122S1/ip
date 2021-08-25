package duke.command;


import duke.Ui;
import duke.task.TaskList;

import java.time.LocalDate;

public class FindByDateCommand extends Command {

    LocalDate date;
    TaskList tasks;
    Ui ui;

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
