package util.commands;

import util.tasks.DukeException;

import java.util.ArrayList;

/**
 * The class representing a list of commands.
 *
 */
public class CommandList extends ArrayList<Command> {

    /**
     * Executing all the commands in the command list.
     * @throws DukeException
     */
    public void executeAll() throws DukeException {
        for (int i = 0; i < this.size(); i++) {
            this.get(i).execute();
        }
    }
}
