package duke;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks;
    private int numTask;

    /**
     * TaskList stores and retrieves tasks created by Duke.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.numTask = 0;
    }

    /**
     * General-purpose method that stores any task created by Duke.
     * @param task the task to be stored
     */
    public void addCustom(Task task) {
        tasks.add(task);
        this.numTask++;
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.printf("Now you have %d %s in the list.\n", this.numTask, this.numTask == 1 ? "task" : "tasks");
    }

    /**
     * Retrieves all stored tasks and displays them.
     */
    public void list() {
        for (int i = 0; i < this.numTask; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * Marks a task as done, represented in the future with a cross in the associated column.
     * @param taskNumber position of task in the TaskList
     */
    public void done(int taskNumber) {
        tasks.get(taskNumber - 1).complete();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + tasks.get(taskNumber - 1));
    }

    /**
     * Removes target task.
     * @param taskNumber position of target task
     */
    public void delete(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        tasks.remove(taskNumber - 1);
        this.numTask--;
        System.out.printf("Now you have %d %s in the list.\n", this.numTask, this.numTask == 1 ? "task" : "tasks");
    }

    /**
     * Indicates whether the taskList contains any tasks. Primarily used for testing.
     * @return whether taskList is empty.
     * @throws Duke.DukeException issue found in state of empty taskList
     */
    public boolean isEmpty() throws Duke.DukeException {
        if (this.numTask == 0) {
            if (!this.tasks.isEmpty()) {
                throw new Duke.DukeException("TaskList is empty without any items");
            }
            return true;
        }
        return false;
    }
}
