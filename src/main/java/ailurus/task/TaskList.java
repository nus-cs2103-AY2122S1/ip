package ailurus.task;

import ailurus.AilurusException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.list = new ArrayList<>(tasks);
    }

    public int length() {
        return this.list.size();
    }

    public String getIndexString(int i) {
        return list.get(i).toString();
    }

    public Task getTask(int i) {
        return list.get(i);
    }

    /**
     * Marking a specific task as done
     *
     * @param str String to be converted to integer, representing task number to be marked as done
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
     */
    public Task addTask(Task task) {
        this.list.add(task);
        return task;
    }

    /**
     * Deleting task from a list of tasks
     *
     * @param str String to be converted to integer, representing task number to be deleted
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
