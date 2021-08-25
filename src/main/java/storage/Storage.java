package storage;

import exception.DukeException;
import models.Task;
import tasklist.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class Storage implements IStorage {

    private TaskList list;
    private final String filePath;

    public Storage(String fileDirectory, String fileName) {
        this.filePath = fileDirectory + "/" + fileName;
        this.list = new TaskList();
        try {
            File directory = new File(fileDirectory);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File storage = new File(filePath);
            if (!storage.exists()) {
                storage.createNewFile();
                writeTaskListToFile();
            }
            this.list = loadTaskListFromFile();
        } catch (IOException error) {
            System.out.println("Fail to handle file, error: " + error.getMessage());
        }
    }

    private TaskList loadTaskListFromFile() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.filePath));
            TaskList result = (TaskList) objectInputStream.readObject();
            return result;
        } catch (IOException error) {
            System.out.println("Fail to retrieve ArrayList from file, error: " + error.getMessage());
        } catch (ClassNotFoundException error) {
            System.out.println("Object from the file cannot be casted to ArrayList<Task>, error: " + error.getMessage());
        }
        return new TaskList();
    }

    private void writeTaskListToFile() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.filePath));
            objectOutputStream.writeObject(this.list);
        } catch (IOException error) {
            System.out.println("Fail to retrieve ArrayList from file, error: " + error.getMessage());
        }
    }

    public void addTask(Task task) {
        this.list.addTask(task);
        writeTaskListToFile();
    }

    public void setDone(int index) throws DukeException {
        this.list.setDone(index);
        writeTaskListToFile();
    }

    public Task getTask(int index) {
        return this.list.getTask(index);
    }

    public Task getLastTask() {
        return this.list.getLastTask();
    }

    public String deleteTask(int index) throws DukeException{
        String result = this.list.deleteTask(index);
        writeTaskListToFile();
        return result;
    }

    public int getSize() {
        return this.list.getSize();
    }

    @Override
    public String toString() {
        return this.list.toString();
    }
}