package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command {

    private final String tab = "      ";
    private final String line = "------------------------------------------------------------";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            ui.showEmptyList();
        } else {
            System.out.println(tab + line);
            System.out.println(tab + "Here are the tasks in your list:");
            for (int j = 0; j < tasks.size(); j++) {
                System.out.println(tab + " " + (j + 1) + ". " + tasks.get(j).toString());
            }
            System.out.println(tab + line);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
