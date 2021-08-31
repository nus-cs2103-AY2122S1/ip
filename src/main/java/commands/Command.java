package commands;

import viper.Storage;
import viper.TaskList;
import viper.Ui;

import java.io.IOException;

/**
 * Represents user input
 */
public abstract class Command {
    /**
     * Creates tasks according to user input
     * @param tasks existing tasks in the tasklist
     * @param ui user input format
     * @param storage stores created command into the txt file
     * @throws IOException if is no user input
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Represents the end of program
     * @return true if program should exit, otherwise do not exit program
     */
    public abstract boolean isExit();

}
