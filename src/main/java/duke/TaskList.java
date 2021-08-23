package duke;

import duke.tasks.Task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    private enum Format {
        LIST,
        SAVE
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    public String stringifyTasksForList() {
        return this.stringify(Format.LIST);
    }

    public String stringifyTasksForSave() {
        return this.stringify(Format.SAVE);
    }

    private String stringify(Format format) {
        if (this.tasks.size() == 0) {
            return "No tasks added yet!";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            Task t = this.tasks.get(i);
            res.append(i + 1).append(". ").append(
                    format == Format.LIST ? t.toString() : t.toSaveString()
            ).append("\n");
        }
        return res.substring(0, res.length() - 1);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int x) {
        return this.tasks.get(x);
    }

    public Task remove(int x) {
        return this.tasks.remove(x);
    }

    public boolean add(Task t) {
        return this.tasks.add(t);
    }
}
