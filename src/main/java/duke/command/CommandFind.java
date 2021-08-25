package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * The class that models a find command.
 */
public class CommandFind extends Command {
    private String keyword;

    public CommandFind(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Execute find action and then print result message.
     * @param tasks The taskList to search keywork in.
     * @param ui The Ui object to print messages.
     * @param storage The Storage object that auto-saves after modification.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList matched = tasks.find(keyword);
        if(matched.isEmpty()) {
            ui.printMsg("There are no matching tasks in your list.");
        } else {
            ui.printMsg("Here are the matching tasks in your list:");
            ui.printMsg(matched.toString());
        }
    }
}
