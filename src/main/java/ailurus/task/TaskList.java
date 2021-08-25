package ailurus.task;

import ailurus.AilurusException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructor for empty TaskList
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructor for Tasklist with default tasks
     *
     * @param tasks default tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.list = new ArrayList<>(tasks);
    }

    /**
     * Size of arraylist of tasks
     *
     * @return length of arraylist
     */
    public int length() {
        return this.list.size();
    }

    /**
     * Get the task message of a particular task
     *
     * @param i index of the task
     * @return task message of the task
     */
    public String getIndexString(int i) {
        return list.get(i).toString();
    }

    /**
     * Get a particular task from the task list
     *
     * @param i index of the task
     * @return the particular task
     */
    public Task getTask(int i) {
        return list.get(i);
    }

    /**
     * Marking a specific task as done
     *
     * @param str String to be converted to integer, representing task number to be marked as done
     * @return task that has been marked as done
     */
    public Task done(String str) {
        try {
            int taskNo = Integer.parseInt(str);
            if (taskNo > list.size() || taskNo < 1) {
                throw new AilurusException(AilurusException.Error.NUMBER);
            } else {
                Task task = list.get(taskNo - 1);
                task.markAsDone();
                return task;
            }
        } catch (NumberFormatException e) {
            throw new AilurusException(AilurusException.Error.NAN);
        }
    }

    /**
     * Adding task to list of tasks
     *
     * @param task Ailurus.Task that has been added
     * @return task that has been added
     */
    public Task addTask(Task task) {
        this.list.add(task);
        return task;
    }

    /**
     * Deleting task from a list of tasks
     *
     * @param str String to be converted to integer, representing task number to be deleted
     * @return task that has been deleted
     */
    public Task deleteTask(String str) {
        try {
            int taskNo = Integer.parseInt(str);
            if (taskNo > list.size() || taskNo < 1) {
                throw new AilurusException(AilurusException.Error.NUMBER);
            } else {
                Task task = list.get(taskNo - 1);
                list.remove(taskNo - 1);
                return task;
            }
        } catch (NumberFormatException e) {
            throw new AilurusException(AilurusException.Error.NAN);
        }
    }
}
