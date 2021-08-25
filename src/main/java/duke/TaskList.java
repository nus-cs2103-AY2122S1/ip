package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds task onto task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Returns the task specified by the index.
     *
     * @param index Index of the task.
     * @return Task.
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task list.
     */
    public Integer taskListSize() {
        return this.taskList.size();
    }

    /**
     * Sets the task specified as done.
     *
     * @param index Index of the task.
     */
    public void setTaskDone(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Remove the task specified by the index.
     *
     * @param index Index of the task.
     * @return The task that is removed.
     */
    public Task removeTask(int index) {
        Task.noOfTask -= 1;
        return taskList.remove(index);
    }

    /**
     * Finds all task that contains a keyword.
     *
     * @param keyword Keyword use to find task.
     * @return Task list that contains all task that have the keyword
     */
    public TaskList findTasks(String keyword) {
        TaskList taskListWithKeyword = new TaskList();
        for(int i = 0; i < taskListSize(); i++) {
            Task currTask = this.taskList.get(i);
            if (currTask.getDescription().contains(keyword)) {
                taskListWithKeyword.addTask(currTask);
            }
        }
        return taskListWithKeyword;
    }

    /**
     * Return string form of task list.
     *
     * @return String form of task list.
     */
    @Override
    public String toString() {
        String str = "";
        for (int j = 0; j < taskList.size(); j++) {
            String listItem = (j + 1)
                    + "."
                    + taskList.get(j).getTaskType()
                    + taskList.get(j).checkIsDone()
                    + " " + taskList.get(j).getDescription() + "\n";
            str = str + listItem;
        }
        return str;
    }
}
