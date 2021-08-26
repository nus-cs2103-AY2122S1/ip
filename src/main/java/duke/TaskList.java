package duke;

import duke.tasks.*;

import java.util.ArrayList;

/**
 * A TaskList class encapsulates the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> TaskList;

    public TaskList(ArrayList<Task> list) {
        this.TaskList = list;
    }

    public ArrayList<Task> getList() {
        return TaskList;
    }

    /**
     * Adds a Task to the tasklist.
     *
     * @param type type of task
     * @param details description of the task
     */
    public void addTask(taskType type, String[] details) {
        switch (type) {
        case TODO:
            TaskList.add(new ToDo(details[0]));
            break;
        case DEADLINE:
            TaskList.add(new Deadline(details[0], details[1]));
            break;
        case EVENT:
            TaskList.add(new Event(details[0], details[1]));
            break;
        }

    }

    /**
     * Deletes a task from a task list.
     *
     * @param taskNo the task number to be deleted
     * @return the deleted task
     * @throws DukeException if task number is invalid
     */
    public Task deleteTask(int taskNo) throws DukeException {
        int index = taskNo - 1;
        try {
            Task toDelete = TaskList.get(taskNo - 1);
            TaskList.remove(index);
            return toDelete;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, I cannot find that task no. please enter a valid number :)");
        }

    }

    /**
     * Marks the task as done and updates the list when new tasks are completed.
     *
     * @param taskNo completed task nunmber
     * @return completed task
     * @throws DukeException if task number is invalid
     */
    public Task doneTask(int taskNo) throws DukeException {
        int index = taskNo - 1;
        try {
            TaskList.get(index).markDone();
            return TaskList.get(taskNo - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Sorry, I cannot find that task no. please enter a valid number :)");
        }
    }

    /**
     * Returns details of a specified task in the list.
     *
     * @param taskNo task number
     * @return task specified by task number
     */
    public Task getTask(int taskNo) {
        Task toPrint = TaskList.get(taskNo - 1);
        return toPrint;
    }

    /**
     * Returns the number of task in the list
     *
     * @return the number of tasks in the list
     */
    public int count() {
        return TaskList.size();
    }

    /**
     * Formats the task list as a numbered list.
     *
     * @return String showing all tasks
     */
    public String toDisplay() {
        String str = "";
        int i = 1;
        for (Task item:TaskList) {
            str = str + String.format("%s. %s\n", i, item);
            i += 1;
        }
        return str;
    }

}
