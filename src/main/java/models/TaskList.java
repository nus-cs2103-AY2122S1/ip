package models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import exception.DukeException;

public class TaskList {

    private ArrayList<Task> list;
    private String filePath;

    public TaskList(String fileDirectory, String fileName) {
        this.list = new ArrayList<Task>();
        this.filePath = fileDirectory + "/" + fileName;
        try {
            File directory = new File(fileDirectory);
            if (!directory.exists()) {
                directory.mkdir();
            }
            File storage = new File(filePath);
            if (!storage.exists()) {
                storage.createNewFile();
                writeArrayListToFile();
            }
            loadArrayListFromFile();
        } catch (IOException error) {
            System.out.println("Fail to handle file, error: " + error.getMessage());
        }
    }

    public void addTask(Task task) {
        this.list.add(task);
        writeArrayListToFile();
    }

    public void setDone(int index) throws DukeException {
        try {
            this.list.get(index).setDone();
            writeArrayListToFile();
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
            writeArrayListToFile();
            return result;
        } catch(IndexOutOfBoundsException e) {
            throw new DukeException("There is no task with number " + (index + 1) + " in the list");
        }
    }

    private void loadArrayListFromFile() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.filePath));
            @SuppressWarnings("unchecked")
            ArrayList<Task> result = (ArrayList<Task>) objectInputStream.readObject();
            this.list = result;
        } catch (IOException error) {
            System.out.println("Fail to retrieve ArrayList from file, error: " + error.getMessage());
        } catch (ClassNotFoundException error) {
            System.out.println("Object from the file cannot be casted to ArrayList<Task>, error: " + error.getMessage());
        }
    }

    private void writeArrayListToFile() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.filePath));
            objectOutputStream.writeObject(this.list);
        } catch (IOException error) {
            System.out.println("Fail to retrieve ArrayList from file, error: " + error.getMessage());
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