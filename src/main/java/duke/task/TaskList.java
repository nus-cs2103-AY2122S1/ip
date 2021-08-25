package duke.task;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int taskNumber) throws DukeException {
        try {
            this.taskList.remove(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number.");
        }

    }

    public Task getTask(int taskNumber) throws DukeException {
        try {
            return this.taskList.get(taskNumber - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number.");
        }

    }

    public void doneTask(int taskNumber) throws DukeException {
        try {
            this.taskList.get(taskNumber - 1).markDone();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Please enter a valid task number.");
        }
    }

    public int totalTask() {
        return this.taskList.size();
    }

    public String toFileString() {
        String fileTask;

        if (taskList.isEmpty()) {
            fileTask = "";
        } else {
            fileTask = taskList.get(0).toFileString();
        }

        for (int i = 1; i < taskList.size(); i++) {
            fileTask = fileTask + "\n" + taskList.get(i).toFileString();
        }

        return fileTask;
    }
}
