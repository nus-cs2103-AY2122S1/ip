package util.commands;


import util.tasks.DukeException;

@FunctionalInterface
public interface Command {
    public void execute() throws DukeException;
}
