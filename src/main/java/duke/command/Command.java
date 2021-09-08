package duke.command;

import java.util.List;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a user command.
 */
public abstract class Command {
    private final String line;

    /**
     * Constructs Command object.
     *
     * @param cmd task command.
     */
    public Command(String cmd) {
        line = cmd;
    }

    /**
     * Executes command.
     *
     * @param tasks list of tasks within chat bot.
     * @param ui user interface of chat bot.
     * @param storage file directory manager.
     * @return chat bot response message.
     * @throws DukeException If errors occur within list.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int listSize = tasks.getSize();
        List<Task> listOfTask = tasks.getTasks();

        String[] saveList = new String[listSize];
        for (int i = 0; i < listSize; i++) {
            saveList[i] = listOfTask.get(i).save();
        }

        storage.save(saveList);
        return "";
    }

    public String getLine() {
        return line;
    }

    /**
     * Returns index from command line.
     */
    public int getIndex() {
        return Integer.parseInt(line) - 1;
    }

    /**
     * Returns if command exits program.
     */
    public abstract boolean isExit();
}
