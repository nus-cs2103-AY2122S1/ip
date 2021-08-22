package util.commands;


import util.tasks.DukeException;


@FunctionalInterface
public interface Command {

    /**
     * The execution of a command.
     *
     * @throws DukeException
     */
    public void execute() throws DukeException;
}
