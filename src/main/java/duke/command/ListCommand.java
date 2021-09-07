package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command that outputs a list of items that are in the user's task list.
 */
public class ListCommand implements Command {

    /**
     * Method executes a command that asks TaskList to print out a list of items in the Arraylist
     * and ui will print out a line if all the tasks have been completed in TaskList.
     *
     * @param taskList Tasklist that contains an Arraylist of agendas on the list.
     * @param ui Ui that outputs something based on the command given.
     * @param storage Storage that changes the list stored in data/duke.txt based on the command.
     * @return The list of items in your taskList.
     * @throws DukeException throws an exception that catches error in user input.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return taskList.printItems();
    }

    /**
     * A method that checks whether the current command will cause the program to exit or not.
     *
     * @return a boolean that prompts the program whether to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj instanceof ListCommand;
    }
}
