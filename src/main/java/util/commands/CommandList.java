package util.commands;

import java.util.ArrayList;

import util.tasks.DukeException;



/**
 * The class representing a list of commands.
 *
 */
public class CommandList extends ArrayList<Command> {

    /**
     * Executing all the commands in the command list.
     *
     * @throws DukeException When a class in the list has the exception.
     */
    public void executeAll() throws DukeException {
        for (int i = 0; i < this.size(); i++) {
            this.get(i).execute();
        }
    }
}
