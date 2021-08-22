package util.commands;

import util.tasks.DukeException;

import java.util.ArrayList;


public class CommandList extends ArrayList<Command> {

    public void executeAll() throws DukeException {
        for (int i = 0; i < this.size(); i++) {
            this.get(i).execute();
        }
    }
}
