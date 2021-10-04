package seedu.duke.commands;

import java.util.ArrayList;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.tasks.Task;
import seedu.duke.timetable.Timetable;

public class FindCommand extends Command {
    private final String word;

    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Finds an {@code ArrayList<Task>} which matches the user description when this
     * command is executed.
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
        ArrayList<Task> foundList = taskList.find(this.word);
        return Ui.printList(foundList, Ui.FIND_ZERO_SIZE, Ui.FIND_LIST_MESSAGE);
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
