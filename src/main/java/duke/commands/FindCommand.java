package duke.commands;

import duke.tasklist.TaskList;
import duke.tasks.Task;
import duke.ui.Ui;


public class FindCommand extends Command {

    String word;

    public FindCommand(String word) {
        this.word = word;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        TaskList list = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().contains(word)) {
                list.add(task);
            }
        }
        ui.displayFind(word, list);
    }
}
