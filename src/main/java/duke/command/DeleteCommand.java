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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        String[] strings = input.split(" ");
        if (tasks.size() == 0) {
            ui.showEmptyList();
        } else {
            try {
                if (strings.length < 2) {
                    ui.textBox("You did not specify which task to delete. Please wake up.");
                } else {
                    int taskIndex = Integer.parseInt(strings[1]) - 1;
                    if (taskIndex < tasks.size()) {
                        ui.showDelete(tasks.get(taskIndex).toString());
                        tasks.delete(taskIndex);
                    } else {
                        ui.textBox("You have entered an invalid task number. Fool.");
                    }
                }
            } catch (NumberFormatException ex) {
                System.err.println(ex);
            }
        }
        Command listCommand = new ListCommand();
        listCommand.execute(tasks, ui, storage);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
