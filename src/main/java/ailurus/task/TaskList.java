package ailurus.task;

import java.util.ArrayList;

import ailurus.AilurusException;


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
     * @param str String to be converted to integer, representing task numbers to be marked as done
     * @return tasks that has been marked as done
     */
    public ArrayList<Task> done(String str) throws AilurusException {
        String[] arr = str.trim().split(" ", -1);
        ArrayList<Task> tasks = new ArrayList<>();
        assert arr.length > 0 : "string array must have length more than 0";
        for (int i = 0; i < arr.length; i++) {
            try {
                int taskNo = Integer.parseInt(arr[i]);
                if (taskNo > list.size() || taskNo < 1) {
                    throw new AilurusException(AilurusException.Error.NUMBER);
                } else {
                    Task task = list.get(taskNo - 1);
                    task.markAsDone();
                    tasks.add(task);
                }
            } catch (NumberFormatException e) {
                throw new AilurusException(AilurusException.Error.NAN);
            }
        }
        return tasks;
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
     * Deleting tasks from a list of tasks
     *
     * @param str String to be converted to integer, representing task numbers to be deleted
     * @return tasks that has been deleted
     */
    public ArrayList<Task> deleteTask(String str) {
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            String[] strArray = str.trim().split(" ", -1);
            assert strArray.length > 0 : "string array must have length more than 0";
            for (int i = 0; i < strArray.length; i++) {
                int taskNo = Integer.parseInt(strArray[i]);
                if (taskNo > list.size() || taskNo < 1) {
                    throw new AilurusException(AilurusException.Error.NUMBER);
                } else {
                    arr.add(taskNo);
                    tasks.add(list.get(arr.get(i) - 1));
                }
            }
            arr.sort((Integer a, Integer b) -> b - a);
            arr.forEach((Integer taskNo) -> list.remove(taskNo - 1));
        } catch (NumberFormatException e) {
            throw new AilurusException(AilurusException.Error.NAN);
        }

        return tasks;
    }
}
