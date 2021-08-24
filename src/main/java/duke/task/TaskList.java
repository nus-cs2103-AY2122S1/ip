package duke.task;

import java.util.ArrayList;
import duke.exception.DukeException;
import duke.Duke;
import duke.io.TextColor;

public class TaskList {
    private final ArrayList<Task> tasks;

    // used when loading from csv
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    // used when new user
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) throws DukeException {
        tasks.add(task);
        save();

        Duke.ui.addMessage("Got it. I've added this task:\n  "
                + task.toString() + "\n" + taskLengthReport(), TextColor.DEFAULT);
    }

    public void doTask(int taskNum) throws DukeException {
        int idx = getTaskIndexFromTaskNum(taskNum);
        Task task = tasks.get(idx);
        task.doTask();
        save();

        Duke.ui.addMessage("Nice! I've marked this task as done:\n  "
                + task.toString(), TextColor.DEFAULT);
    }

    public void deleteTask(int taskNum) throws DukeException {
        int idx = getTaskIndexFromTaskNum(taskNum);

        Task task = tasks.get(idx);
        tasks.remove(idx);
        save();

        Duke.ui.addMessage("Noted! I've removed this task:\n  "
                + task.toString() + "\n" + taskLengthReport(), TextColor.DEFAULT);
    }

    public void deleteDone() throws DukeException {
        tasks.removeIf(Task::isDone);
        save();
        Duke.ui.addMessage("Noted! I've removed all completed tasks.\n"
                + taskLengthReport(), TextColor.DEFAULT);
    }

    public void deleteExpired() throws DukeException {
        tasks.removeIf(Task::isExpired);
        save();
        Duke.ui.addMessage("Noted! I've removed all expired tasks.\n"
                + taskLengthReport(), TextColor.DEFAULT);
    }

    public void list() {
        int size = tasks.size();
        if (size == 0) {
            Duke.ui.addMessage("No tasks yet!", TextColor.DEFAULT);
        } else {
            for (int i = 0; i < size; i++) {
                Task task = tasks.get(i);
                Duke.ui.addMessage((i + 1) + ". " + task.toString()
                        + (i == size - 1 ? "" : "\n"), task.getListColor());
            }
        }
    }

    // parses the user's input and returns the index of the task in question
    private int getTaskIndexFromTaskNum(int taskNum) throws DukeException {
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new DukeException("Please input the ID of a task!");
        }

        // tasks are 1-indexed to the user, but 0-indexed by implementation
        return taskNum - 1;
    }

    // returns a string telling the user how many tasks are in the list
    private String taskLengthReport() {
        return "Now you have " + tasks.size()
                + (tasks.size() != 1 ? " tasks" : " task") + " in the list.";
    }

    private void save() throws DukeException {
        Duke.storage.save(convertToSaveString());
    }

    private String convertToSaveString() {
        StringBuilder sb = new StringBuilder();

        if (tasks.size() == 0) {
            return "";
        }

        for (Task task : tasks) {
            sb.append(task.getSaveString());
            sb.append('\n');
        }

        // remove the last newline character
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

    // for testing
    protected ArrayList<Task> getList() {
        return tasks;
    }
}
