package storage;

import exception.DukeException;
import models.Task;
import tasklist.TaskList;

public class StorageStub implements IStorage {

    private TaskList loadTaskListFromFile() {
        return new TaskList();
    }

    private void writeTaskListToFile() {

    }

    public void addTask(Task task) {

    }

    public void setDone(int index) throws DukeException {

    }

    public Task getTask(int index) {
        return new Task("");
    }

    public Task getLastTask() {
        return new Task("");
    }

    public String deleteTask(int index) throws DukeException{
        return "test";
    }

    public int getSize() {
        return 0;
    }
}
