package duke.commands;

import duke.exceptions.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * This is an abstract Command class.
 */
public abstract class Command {

    /**
     * These are class fields of a Command instance.
     */
    protected final String command;
    protected final Boolean isExit;
    protected final String description;
    protected int index;

    /**
     * This is the constructor for a Command instance.
     *
     * @param command A String representing the type of command created.
     */
    protected Command(String command) {
        this.command = command;
        this.description = "";
        if (command.equals("bye")) {
            this.isExit = true;
        } else {
            this.isExit = false;
        }
    }

    /**
     * This is the overloaded constructor for Command instance.
     *
     * @param command A String representing the type of command created.
     * @param description A String representing the details of the command.
     */
    protected Command(String command, String description) {
        this.command = command;
        this.isExit = false;
        this.description = description;
    }


    /**
     * This is an overloaded constructor for Command instance.
     *
     * @param command A String representing the type of command.
     * @param index  An int representing an index of the task list.
     */
    protected Command(String command, int index) {
        this.command = command;
        this.description = "";
        this.isExit = false;
        this.index = index;
    }

    /**
     * This is an abstract method that runs the action as specified by the command.
     *
     * @param taskList  A TaskList instance that may store or remove task (if any) by command.
     * @param store  A Storage instance that records the task list after executing the command.
     * @param ui  An Ui instance that prints the message generated from the action specified by the command.
     * @return A String representing the message of the action.
     * @throws DukeException An Exception thrown if Duke gets an Error
     */
    public abstract String execute(TaskList taskList, Storage store, Ui ui)
            throws DukeException;

    /**
     * Returns a boolean indicating if user is exiting Duke.
     *
     * @return A boolean representing if the user is exiting Duke.
     */
    public boolean isExit() {
        return this.isExit;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Command)) {
            return false;
        } else {
            Command other = (Command) o;
            return this.command.equals(other.command)
                    && this.description.equals(other.description)
                    && (this.index == other.index);
        }
    }
}
