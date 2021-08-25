package commands;

import duke.*;
import tasks.*;
import exceptions.*;

/**
 * The Command interface models a command given by the user through
 * the user input.
 *
 * Each command has an execute method that acts on the duke.Duke program.
 * Each command also has a method which tells duke.Duke whether to quit, but
 * only the commands.QuitCommand will return true in general.
 */
public interface Command {
    void execute(Ui ui, TaskList taskList, Storage storage);

    boolean isQuit();
}
