package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    private int numberOfTasks;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.numberOfTasks = 0;
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
        this.numberOfTasks = taskList.size();
    }

    public int getNumberOfTasks() {
        return this.numberOfTasks;
    }

    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    public void add(Task task) {
        this.taskList.add(task);
        this.numberOfTasks++;
    }

    public Task delete(int taskIndex) {
        Task deletedTask = this.taskList.remove(taskIndex);
        this.numberOfTasks--;
        return deletedTask;
    }

    public Task markAsDone(int taskIndex) {
        this.taskList.get(taskIndex).markAsDone();
        return getTask(taskIndex);
    }

    public String getSaveFormat() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.numberOfTasks; i++) {
            sb.append(this.taskList.get(i).getSaveFormat());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.numberOfTasks; i++) {
            sb.append(String.format("%d.%s\n", i + 1, this.taskList.get(i).toString()));
        }
        return sb.toString();
    }

    /**
     * Retrieves all matching tasks in the task list that match a specified keyword.
     *
     * @param keyword The keyword being searched.
     * @return A task list object containing all of the tasks that match the keyword specified.
     */
    public TaskList getMatchingTasks(String keyword) {
        TaskList results = new TaskList();
        for (int i = 0; i < this.numberOfTasks; i++) {
            Task currentTask = this.taskList.get(i);
            if (currentTask.containsKeyword(keyword)) {
                results.add(currentTask);
            }
        }
        return results;
    }
}
