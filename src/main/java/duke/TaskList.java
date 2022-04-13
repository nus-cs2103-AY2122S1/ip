package duke;

import java.util.ArrayList;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.taskType;

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
            assert details.length == 1 : "details array for Todo should contain 1 field.";
            TaskList.add(new ToDo(details[0]));
            break;
        case DEADLINE:
            assert details.length == 2 : "details array for Deadline should contain 2 fields.";
            TaskList.add(new Deadline(details[0], details[1]));
            break;
        case EVENT:
            assert details.length == 2 : "details array for Event should contain 2 fields.";
            TaskList.add(new Event(details[0], details[1]));
            break;
        default:
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
            assert toDelete != null : "Task to delete should exist.";
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
            assert TaskList.get(index) != null : "Task to mark done should be valid.";
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
        assert toPrint != null : "Task to be printed should be valid.";
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
        if (TaskList.size() == 0) {
            return "You don't have any tasks in your list.";
        } else {
            String str = "";
            int i = 1;
            for (Task item:TaskList) {
                str = str + String.format("%s. %s\n", i, item);
                i += 1;
            }
            assert !str.equals("") : "string returned should not be empty.";
            return str;
        }
    }


    /**
     * Finds tasks in the tasklist containing the specified keyword.
     *
     * @param keyword the keyword to search
     * @return an ArrayList containing the matching tasks
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> results = new ArrayList<Task>();
        for (Task item : TaskList) {
            if ((item.toString().toLowerCase()).contains(keyword.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

}
