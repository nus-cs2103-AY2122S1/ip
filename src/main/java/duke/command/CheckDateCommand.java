package duke.command;

import java.time.LocalDate;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Type of Command that prints a list of tasks that match the date stated.
 */
public class CheckDateCommand extends Command {
    private LocalDate dateToCheck;

    /**
     * Constructor.
     *
     * @param dateToCheck the date to compare with all tasks in task list.
     */
    public CheckDateCommand(LocalDate dateToCheck) {
        this.dateToCheck = dateToCheck;
    }

    /**
     * Executes a series of operations to get a list of tasks.
     *  @param taskList
     * @param ui
     * @param storage
     * @param archive
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage, Archive archive) {
        return ui.checkDate(taskList, dateToCheck);
    }

    /**
     * Not Exit Command
     *
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
