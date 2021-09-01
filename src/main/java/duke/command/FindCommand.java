package duke.command;

import duke.exception.InvalidInputException;
import duke.task.Task;
import duke.task.ToDo;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import javax.sound.midi.SysexMessage;

public class FindCommand extends Command {

    private String keyword;
    private final String tab = "      ";
    private final String line = "------------------------------------------------------------";

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException {
        if (tasks.size() == 0) {
            ui.showEmptyList();
        } else {
            System.out.println(tab + line);
            System.out.println(tab + "Your keyword is: \"" + keyword + "\". \n");
            System.out.println(tab + "Here are the matching tasks in your list:");
            for (int j = 0; j < tasks.size(); j++) {
                if (tasks.get(j).getDescription().contains(keyword)) {
                    System.out.println(tab + " " + (j + 1) + ". " + tasks.get(j).toString());
                }
            }
            System.out.println(tab + line);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
