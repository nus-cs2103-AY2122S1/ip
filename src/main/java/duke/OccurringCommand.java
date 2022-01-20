package duke;

import java.time.LocalDate;

/**
 * Represents occurring command that shows all tasks that occurs on a given date.
 */
public class OccurringCommand extends Command {
    private LocalDate queryDate;

    /**
     * Constructor for OccurringCommand.
     *
     * @param queryDate Date to query.
     */
    public OccurringCommand(LocalDate queryDate) {
        this.queryDate = queryDate;
    }

    /**
     * Shows all tasks that occurs on a given date.
     * @param tasks Task list component.
     * @param storage Storage component.
     * @param ui UI component.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        ui.showOccurringTasks(queryDate, tasks);
        storage.addToHistory(tasks, this);
    }

    @Override
    public String toString() {
        return "Show tasks occurring on date Command";
    }
}
