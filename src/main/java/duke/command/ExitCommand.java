package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command that tells the program to exit.
 */
public class ExitCommand implements Command {

    /**
     * Executes a command to the Ui side to output a goodbye message and closes the program.
     *
     * @param taskList Tasklist that contains an Arraylist of agendas on the list.
     * @param ui Ui that outputs something based on the command given.
     * @param storage Storage that changes the list stored in data/duke.txt based on the command.
     * @return A message that prints goodbye to the user.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.byeMessage();
    }

    /**
     * A method that causes the program to exit since this is ExitCommand.
     *
     * @return a boolean that prompts the program whether to exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj instanceof ExitCommand;
    }
}
