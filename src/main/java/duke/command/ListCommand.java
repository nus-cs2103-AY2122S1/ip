package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder result = new StringBuilder();
        int currentIndex = 1;
        for (Task task : tasks.getTasks()) {
            if (task != null)
                result.append("\n\t ")
                        .append(currentIndex)
                        .append(".")
                        .append(task);
            currentIndex ++;
        }
        System.out.println("\tHere are the tasks in your list:" + result);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
