package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;


public class DoneCommand implements Command {
    private String[] inputs;

    public DoneCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /** Marks the task as done */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        if (inputs.length != 2) {
            return "Wrong input format";
        }
        String index = inputs[1];

        return doTask(index, taskList, storage);
    }

    /**
     * Returns the message that shows which task is marked as completed.
     * Mark the task indicated by index as completed.
     * @param index the index of task to be marked
     * @param taskList the taskList containing all tasks
     * @return the messages
     */
    static String doTask(String index, TaskList taskList, Storage storage) {
        int idx;
        ArrayList<Task> tasks = taskList.getTasks();

        try {
            idx = Integer.parseInt(index);
            tasks.get(idx - 1);
        } catch (NumberFormatException nfe) {
            return "Please check the format of the index.";
        } catch (IndexOutOfBoundsException e) {
            return "The task does not exist in task list.";
        }


        StringBuilder s = new StringBuilder();
        s.append("    Nice! I've marked this task as done: \n");
        tasks.get(idx - 1).setDone();
        s.append("       " + tasks.get(idx - 1).toString() + "\n");

        try {
            storage.saveData(taskList);
        } catch (DukeException e) {
            return e.getMessage();
        }

        return s.toString();
    }
}
