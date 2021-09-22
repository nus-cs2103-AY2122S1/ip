package fan.cs2103t.duke.command;

import static fan.cs2103t.duke.commons.Messages.MESSAGE_SUCCESSFULLY_DELETED_FORMAT;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_TASK_NOT_FOUND_FORMAT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import fan.cs2103t.duke.task.Task;
import fan.cs2103t.duke.task.TaskList;
import fan.cs2103t.duke.ui.Ui;

/**
 * Represents a Duke's command which deletes tasks from Duke's task list upon execution.
 * <p>
 * This is a subclass of the <code>Command</code> class.
 */
public class DeleteCommand extends Command {

    private final ArrayList<Integer> indicesOfTasks;

    /**
     * Constructs a delete command with the specified list of indices of all the tasks
     * to be deleted from Duke's task list.
     *
     * @param indicesOfTasks the list of indices of all the tasks to be deleted.
     */
    public DeleteCommand(ArrayList<Integer> indicesOfTasks) {
        assert indicesOfTasks.size() >= 1;
        this.indicesOfTasks = indicesOfTasks;
    }

    /**
     * Executes this command. Deletes tasks with index inside <code>indicesOfTasks</code> from the specified task list.
     * Displays a message to the user through the specified UI to show the execution status for each task:
     * if the task is successfully deleted. The current program is exited immediately if any deletion fails.
     * Returns the status message as a string.
     *
     * @param taskList the task list for tasks to be deleted from.
     * @param ui the UI for the message to be displayed through.
     * @return a message to indicate the status of execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        processIndices(); // remove all duplicate indices and sort by value
        StringBuilder output = new StringBuilder();
        for (int i = indicesOfTasks.size() - 1; i >= 0; i--) {
            Integer taskIndex = indicesOfTasks.get(i);
            try {
                Task t = taskList.getTasks().get(taskIndex - 1);
                String description = t.getDescriptionWithStatus();
                if (taskList.deleteTask(taskIndex - 1)) {
                    output.insert(0, String.format(MESSAGE_SUCCESSFULLY_DELETED_FORMAT, taskIndex, description));
                } else {
                    System.exit(1);
                }
            } catch (IndexOutOfBoundsException ex) {
                output.insert(0, String.format(MESSAGE_TASK_NOT_FOUND_FORMAT, taskIndex));
            }
        }
        output.setLength(output.length() - 1);
        return output.toString();
    }

    private void processIndices() {
        HashSet<Integer> set = new HashSet<>(indicesOfTasks);
        indicesOfTasks.clear();
        indicesOfTasks.addAll(set);
        Collections.sort(indicesOfTasks);
    }

}
