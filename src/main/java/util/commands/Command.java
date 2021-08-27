package util.commands;


import util.tasks.DukeException;


@FunctionalInterface
public interface Command {
    /**
     * The execution of a command.
     *
     * @throws DukeException When the command activity throws the exception.
     */
    public void execute() throws DukeException;
}
