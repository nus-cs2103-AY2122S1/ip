package duke.command;

import duke.ResponseFormatter;
import duke.Storage;
import duke.TaskList;
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
     *
     *  @param taskList current task list
     * @param ui current ui
     * @param storage current storage
     *
     * @return
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printBye();
    }

    /**
     * Execute ByeCommand, returns a goodbye message from duke
     *
     * @param taskList current task list
     * @param rf Response formatter
     * @param storage current storage
     * @return
     */
    @Override
    public String execute(TaskList taskList, ResponseFormatter rf, Storage storage) {
        return rf.formatBye();
    }
}
