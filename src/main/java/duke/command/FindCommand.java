package duke.command;

import duke.exception.InvalidInputException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the specified command.
     *
     * @param tasks The TaskList which we are modifying.
     * @param ui The Ui we will use for user interaction.
     * @param storage The Storage we will use for storing save data.
     * @throws InvalidInputException When the input is deemed invalid.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (tasks.size() == 0) {
            return ui.showEmptyList();
        } else {
            String output = "";
            output += "Your keyword is: \"" + keyword + "\". \nHere are the matching tasks in your list:\n";
            for (int j = 0; j < tasks.size(); j++) {
                if (tasks.get(j).getDescription().contains(keyword)) {
                    output += (j + 1) + ". " + tasks.get(j).toString() + "\n";
                }
            }
            return output.equals("")
                    ? "There are no tasks matching that keyword. Try another keyword."
                    : output;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
