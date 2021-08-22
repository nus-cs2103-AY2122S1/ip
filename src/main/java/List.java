package main.java;

import java.time.DateTimeException;

/**
 * List is a command which prints out all the Tasks in the list, and even
 * prints only tasks of a specific date if the date is specified.
 *
 * @author Zhen Xuan (Tutorial Group W12)
 * @version CS2103T AY21/22 S2
 */
public class List extends Command {

    /**
     * Constructor.
     *
     * @param description it is either empty, or contains a date for which tasks with that date should be returned.
     */
    List(String description) {
        super(description);
    }

    /**
     * Lists down all the tasks (of the specified date).
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage for the saved task list
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String date = super.description.replace(" ", "");
        try {
            switch (date) {
                case "":
                    tasks.printList();
                    break;
                default:
                    tasks.printListDate(date);
                    break;
            }
        } catch (DateTimeException e) {
            throw new DukeException("Your date (YYYY-MM-DD) / date & time (YYYY-MM-DD HHMM) (24h) is given in the wrong format!");
        }
    }
}
