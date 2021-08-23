package duke.command;

import duke.FileController;
import duke.UI;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command{

    private String toFind;

    public FindCommand(String toFind) {
        this.toFind = toFind;
    }

    public void execute(TaskList tasks, UI ui, FileController fc) {
        StringBuilder sb = new StringBuilder();
        int size = tasks.size();
        int count = 0;
        for (int i = 0; i < size; i++) {
            Task task = tasks.get(i);
            String name = task.getName();
            if (name.contains(toFind)) {
                sb.append("\n")
                        .append(task.toString())
                        .append(String.format(". ID in list: %d", i));
                count++;
            }
        }
        sb.insert(0, String.format("Found %d entries", count));
        ui.printText(sb.toString());

    }
}
