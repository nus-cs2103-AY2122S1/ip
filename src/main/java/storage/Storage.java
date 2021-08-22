package storage;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import exceptions.AuguryException;
import exceptions.FileIOException;
import tasks.Task;
import tasks.TaskFactory;
import tasks.TaskList;

public class Storage {
    // doesStorageFileExist, createBlankFile, createTaskListFromStorage, writeTaskListToStorage

    private String path;

    public Storage(String path) {
        this.path = path;
    }

    public void initializeTaskList(TaskList t) throws IOException {
        String directory = "data";
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File f = new File(path);
        f.createNewFile();

        writeStorageToTaskList(t);
    }

    public void saveTaskListToStorage(TaskList t) throws AuguryException {
        try {
            File f = new File(path);
            String s = convertTaskListToString(t);
            writeStringToStorage(s);
        } catch(IOException e) {
            e.printStackTrace();
            throw new FileIOException("File error occured");
        }
    }

    private String convertTaskListToString(TaskList xs) {
        ArrayList<Task> tasks = xs.tasks();
        ArrayList<String> tasks_string = new ArrayList<>();

        for (Task t : tasks) {
            tasks_string.add(t.toString());
        }

        String res = String.join("\n", tasks_string);
        return res.toString();
    }

    private void writeStorageToTaskList(TaskList t) throws FileNotFoundException {
        // read tasks.txt
        Scanner s = new Scanner(new File(path)); // create a Scanner using the File as the source
        ArrayList<String> tasks = new ArrayList<>();
        while (s.hasNext()) {
            tasks.add(s.nextLine());
        }

        // create a task for each line
        TaskFactory tf = new TaskFactory();
        for (String task : tasks) {
            Task newTask = tf.createTask(task);
            t.addTask(newTask);
        }
    }

    private void writeStringToStorage(String s) throws IOException {
        FileWriter fw = new FileWriter(path);
        fw.write(s);
        fw.close();
    }

}
