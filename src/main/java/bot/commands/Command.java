package bot.commands;

import bot.error.DukeException;

public class Command {
    protected static final String INFORM_FORMAT = "Now you have %d tasks in the list.";
    protected static StringBuilder message;
    protected boolean canExit = false;
    public String execute() throws DukeException {
        throw new DukeException("This is not a valid Command");
    }
    public boolean canEnd() {
        return this.canExit;
    }
}
