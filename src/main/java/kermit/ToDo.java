package kermit;

import kermit.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 *  ToDo list stores tasks.
 */
public class ToDo extends ArrayList<Task> {

    /**
     * ToDo constructor.
     * Initialises with no tasks.
     */
    public ToDo() {
        super();
    }

    /**
     * ToDo constructor.
     * Initialises list with tasks.
     *
     * @param taskArr Tasks to intialise ToDo list.
     */
    public ToDo(List<Task> taskArr) {
        super(taskArr);
    }

    public Task completeTask(int index) {
        Task task = super.get(index);
        task.markAsComplete();
        return task;
    }

    /**
     * Delete task from ToDo list using its index.
     * List is zero-indexed.
     *
     * @param index Index of task to delete.
     * @return Task that is deleted.
     */
    public Task deleteTask(int index) {
        Task task = super.get(index);
        super.remove(index);
        return task;
    }

    /**
     * String representation of todo list.
     *
     * @return String representation of todo list.
     */
    @Override
    public String toString() {
        String stringifiedList = "";
        for (int i = 0; i < super.size(); i++) {
            stringifiedList += (i + 1) + ". " + super.get(i).toString();
            // After last item should not have line break
            if (i < super.size() - 1) {
                stringifiedList += "\n";
            }
        }
        return stringifiedList;
    }
}
