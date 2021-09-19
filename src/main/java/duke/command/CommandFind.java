package duke.command;

import duke.Task;
import duke.TaskList;
import duke.ui.UserInterface;

public class CommandFind extends Command {

    private String keyword;

    public CommandFind(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui) {
        TaskList matchingTasks = new TaskList();
        for (Task task: taskList.getTasks()) {
            if (task.getName().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        ui.print(matchingTasks.toString());
    }
}
