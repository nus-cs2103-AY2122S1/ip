package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    public void add(Task toAdd) {
        this.tasks.add(toAdd);
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task remove(int index) {
        return this.tasks.remove(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public String taskToString(int index) {
        return this.tasks.get(index).toString();
    }

    public String taskSaveToString(int index) {
        return this.tasks.get(index).convertToString();
    }

    public void markAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    /**
     * Finds tasks with given keyword in tasks.
     *
     * @param keyword Keyword of tasks we want to find.
     * @return TaskList of Tasks with given keyword.
     */
    public TaskList findTasksWithKeyword(String keyword) {
        ArrayList<Task> tasksWithKeyword = new ArrayList<>();

        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).hasKeyword(keyword)) {
                tasksWithKeyword.add(this.tasks.get(i));
            }
        }

        return new TaskList(tasksWithKeyword);
    }

    @Override
    public String toString() {
        return tasks.toString();
    }
}
