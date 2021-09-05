package duke;

import java.util.ArrayList;

import duke.task.Task;

/**
 * List to store tasks in Duke chatbot.
 */
public class TaskList<Task> extends ArrayList<Task> {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

}
