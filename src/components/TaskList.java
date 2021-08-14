package components;

import exceptions.DukeIndexOutOfBoundsException;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    private TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList addTask(Task task) {
        this.taskList.add(task);
        return new TaskList(this.taskList);
    }

    public String showTask(int index) throws DukeIndexOutOfBoundsException {
        try {
            return this.taskList.get(index).toString();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexOutOfBoundsException(index, this.taskList.size());
        }
    }

    public int size() {
        return this.taskList.size();
    }

    public TaskList markTaskDone(int index) throws DukeIndexOutOfBoundsException {
        // index should be between 0 and n-1
        try {
            this.taskList.set(index, this.taskList.get(index).markDone());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeIndexOutOfBoundsException(index, this.taskList.size());
        } finally {
            return new TaskList(this.taskList);
        }
    }

    @Override
    public String toString() {
        String returnString = "";
        for (int i = 1; i < this.taskList.size() + 1; i++) {
            String row = String.format("%d. %s", i, this.taskList.get(i-1).toString());
            if (i != this.taskList.size()) {
                row += "\n";
            }
            returnString += row;
        }
        return  returnString;
    }
}
