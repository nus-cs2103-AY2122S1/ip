package duke.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import duke.task.Task;

/**
 * @author Dr-Octavius
 *
 * Class that represents Task List of Tasks
 *
 * @version 1.0
 */
public class TaskList {

    private final List<Task> taskList;

    /**
     * Class Constructor that initiates a new Task List
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Class Constructor that initiates a Task List from a given Storage File
     *
     * If storage file is empty, a new List is created instead
     *
     * @param storageFile provided storage file
     */
    public TaskList(File storageFile) {
        this();
    }

    /**
     * Adds a Task into the Task List
     */
    public void add(Task t){
        taskList.add(t);
    }

    /**
     * Removes a Task from the Task List
     */
    public void remove(int index){
        taskList.remove(index);
    }

    /**
     * Retrieves the size of the task list
     *
     * @return size of the task list
     */
    public int size(){
        return taskList.size();
    }

    /**
     * Retrieves the Task at the specified index in the Task List
     *
     * @param index target index within the Task List
     * @return Task at the specified index
     */
    public Task get(int index){
        return taskList.get(index);
    }
}
