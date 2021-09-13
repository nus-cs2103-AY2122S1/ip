package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.timetable.Timetable;

public abstract class Command {
    protected static final String DATA_STORAGE_ISDONE_FALSE = " | 0 | ";

    /**
     * Executes the command.
     * 
     * @param taskList  contains an {@code ArrayList<Task>} where all {@code Task}
     *                  is stored.
     * @param timetable it contains the entire schedule of the
     *                  {@code ScheduledTask}.
     * @param storage   the database where the Tasks are being saved for
     *                  progression.
     * @return a reply message or information from {@code Duke}.
     */
    public abstract String execute(TaskList taskList, Timetable timetable, Storage storage);

    /**
     * Checks if the user wants to exit from the application.
     * 
     * @return a boolean whether the user wants to exit the application.
     */
    public abstract boolean isExit();
}
