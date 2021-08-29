package duke.commands;

import duke.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Finds the tasks containing a specific phrase.
 */
public class FindCommand extends Command {
    private String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList filteredList = new TaskList();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        for (Task task : tasks) {
            if (task.getDescription().contains(toFind)) {
                filteredList.add(task);
            }
        }
        System.out.println("Here are the tasks containing the word : " + toFind);
        System.out.println(arrayToString(filteredList));
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        storage.write(tasks);
    }
}
