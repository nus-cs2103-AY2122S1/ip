import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

public class TaskList {
    private final ArrayList<Task> taskList;

    // used when loading from csv
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    // used when new user
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) throws DukeException {
        taskList.add(task);
        save();

        Duke.ui.addMessage("Got it. I've added this task:\n  " +
                task.toString() + "\n" + taskLengthReport(), TextColor.DEFAULT);
    }

    public void doTask(int taskNum) throws DukeException {
        int idx = getTaskIndexFromTaskNum(taskNum);
        Task task = taskList.get(idx);
        task.doTask();
        save();

        Duke.ui.addMessage("Nice! I've marked this task as done:\n  " +
                task.toString(), TextColor.DEFAULT);
    }

    public void deleteTask(int taskNum) throws DukeException {
        int idx = getTaskIndexFromTaskNum(taskNum);

        Task task = taskList.get(idx);
        taskList.remove(idx);
        save();

        Duke.ui.addMessage("Noted! I've removed this task:\n  " +
                task.toString() + "\n" + taskLengthReport(), TextColor.DEFAULT);
    }

    public void deleteDone() throws DukeException {
        taskList.removeIf(Task::isDone);
        save();
        Duke.ui.addMessage("Noted! I've removed all completed tasks.\n" +
                taskLengthReport(), TextColor.DEFAULT);
    }

    public void deleteExpired() throws DukeException {
        taskList.removeIf(Task::isExpired);
        save();
        Duke.ui.addMessage("Noted! I've removed all expired tasks.\n" +
                taskLengthReport(), TextColor.DEFAULT);
    }

    // Returns a nicely formatted string representation of all tasks in the list
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int size = taskList.size();

        if (size == 0) {
            return "No tasks yet!";
        }

        for (int i = 0; i < size; i++) {
            stringBuilder.append(i + 1);
            stringBuilder.append(". ");
            stringBuilder.append(taskList.get(i).toString());
            stringBuilder.append("\n");
        }

        // delete the last new line character
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    public void list() {
        int size = taskList.size();
        if (size == 0) {
            Duke.ui.addMessage("No tasks yet!", TextColor.DEFAULT);
        } else {
            for (int i = 0; i < size; i++) {
                Task task = taskList.get(i);
                Duke.ui.addMessage((i + 1) + ". " + task.toString()
                        + (i == size - 1 ? "" : "\n"), task.getListColor());
            }
        }
    }

    // parses the user's input and returns the index of the task in question
    private int getTaskIndexFromTaskNum(int taskNum) throws DukeException {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new DukeException("Please input the ID of a task!");
        }

        // tasks are 1-indexed to the user, but 0-indexed by implementation
        return taskNum - 1;
    }

    // returns a string telling the user how many tasks are in the list
    private String taskLengthReport() {
        return "Now you have " + taskList.size()
                + (taskList.size() != 1 ? " tasks" : " task") + " in the list.";
    }

    private void save() throws DukeException {
        Duke.storage.save(convertToSaveString());
    }

    private String convertToSaveString() {
        StringBuilder sb = new StringBuilder();

        if (taskList.size() == 0) {
            return "";
        }

        for (Task task : taskList) {
            sb.append(task.getSaveString());
            sb.append('\n');
        }

        // remove the last newline character
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }
}
