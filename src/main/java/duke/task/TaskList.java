package duke.task;

import java.util.ArrayList;
import java.util.List;


/**
 * The class for creating and managing the list of tasks.
 */
public class TaskList {
    private static TaskList MainList = null;
    private ArrayList<Task> taskList = new ArrayList<>();

    private TaskList() {}

    /**
     * Returns the tasklist object
     *
     * @return TaskList An ArrayList of all the tasks
     */
    public static TaskList getTaskList() {
        if( MainList == null) {
            MainList = new TaskList();
        }

        return MainList;
    }

    /**
     * Returns a list of tasks
     *
     * @return TaskList An ArrayList of all the tasks
     */
    public List<? extends Task> getTasks() {
        if(MainList == null) {
            MainList = new TaskList();
        }

        return this.taskList;
    }

    /**
     * Adds a task to the list of tasks
     *
     * @return int size of the list of tasks
     */
    public int addTask(Task task) {
        if( MainList == null) {
            MainList = new TaskList();
        }
        this.taskList.add(task);
        return this.taskList.size();
    }

    /**
     * Returns the number of tasks in the list
     * @return int size of the list of tasks
     */
    public int getSize() {
        return this.taskList.size();
    }
}
