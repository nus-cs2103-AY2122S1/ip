package duke.command;

import duke.exception.InvalidInputException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {

    private String input;

    public DeleteCommand(String input) {
        this.input = input;
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
        String[] strings = input.split(" ");
        if (tasks.size() == 0) {
            return ui.showEmptyList();
        } else {
            try {
                if (strings.length < 2) {
                    return ui.textBox("You did not specify which task to delete. Please wake up.");
                } else {
                    int taskIndex = Integer.parseInt(strings[1]) - 1;
                    if (taskIndex < tasks.size()) {
                        String list = "\n\n";
                        for (int j = 0; j < tasks.size(); j++) {
                            if (j < taskIndex) {
                                list += (j + 1) + ". " + tasks.get(j).toString() + "\n";
                            } else if (j == taskIndex) {

                            } else {
                                list += (j) + ". " + tasks.get(j).toString() + "\n";
                            }
                        }
                        return tasks.delete(taskIndex) + list;
                    } else {
                        return ui.textBox("You have entered an invalid task number. Fool.");
                    }
                }
            } catch (NumberFormatException ex) {
                System.err.println(ex);
            }
        }
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
