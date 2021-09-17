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
     * Add task to list.
     *
     * @param task Task to be added
     *
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Get current task list.
     *
     * @return Current task list
     *
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Mark certain task as complete.
     *
     * @param taskNo Index of task to be marked as completed
     * @return Displayed message to inform user that task has been marked as completed
     *
     */
    public String complete(int taskNo) {
        return tasks.get(taskNo).check();
    }

    /**
     * Mark certain task as incomplete.
     *
     * @param taskNo Index of task to be unchecked
     * @return Displayed message to inform user that task has been unchecked
     */
    public String uncheck(int taskNo) {
        return tasks.get(taskNo).uncheck();
    }

    /**
     * Delete a certain task.
     *
     * @param taskNo Index of task to be deleted
     * @return Displayed message to inform user that task has been deleted
     *
     */
    public String delete(int taskNo) {
        String temp = tasks.get(taskNo).toString();
        tasks.remove(taskNo);
        return temp;
    }

    /**
     * Finds tasks that contains the keyword.
     *
     * @param keyword Keyword that user inputted
     * @return Collection of task that contains the keyword
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
