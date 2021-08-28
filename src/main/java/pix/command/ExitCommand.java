package pix.command;

import java.io.IOException;

import pix.exception.PixIOException;
import pix.storage.Storage;
import pix.task.TaskList;
import pix.ui.Ui;

public class ExitCommand extends Command {
    /**
     * Constructor for the Exit Command.
     */
    public ExitCommand() {
        super(true);
    }

    /**
     * Triggers the Exit Command, printing the exit message to the Ui.
     *
     * @param storage Storage class to store the data in.
     * @param taskList Task list class that has the task list to write from.
     * @param ui Ui class to display the exit message
     *
     * @return Returns the message to display.
     */
    @Override
    public String trigger(Storage storage, TaskList taskList, Ui ui) {
        try {
            storage.writeToFile(taskList.getTaskList());
        } catch (IOException | PixIOException e) {
            //The format should be set and there shouldn't be any I/O error.
        }
        return "Please don't come back...";
    }
}
