package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tList;
    public TaskList() {
        tList = new ArrayList<>();
    }

    public void add(Task task) {
        tList.add(task);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : tList) {
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
        return tList.size();
    }

    public Task getIndex(int index) {
        return tList.get(index);
    }

    public void removeIndex(int index) {
        tList.remove(index);
    }
}
