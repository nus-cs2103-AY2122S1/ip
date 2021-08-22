package duke.task;

import duke.io.Ui;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public void remove(int i) {
        taskList.remove(i);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(task.toDataFormat());
            if (i != taskList.size() - 1) {
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public void print(Ui ui) {
        print(ui, "");
    }

    /**
     * Prints out the task list formatted and indented.
     */
    public void print(Ui ui, String message) {
        StringBuilder tasksString = new StringBuilder();
        if (!message.isEmpty()) {
            tasksString.append(message).append(System.lineSeparator());
        }
        for (int i = 0; i < taskList.size(); i++) {
            String taskAsString = i == taskList.size() - 1
                    ? String.format("%d.%s", i + 1, taskList.get(i))
                    : String.format("%d.%s\n", i + 1, taskList.get(i));
            tasksString.append(taskAsString);
        }
        ui.print(tasksString.toString());
    }

    public TaskList filter(String keyword) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                tasks.add(task);
            }
        }
        return new TaskList(tasks);
    }
}
