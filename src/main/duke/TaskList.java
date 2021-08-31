package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * List to store tasks in Duke chatbot.
 */
public class TaskList<Task> extends ArrayList<Task>{

    public ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

}
