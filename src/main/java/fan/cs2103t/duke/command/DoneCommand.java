package fan.cs2103t.duke.command;

import static fan.cs2103t.duke.commons.Messages.MESSAGE_SUCCESSFULLY_DONE_FORMAT;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_TASK_ALREADY_DONE_FORMAT;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_TASK_NOT_FOUND_FORMAT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import fan.cs2103t.duke.task.Task;
import fan.cs2103t.duke.task.TaskList;
import fan.cs2103t.duke.ui.Ui;

/**
 * Represents a Duke's command which changes the completion status of tasks to "done".
 * <p>
 * This is a subclass of the <code>Command</code> class.
 */
public class DoneCommand extends Command {

    private final ArrayList<Integer> indicesOfTasks;

    /**
     * Constructs a done command with the specified list of indices of all the tasks to be marked as done.
     *
     * @param indicesOfTasks the list of indices of all the tasks to be marked as done.
     */
    public DoneCommand(ArrayList<Integer> indicesOfTasks) {
        assert indicesOfTasks.size() >= 1;
        this.indicesOfTasks = indicesOfTasks;
    }

    /**
     * Executes this command. Changes the completion status of tasks with index
     * inside <code>indicesOfTasks</code> to "done".
     * Displays a message to the user through the specified UI to show the execution status for each task:
     * if the status of the task is successfully changed,
     * or the task has already been marked as done, or the task with an index does not exist.
     * Returns the status message as a string.
     *
     * @param taskList the task list that tasks to be marked as done are in.
     * @param ui the UI for the message to be displayed through.
     * @return a message to indicate the status of execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        processIndices(); // remove all duplicate indices and sort by value
        StringBuilder output = new StringBuilder();
        for (Integer taskIndex : indicesOfTasks) {
            try {
                Task t = taskList.getTasks().get(taskIndex - 1);
                if (t.getIsDone()) {
                    output.append(String.format(MESSAGE_TASK_ALREADY_DONE_FORMAT, taskIndex));
                } else {
                    t.markAsDone();
                    output.append(String.format(MESSAGE_SUCCESSFULLY_DONE_FORMAT,
                            taskIndex, t.getDescriptionWithStatus()));
                }
            } catch (IndexOutOfBoundsException ex) {
                output.append(String.format(MESSAGE_TASK_NOT_FOUND_FORMAT, taskIndex));
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
