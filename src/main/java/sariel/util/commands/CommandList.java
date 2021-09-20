package sariel.util.commands;

import java.util.ArrayList;

import sariel.util.tasks.DukeException;



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
    public String executeAll() throws DukeException {
        String output = "";
        for (int i = 0; i < this.size(); i++) {
            output += this.get(i).execute();

        }
        return output;
    }
}
