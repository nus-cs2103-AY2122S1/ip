package duke.command;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.task.Task;
import duke.util.Message;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Handles the command for list.
 *
 * @author marcuspeh
 * @version A-Assertions
 * @since 6 Sep 2021
 */
public class ListCommand implements Command {
    /**
     * Adds a new task to the task list. IntStream is used to format the output nicely.
     *
     * @param taskList duke.main.TaskList to execute the command.
     * @param ui       To interact with the user.
     * @return message to be used by either the graphic UI or command line UI.
     */
    @Override
    public Message execute(TaskList taskList, Ui ui) {
        assert taskList != null : " Tasklist is required by command.";
        assert ui != null : " Ui is required by command.";

        List<Task> allTask = taskList.getTaskList();
        String[] task = IntStream.range(0, allTask.size())
                .mapToObj(x -> (x + 1) + ". " + allTask.get(x).toString())
                .collect(Collectors.toList())
                .toArray(new String[0]);
        return ui.listTask(task);
    }
}
