package duke.command;


import java.time.LocalDate;

import duke.Ui;
import duke.task.TaskList;

public class FindByDateCommand extends Command {

    private LocalDate date;
    private TaskList tasks;
    private Ui ui;


    /**
     * Instantiates command to find task by date.
     *
     * @param date Date to be searched.
     * @param tasks Task list by which task is to be searched from.
     * @param ui Ui to handle interactions.
     */
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
