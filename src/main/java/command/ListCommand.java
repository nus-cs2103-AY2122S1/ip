package command;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;

public class ListCommand extends Command {

        private final boolean EXIT = false;

        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ArrayList<Task> taskList = tasks.getTaskList();
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("\t%d.%s%n", (i + 1), taskList.get(i));
            }
        }

        public boolean isExit() {
            return EXIT;
        }
}
