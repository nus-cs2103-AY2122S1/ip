package duke.command;

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
     *  @param tasks The TaskList which we are modifying.
     * @param ui The Ui we will use for user interaction.
     * @param storage The Storage we will use for storing save data.
     * @return
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String[] strings = input.split(" ");
        if (tasks.size() == 0) {
            return ui.showEmptyList();
        } else {
            try {
                if (strings.length < 2) {
                    return ui.textBox("You did not specify which task to complete.");
                } else {
                    int taskNumber = Integer.parseInt(strings[1]) - 1;
                    if (taskNumber < tasks.size()) {
                        if (tasks.get(taskNumber).checkCompletion()) {
                            ui.textBox("The task has already been completed, please be more attentive.");
                        } else {
                            String list = "\n\n";
                            for (int j = 0; j < tasks.size(); j++) {
                                if (j == taskNumber) {
                                    tasks.get(j).complete();
                                }
                                list += (j + 1) + ". " + tasks.get(j).toString() + "\n";
                            }
                            tasks.get(taskNumber).complete();
                            return ui.showComplete(tasks.get(taskNumber).toString()) + list;
                        }
                    } else {
                        return "You have entered an invalid task number. Fool.";
                    }
                }
            } catch (NumberFormatException ex) {
                return ex.getMessage();
            }
        }
        return null;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
