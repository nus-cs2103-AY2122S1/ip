package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command that creates a list of helpful commands so the user can understand more.
 */
public class HelpCommand implements Command {

    /**
     * Executes a command to Ui to print out a message containing helpful information for available
     * commands.
     *
     * @param taskList Tasklist that contains an Arraylist of agendas on the list.
     * @param ui Ui that outputs something based on the command given.
     * @param storage Storage that changes the list stored in data/duke.txt based on the command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.helpMessage();
    }

    /**
     * A method that checks whether the current command will cause the program to exit or not.
     *
     * @return a boolean that prompts the program whether to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj instanceof HelpCommand;
    }
}
