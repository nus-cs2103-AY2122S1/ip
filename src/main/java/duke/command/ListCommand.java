package duke.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.Task;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command for list.
 *
 * @author marcuspeh
 * @version A-JavaDoc
 * @since 23 Aug 2021
 */
public class ListCommand implements Command {
    /**
     * Adds a new task to the task list.
     *
     * @param taskList duke.main.TaskList to execute the command.
     * @param ui       To interact with the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        List<Task> allTask = taskList.getTaskList();
        String[] task = IntStream.range(0, allTask.size())
                .mapToObj(x -> (x + 1) + ". " + allTask.get(x).toString())
                .collect(Collectors.toList())
                .toArray(new String[0]);
        ui.listTask(task);
    }
}
