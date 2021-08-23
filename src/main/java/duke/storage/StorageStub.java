package main.java.duke.storage;

import main.java.duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class StorageStub implements Storage {

    private ArrayList<String> fileContent = new ArrayList<>();

    @Override
    public ArrayList<Task> load() {
        return new ArrayList<>();
    }

    @Override
    public void setDone(int index) {
        String str = fileContent.get(index);
        fileContent.set(index, str.replace("|0|", "|1|"));
    }

    @Override
    public void add(String type, String description, String date) {
        fileContent.add(type + "|0|" + description + "|" + date);
    }

    @Override
    public void delete(int index) {
        fileContent.remove(index);
    }

    public String getString(int index) {
        return fileContent.get(index);
    }
}
