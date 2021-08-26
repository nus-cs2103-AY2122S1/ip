package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks = new ArrayList<>(100);

    /**
     * Constructor for TaskList
     */
    public TaskList() {}

    /**
     * Constructor for TaskList
     *
     * @param taskList Existing list
     *
     */
    public TaskList(TaskList taskList) {
        this.tasks = taskList.getList();
    }

    /**
     * add task to list
     *
     * @param task task to be added
     *
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * get current list
     *
     * @return current list
     *
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * mark certain task as complete
     *
     * @param taskNo the index of task to be marked as completed
     * @return displayed message to inform user that task has been marked as completed
     *
     */
    public String complete(int taskNo) {
        return tasks.get(taskNo).check();
    }

    /**
     * delete certain task
     * 
     * @param taskNo the index of task to be deleted
     * @return displayed message to inform user that task has been deleted
     *
     */
    public String delete(int taskNo) {
        String temp = tasks.get(taskNo).toString();
        tasks.remove(taskNo);
        return temp;
    }
}
