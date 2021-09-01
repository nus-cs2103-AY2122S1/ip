package duke.command;

import duke.exception.InvalidInputException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DoneCommand extends Command {

    private String input;

    public DoneCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the specified command.
     *
     * @param tasks The TaskList which we are modifying.
     * @param ui The Ui we will use for user interaction.
     * @param storage The Storage we will use for storing save data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] strings = input.split(" ");
        if (tasks.size() == 0) {
            ui.showEmptyList();
        } else {
            try {
                if (strings.length < 2) {
                    ui.textBox("You did not specify which task to complete.");
                } else {
                    int taskNumber = Integer.parseInt(strings[1]) - 1;
                    if (taskNumber < tasks.size()) {
                        if (tasks.get(taskNumber).checkCompletion()) {
                            ui.textBox("The task has already been completed, please be more attentive.");
                        } else {
                            tasks.get(taskNumber).complete();
                            ui.showComplete(tasks.get(taskNumber).toString());
                        }
                    } else {
                        System.out.println("You have entered an invalid task number. Fool.");
                    }
                }
            } catch (NumberFormatException ex) {
                System.err.println(ex);
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
