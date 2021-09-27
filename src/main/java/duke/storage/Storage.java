package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;

/**
 * Deals with loading tasks form the file and saving tasks in the file
 */
public class Storage {
    /** Path of data file */
    private final Path filePath;

    /**
     * Constructor for Storage
     *
     * @param filePath the path where data is to be stored
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
        if (!Files.exists(filePath)) {
            createFile(filePath);
        }
    }

    /**
     * Creates file from given path
     *
     * @param filePath the path to create file at
     */
    private void createFile(Path filePath) {
        try {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
            ArrayList<Task> newList = new ArrayList<>();
            saveData(newList);
        } catch (IOException e) {
            System.out.println("Can't create data file");
        }
    }

    /**
     * Fetches saved tasks and loads them into ArrayList
     *
     * @return list of saved tasks
     */
    public ArrayList<Task> loadData() {
        try {
            ArrayList<Task> lst = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(this.filePath.toFile()));

            for (String line = br.readLine(); line != null; line = br.readLine()) {
                String[] txt = line.split("/");
                boolean isDone = txt[1].equals("1");
                switch (txt[0]) {
                case "T":
                    lst.add(new Todo(txt[2], isDone));
                    break;
                case "E":
                    lst.add(new Event(Arrays.copyOfRange(txt, 2, 4), isDone));
                    break;
                case "D":
                    lst.add(new Deadline(Arrays.copyOfRange(txt, 2, 4), isDone));
                    break;
                default:
                    assert false; //Stored data should be in the correct format
                }
            }

            return lst;
        } catch (IOException e) {
            System.out.println("Can't load data");
            return new ArrayList<>();
        }
    }

    /**
     * Saves the given list into data folder
     *
     * @param lst the list of tasks to save
     */
    public void saveData(ArrayList<Task> lst) {
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter(this.filePath.toFile()));
            Task[] toIterate = lst.toArray(new Task[0]);
            for (Task t : toIterate) {
                bf.write(t.convertToData());
                bf.newLine();
            }
            bf.flush();
            bf.close();
        } catch (IOException e) {
            System.out.println("Can't save data");
        }
    }
}
