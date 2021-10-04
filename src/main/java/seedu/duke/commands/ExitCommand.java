package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.timetable.Timetable;

public class ExitCommand extends Command {

    /**
     * Replies with an Exit message when this function is triggered.
     * 
     * @param taskList  contains an {@code ArrayList<Task>} where all {@code Task}
     *                  is stored.
     * @param timetable it contains the entire schedule of the
     *                  {@code ScheduledTask}.
     * @param storage   the database where the Tasks are being saved for
     *                  progression.
     * @return a reply message or information from {@code Duke}.
     */
    @Override
    public String execute(TaskList taskList, Timetable timetable, Storage storage) {
        return Ui.printMessage(Ui.BYE_MESSAGE);
    }

    /**
     * Checks if the user wants to exit from the application.
     * 
     * @return {@code true} as this command is ready for user to exit the
     *         application.
     */
    @Override
    public boolean isExit() {
        return true;
    };
}
