package duke;

import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList implements Cloneable {
    private ArrayList<Task> tasks;
    public TaskList() {
        tasks = new ArrayList<Task>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void replaceWithTaskList(TaskList taskList) {
        tasks = taskList.tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removed the task at index.
     * @param index Index of task to remove.
     * @return
     */
    public Task removeTask(int index) {
        assert tasks.size() > index && index >= 0 : "index should have been valididated";
        return tasks.remove(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasksOccurringOn(LocalDate date) {
        ArrayList<Task> relevantTasks = new ArrayList<Task>();
        for (Task task: tasks) {
            if ((task instanceof Deadline && ((Deadline) task).by.equals(date))
                    || (task instanceof Event && ((Event) task).at.equals(date))) {
                relevantTasks.add(task);
            }
        }
        return relevantTasks;
    }

    public int getLength() {
        return tasks.size();
    }

    public ArrayList<Task> getMatchingTasks(String query) {
        ArrayList<Task> matchingTasks = new ArrayList<>(tasks);
        matchingTasks.removeIf(task -> !task.matches(query));
        return matchingTasks;
    }

    public boolean doesContain(Task task) {
        return tasks.contains(task);
    }

    @Override
    public TaskList clone() {
        ArrayList<Task> clonedTaskList = new ArrayList<Task>();
        for (Task task: this.tasks) {
            clonedTaskList.add(task.clone());
        }
        return new TaskList(clonedTaskList);
    }
}
