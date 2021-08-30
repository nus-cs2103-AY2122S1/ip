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

    @Override
    public void addTask(Task task) {

    }

    @Override
    public void setDone(int index) throws DukeException {

    }

    @Override
    public Task getTask(int index) {
        return new Task("");
    }

    @Override
    public Task getLastTask() {
        return new Task("");
    }

    @Override
    public String deleteTask(int index) throws DukeException {
        return "test";
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public TaskList findKeyword(String keyword) {
        return null;
    }
}
