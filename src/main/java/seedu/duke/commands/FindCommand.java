package seedu.duke.commands;

import java.util.ArrayList;

import seedu.duke.storage.Storage;
import seedu.duke.storage.TaskList;
import seedu.duke.tasks.Task;

public class FindCommand extends Command {
    private final String find;

    public FindCommand(String find) {
        this.find = find;
    }

    /**
     * Helps to find a list of Tasks which matches the user description when this
     * command is executed.
     * 
     * @param taskList the list of Tasks which is being stored.
     * @param storage  the database where the Tasks are being saved for progression.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) {
        ArrayList<Task> foundList = taskList.find(this.find);
        return Ui.printList(foundList, Ui.FIND_ZERO_SIZE, Ui.FIND_LIST_MESSAGE);
    }

    /**
     * Checks if the user wants to exit from the application.
     * 
     * @return boolean whether the user wants to exit from the application.
     */
    @Override
    public boolean getIsExit() {
        return false;
    }
}
