package duke.commands;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Creates an AddDeadlineCommand to add deadlines to the task list.
 */
public class FindCommand extends Command {
    private String command;

    public FindCommand(String command) {
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
