package duke.command;

import duke.ArchiveList;
import duke.DukeList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Representation of remove command of Duke.
 */
public class RemoveCommand extends Command {

    private final int indexToRemove;

    private final boolean isRemoveAll;

    /**
     * Constructor for RemoveCommand.
     *
     * @param indexToRemove Index of TaskList to be removed.
     */
    public RemoveCommand(int indexToRemove) {
        this.indexToRemove = indexToRemove;
        this.isRemoveAll = indexToRemove == -1;
    }

    /**
     * Checks if this is an exit command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the RemoveCommand.
     *
     * @param taskList TaskList to remove command at index indexToRemove from.
     * @param archiveList ArchiveList to add the archived tasks to.
     * @param ui Ui to print to users of Duke.
     * @param storage Storage to save and load TaskList of Duke.
     */
    public void execute(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (this.isRemoveAll) {
            message = formatAndRemoveAll(taskList);

        } else {
            message = formatAndRemoveIndexToRemove(taskList);

        }
        ui.print(message);
    }

    /**
     * Gets the String representation of the things printed in the
     * execute method as well as execute the removal of the index.
     *
     * @param taskList TaskList to remove command at index indexToRemove from.
     * @param archiveList ArchiveList to add the archived tasks to.
     * @param ui Ui to get the String representation of the text printed.
     * @param storage Storage to save and load TaskList of Duke.
     */
    @Override
    public String formatExecutedString(TaskList taskList, ArchiveList archiveList, Ui ui, Storage storage) {
        String message;

        if (this.isRemoveAll) {
            message = formatAndRemoveAll(taskList);

        } else {
            message = formatAndRemoveIndexToRemove(taskList);

        }
        return message;
    }

    /**
     * Formats and removes all the items from dukeList.
     *
     * @param dukeList dukeList to remove all the tasks from.
     * @return Returns the formatted string to be printed to the user.
     */
    public String formatAndRemoveAll(DukeList dukeList) {
        String message = "Noted. I've removed these tasks from the " + dukeList.type() + ":\n";

        int counter = 0;
        int size = dukeList.getSize();

        for (int i = 0; i < size; i++) {
            Task toRemove = dukeList.remove(0);
            counter++;
            message += counter + "." + toRemove + "\n";
        }

        message += "Now you have 0 tasks in the " + dukeList.type() + ".";
        return message;
    }

    /**
     * Formats and removes task at indexToRemove from dukeList.
     *
     * @param dukeList dukeList to remove task at indexToRemove.
     * @return Returns the formatted string to be printed to the user.
     */
    public String formatAndRemoveIndexToRemove(DukeList dukeList) {
        Task toRemove = dukeList.remove(this.indexToRemove);

        return "Noted. I've removed this task from the " + dukeList.type() + ":\n" + toRemove + "\nNow you have "
                + dukeList.getSize() + " tasks in the " + dukeList.type() + ".";
    }

    /**
     * Gets isRemoveAll value which checks if the RemoveCommand
     * removes all the tasks.
     *
     * @return isRemoveAll value.
     */
    public boolean isRemoveAll() {
        return this.isRemoveAll;
    }

    /**
     * Gets the index to be removed.
     *
     * @return Index to be removed.
     */
    public int getIndexToRemove() {
        return this.indexToRemove;
    }
}

