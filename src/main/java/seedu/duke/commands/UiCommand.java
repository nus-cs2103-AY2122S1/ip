package seedu.duke.commands;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.timetable.Timetable;

public class UiCommand extends Command {
    private final String uiMsg;

    /**
     * Primary Constructor.
     * 
     * @param uiMsg is the message that the Ui will print for the user to read.
     */
    public UiCommand(String uiMsg) {
        this.uiMsg = uiMsg;
    }

    /**
     * Prints UI message for the user when command is executed.
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
        return Ui.printMessage(this.uiMsg);
    }

    /**
     * Checks if the user wants to exit from the application.
     * 
     * @return {@code false} as this command is not ready for user to exit the
     *         application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
