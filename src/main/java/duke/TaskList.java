package main.java.duke;
import java.util.ArrayList;

import main.java.duke.tasks.Task;

public class TaskList {
    protected ArrayList<Task> taskList;
    
    /**
     * Constructs a new task list with a given list of tasks
     * 
     * @param taskList a list of tasks
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Constructs a new empty task list 
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

}
