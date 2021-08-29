package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

/**
 * ListCommand will list out the task number, task description and date(if applicable) when executed.
 */
public class ListCommand extends Command {

        private final boolean EXIT = false;

        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ArrayList<Task> tasklist = tasks.getTaskList();
            for (int i = 0; i < tasklist.size(); i++) {
                System.out.printf("\t%d.%s%n", (i + 1), tasklist.get(i));
            }
        }

        public boolean isExit() {
            return EXIT;
        }
}
