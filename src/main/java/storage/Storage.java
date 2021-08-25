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

/**
 * Storage implementation that writes and saves the TaskList to local storage.
 */
public class Storage implements IStorage {

    /** TaskList object that store all the Task objects. */
    private TaskList list;

    /** The path of the file that store TaskList in local disk. */
    private final String filePath;

    /**
     * Constructor of the Storage object that allows local disk memory.
     *
     * @param fileDirectory Directory of the storage file.
     * @param fileName Name of the storage file.
     */
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

    /**
     * Retrieve the TaskList saved inside the local storage file.
     *
     * @return Saved TaskList in the local memory.
     */
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

    /**
     * Save the current TaskList to local memory.
     */
    private void writeTaskListToFile() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.filePath));
            objectOutputStream.writeObject(this.list);
        } catch (IOException error) {
            System.out.println("Fail to retrieve ArrayList from file, error: " + error.getMessage());
        }
    }

    /**
     * Wrapper function implementation that add task to the TaskList.
     *
     * @param task
     */
    public void addTask(Task task) {
        this.list.addTask(task);
        writeTaskListToFile();
    }

    /**
     * Wrapper function implementation that set a specified Task inside TaskList to be done.
     *
     * @param index Index of the Task that will be set done.
     * @throws DukeException If there is no task with the specified index.
     */
    public void setDone(int index) throws DukeException {
        this.list.setDone(index);
        writeTaskListToFile();
    }

    /**
     * Wrapper function implementation that get a specified Task from TaskList.
     *
     * @param index Index of the Task that will be retrieved.
     * @return  Task object with the specified index.
     */
    public Task getTask(int index) {
        return this.list.getTask(index);
    }

    /**
     * Wrapper function implementation to get the latest Task from the TaskList.
     *
     * @return Task object with the last index.
     */
    public Task getLastTask() {
        return this.list.getLastTask();
    }

    /**
     * Wrapper function implementation that delete Task from the TaskList with the specified index.
     *
     * @param index Index of the Task that will be deleted.
     * @return String representation of the deleted Task.
     * @throws DukeException If there is no Task with the specified index.
     */
    public String deleteTask(int index) throws DukeException{
        String result = this.list.deleteTask(index);
        writeTaskListToFile();
        return result;
    }

    /**
     * Wrapper function implementation to get the number of Task in the TaskList.
     *
     * @return The number of Task objects in the TaskList.
     */
    public int getSize() {
        return this.list.getSize();
    }

    /**
     * Return TaskList String representation.
     *
     * @return String representation of TaskList.
     */
    @Override
    public String toString() {
        return this.list.toString();
    }
}