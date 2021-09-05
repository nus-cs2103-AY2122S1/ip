package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ShowHistoryCommand extends Command {
    private String command;

    public ShowHistoryCommand(String command) {
        this.command = command;
    }

    /**
     * Executes the Command accordingly.
     *
     * @param storage Storage to store changes in text file.
     * @param tasks Tasks compiled in a TaskList.
     * @return A String array containing output.
     */
    public String[] execute(Storage storage, TaskList tasks) {
        try {
            int len = storage.historySize();
            String[] result = new String[len];
            for (int i = 0; i < len; i++) {
                result[i] = storage.getHistoryAt(i);
            }
            return Ui.printHistory(result);
        } catch (DukeException e) {
            return Ui.display(e.toString());
        }
    }
}

