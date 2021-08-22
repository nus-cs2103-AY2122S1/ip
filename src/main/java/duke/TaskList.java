package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> taskList;

    private enum Format {
        LIST,
        SAVE
    }

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public String stringifyTasksForList() {
        return this.stringify(Format.LIST);
    }

    public String stringifyTasksForSave() {
        return this.stringify(Format.SAVE);
    }

    private String stringify(Format format) {
        if (this.taskList.size() == 0) {
            return "No tasks added yet!";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task t = this.taskList.get(i);
            res.append(i + 1).append(". ").append(
                    format == Format.LIST ? t.toString() : t.toSaveString()
            ).append("\n");
        }
        return res.substring(0, res.length() - 1);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int x) {
        return this.taskList.get(x);
    }

    public Task remove(int x) {
        return this.taskList.remove(x);
    }

    public boolean add(Task t) {
        return this.taskList.add(t);
    }
}
