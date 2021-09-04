package duke.task;

import duke.error.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList (ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void updateStatus(int index) {
        tasks.get(index).updateStatus();
    }

    public Task deleteTask(int index) {
        return tasks.remove(index);
    }

    public void addTodoTask(String description) {
        tasks.add(new TodoTask(description));
    }

    public void addDeadlineTask(String description) throws DukeException {
        int timeIdxStart = description.indexOf("/");

        if(timeIdxStart == -1) {
            throw new DukeException("OOPS!! format for deadline command is incorrect.\nthe format should be:\n\t[description] /by [date]");
        }

        String task = description.substring(0, timeIdxStart);
        String time = description.substring(timeIdxStart + 4);
        tasks.add(new DeadlineTask(task, time));
    }

    public void addEventtask(String description) throws DukeException {
        int timeIdxStart = description.indexOf("/");

        if(timeIdxStart == -1) {
            throw new DukeException("OOPS!! format for event command is incorrect.\nthe format should be:\n\t[description] /at [time]");
        }

        String task = description.substring(0, timeIdxStart);
        String time = description.substring(timeIdxStart + 4);
        tasks.add(new EventTask(task, time));
    }

    public ArrayList<Task> getListOfTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }
}
