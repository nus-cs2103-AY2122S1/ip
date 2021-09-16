package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Creates an AddDeadlineCommand to add deadlines to the task list.
 */
public class FindCommand extends Command {
    private String command;

    /**
     * Creates FindCommand object.
     *
     * @param command command given by user.
     */
    public FindCommand(String command) {
        assert command.startsWith("find");
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
        Task[] result = new Task[tasks.size()];
        String[] words = command.split(" ");
        if (words.length == 1) {
            throw new DukeException("invalidFindTask");
        }
        String piece = command.substring(5);
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            String name = tasks.get(i).getName();
            if (name.contains(piece)) {
                result[count] = tasks.get(i);
                count++;
            }
        }
        return Ui.printFindTask(result);
    }
}
