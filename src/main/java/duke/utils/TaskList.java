package duke.utils;

import duke.exceptions.InvalidTaskIdException;
import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void add(Task t) {
        this.taskList.add(t);
    }

    public void delete(int index) throws InvalidTaskIdException {
        if (index >= 0 && index < this.taskList.size()) {
            this.taskList.remove(index);
        } else {
            throw new InvalidTaskIdException();
        }
    }

    public Task get(int index) throws InvalidTaskIdException {
        if (index >= 0 && index < this.taskList.size()) {
            return this.taskList.get(index);
        } else {
            throw new InvalidTaskIdException();
        }
    }

    public void markAsCompleted(int index) throws InvalidTaskIdException {
        if (index >= 0 && index < this.taskList.size()) {
            this.taskList.get(index).markAsCompleted();
        } else {
            throw new InvalidTaskIdException();
        }
    }

    public int getSize() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        List<String> stringsArray = new ArrayList<>();
        for (Task t : this.taskList) {
            stringsArray.add(t.toString());
        }
        String tasks = String.join("\n", stringsArray);
        return "Current List:\n" + "---------------\n" + tasks;
    }
}
