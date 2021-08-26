package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class ByeCommand extends Command {
    public static final String COMMAND = "Bye!";

    /**
     * Constructor for ByeCommand
     * will mark isExit as true so duke can escape while loop
     *
     */
    public ByeCommand() {
        this.isExit = true;
    }

    /**
     * Execute ByeCommand, prints a goodbye message from duke
     * @param taskList current task list
     * @param ui current ui
     * @param storage current storage
     *
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
    }
}
