package storage;

import java.util.ArrayList;

import exception.InvalidIndexException;
import models.Task;
import tasklist.TaskList;

public class StorageStub implements IStorage {

    private TaskList loadTaskListFromFile() {
        return new TaskList(new ArrayList<>());
    }

    private void writeTaskListToFile() {

    }

    @Override
    public void addTask(Task task) {

    }

    @Override
    public void setDone(int index) throws InvalidIndexException {

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
    public String deleteTask(int index) throws InvalidIndexException {
        return "test";
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public TaskList findKeyword(String ... keywords) {
        return null;
    }

    @Override
    public TaskList getPrevious() {
        return null;
    }

    @Override
    public TaskList getNext() {
        return null;
    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {

    }
}
