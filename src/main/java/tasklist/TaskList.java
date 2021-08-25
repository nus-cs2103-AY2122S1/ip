package tasklist;

import exception.DukeException;
import models.Task;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;

public class TaskList implements Serializable {

    private ArrayList<Task> list;

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
            String result = this.list.remove(index).toString();
            return result;
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("There is no task with number " + (index + 1) + " in the list");
        }
    }

    public TaskList findKeyword(String keyword) {
        TaskList result = new TaskList();
        for (int i = 0; i < this.list.size(); i++) {
            if (this.list.get(i).toString().contains(keyword)) {
                result.addTask(this.list.get(i));
            }
        }
        return result;
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