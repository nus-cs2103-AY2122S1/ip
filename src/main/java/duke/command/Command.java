package duke.command;

import duke.Storage;

import duke.task.TaskList;

/**
 * An abstract class that represents the various types of commands that can be entered by the user.
 */
public abstract class Command {
    private String command;

    /**
     * Constructor for the Command class where the user command is initialized.
     *
     * @param command Command entered by the user.
     */
    public Command(String command) {
        this.command = command;
    }

    /**
     * Abstract method which represents Duke's response to various types of user commands.
     *
     * @param taskList TaskList where the tasks are stored.
     */
    public abstract void execute(TaskList taskList, Storage storage);
}
