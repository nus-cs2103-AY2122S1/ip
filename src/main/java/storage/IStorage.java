package storage;

import exception.DukeException;
import models.Task;

public interface IStorage {

    public void addTask(Task task);

    public void setDone(int index) throws DukeException;

    public Task getTask(int index);

    public Task getLastTask();

    public String deleteTask(int index) throws DukeException;

    public int getSize();
}
