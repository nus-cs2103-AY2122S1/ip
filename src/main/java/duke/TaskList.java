package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> taskList){
        this.tasks = taskList;
    }

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) throws DukeException {
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Invalid Task. Please try again.");
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes a task from the taskList.
     *
     * @param index Index number of task.
     * @throws DukeException If task number is outside the range of tasklist.
     */
    public void deleteTask(int index) throws DukeException {
        try {
            tasks.remove(index);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Invalid Task. Please try again.\n");
        }
    }

    /**
     * Marks a task in the taskList as done.
     *
     * @param index Index number of task.
     * @throws DukeException If task number is outside the range of taskList.
     */
    public void markTask(int index) throws DukeException {
        try {
            Task task = tasks.get(index);
            task.markDone();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new DukeException("Invalid Task. Please try again.\n");
        }
    }

    public String toSave() {
        String saveTasks = "";
        for (int i = 0; i<tasks.size(); i++) {
            String taskToWrite = tasks.get(i).toWrite();
            saveTasks = saveTasks.concat(taskToWrite + "\n");
        }
        return saveTasks;
    }

    public String toString() {
        String list = "";
        for (int i = 0; i < tasks.size(); i++) {
            String task = tasks.get(i).toString();
            list = list.concat(String.format("%d.%s%n", i + 1, task));
        }
        return list;
    }
}
