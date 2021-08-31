package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a ByeCommand to exit the Duke program.
 */
public class ByeCommand extends Command {
    /**
     * Saves tasks in storage file, shows exit messages to user and closes Ui.
     * 
     * @param tasks TaskList containing tasks to be saved to storage file.
     * @param ui Ui that displays messages to users.
     * @param storage Storage that is used to save tasks.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveTasks(tasks);
        ui.showBye();
        ui.close();
    }

    /**
     * Returns true to show that the Duke chatbot should be exited.
     * 
     * @return True.
     */
    @Override
    public boolean isExit() { 
        return true; 
    }

    /**
     * Returns true if object is a ByeCommand.
     * 
     * @param obj Object to be compared to ByeCommand.
     * @return True if object is a ByeCommand.
     */
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof ByeCommand);
    }
}
