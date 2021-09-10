package duke.common;

import java.io.Serializable;
import java.util.ArrayList;

import duke.common.task.Task;

/**
 * TaskList stores and retrieves tasks created by Duke.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    /**
     * Default constructor for TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * General-purpose method that stores any task created by Duke.
     *
     * @param task the task to be stored
     */
    public String addCustom(Task task) {
        tasks.add(task);
        return "Got it. I've added this task: \n"
               + String.format("  %s\n", task)
               + String.format("Now you have %d %s in the list.", this.tasks.size(),
                                                                  this.tasks.size() == 1 ? "task" : "tasks");
    }

    /**
     * Retrieves all stored tasks and displays them.
     */
    public String list() {
        String result = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            result += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return result;
    }

    /**
     * Marks a task as done, represented in the future with a cross in the associated column.
     *
     * @param taskNumber position of task in the TaskList
     */
    public String done(int taskNumber) {
        tasks.get(taskNumber - 1).complete();
        return "Nice! I've marked this task as done: \n"
               + "  " + tasks.get(taskNumber - 1);
    }

    /**
     * Removes target task.
     *
     * @param taskNumber position of target task
     */
    public String delete(int taskNumber) {
        String result = "";
        if (taskNumber > this.tasks.size() || taskNumber <= 0) {
            return "Sorry, that task does not exist!\n";
        }
        Task task = tasks.get(taskNumber - 1);
        result += "Noted. I've removed this task:\n"
                  + "  " + task + "\n";
        tasks.remove(taskNumber - 1);
        return result + String.format("Now you have %d %s in the list.\n", this.tasks.size(),
                                                                           this.tasks.size() == 1 ? "task" : "tasks");
    }

    /**
     * Finds stored tasks by keyword
     *
     * @param query keywords for task-finding
     */
    public ArrayList<Task> find(String query) {
        ArrayList<Task> results = new ArrayList<>();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task tmp = this.tasks.get(i);
            if (tmp.getDescription().contains(query)) {
                results.add(tmp);
            }
        }
        return results;
    }

    /**
     * Indicates whether the taskList contains any tasks. Primarily used for testing.
     *
     * @return whether taskList is empty.
     * @throws Duke.DukeException issue found in state of empty taskList
     */
    public boolean isEmpty() throws Duke.DukeException {
        return this.tasks.isEmpty();
    }
}
