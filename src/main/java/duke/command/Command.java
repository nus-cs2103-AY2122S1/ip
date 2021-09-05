package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A generic interface that gives the backbone of the commands that user can input to the
 * program.
 */
public interface Command {

    /**
     * Executes the current command and activates the Tasklist, Ui and Storage objects to
     * perform certain actions based on the command given.
     *
     * @param taskList Tasklist that contains an Arraylist of agendas on the list.
     * @param ui Ui that outputs something based on the command given.
     * @param storage Storage that changes the list stored in data/duke.txt based on the command.
     * @throws DukeException Deals with any errors that rises due to the user's input.
     */
    String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * A method that checks whether the current command will cause the program to exit or not.
     *
     * @return a boolean that prompts the program whether to exit.
     */
    boolean isExit();
}
