package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showMessage(this.getMessage(tasks));
    }

    @Override
    public String getMessage(TaskList tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            stringBuilder.append(String.format("%d. %s\n",
                    i + 1,
                    tasks.getIndex(i).toString()));
        }
        return stringBuilder.toString();
    }
}
