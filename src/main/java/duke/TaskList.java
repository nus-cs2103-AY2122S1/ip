package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

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
}
