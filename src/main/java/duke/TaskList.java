package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tasks) {
            if (stringBuilder.length() != 0) {
                stringBuilder.append("\n");
            }
            String save = task.saveString();
            System.out.println(save);
            stringBuilder.append(save);
        }
        return stringBuilder.toString();
    }

    public int size() {
        return tasks.size();
    }

    public Task getIndex(int index) {
        return tasks.get(index);
    }

    public void removeIndex(int index) {
        tasks.remove(index);
    }

    /**
     * Finds all tasks with description matching the regex.
     * @param regex Regex to match to.
     * @return TaskList containing all matching tasks.
     */
    public TaskList findTasks(String regex) {
        TaskList result = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(regex)) {
                result.add(task);
            }
        }
        return result;
    }
}
