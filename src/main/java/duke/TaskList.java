package duke;

import java.util.ArrayList;

import duke.task.Task;


public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

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

    public String uncheck(int taskNo) {
        return tasks.get(taskNo).uncheck();
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

    /**
     * finds tasks that contains the keyword
     *
     * @param keyword keyword
     * @return the collection of task that contains the keyword
     */
    public ArrayList<Task> findKeyword(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.findKeyword(keyword)) {
                matchedTasks.add(task);
            }
        }
        return matchedTasks;
    }

    public void updateTaskList(ArrayList taskList) {
        this.tasks = taskList;
    }
}
