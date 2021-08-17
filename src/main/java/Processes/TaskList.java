package Processes;

import java.util.List;
import java.util.ArrayList;

import Exception.DukeException;

public class TaskList {

    private final List<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public void setDone(int index) throws DukeException {
        try {
            this.list.get(index).setDone();
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("There is no task with number " + (index + 1) + " in the list");
        }
    }

    public Task getTask(int index) {
        return this.list.get(index);
    }

    public Task getLastTask() {
        return this.getTask(this.getSize() - 1);
    }

    public int getSize() {
        return this.list.size();
    }

    public String deleteTask(int index) throws DukeException{
        try {
            return this.list.remove(index).toString();
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("There is no task with number " + (index + 1) + " in the list");
        }
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list!\n";
        for (int i = 1; i < this.list.size() + 1; i++) {
            result += i + ". " + this.list.get(i - 1);
            if(i != this.list.size()) {
                result += "\n";
            }
        }
        return result;
    }
}