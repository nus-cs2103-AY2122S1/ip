package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Manages the reading and writing of files onto the disk.
 */
public class Storage {
    private final File DATA_FILE;

    /**
     * Initialises a new Storage with the given file location to save.
     * If file does not exist, one will be created.
     *
     * @param filepath path to a .txt file
     */
    public Storage(String filepath) {
        DATA_FILE = new File(filepath);

        //Create data file if missing
        if (!DATA_FILE.exists()) {
            try {
                Files.createDirectory(Path.of("data"));
                Files.createFile(Path.of(filepath));
            } catch (IOException e) {
                System.out.println("WARNING: Cannot create save file");
            }
        }
    }

    /**
     * Retrieves the list of tasks saved from onto the disk.
     * Returns an empty list if error is encountered.
     *
     * @return list of tasks retrieved from disk
     */
    public ArrayList<Task> readFromDisk() {
        // Return empty list if file is empty
        if (DATA_FILE.length() == 0) {
            return new ArrayList<>();
        }

        // Else, deserialize data
        try {
            FileInputStream f = new FileInputStream(DATA_FILE);
            ObjectInputStream i = new ObjectInputStream(f);

            return (ArrayList<Task>) (i.readObject());
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: Task.Task list's save file missing.\n"
                    + e.getMessage());
        } catch (IOException e) {
            System.out.println("WARNING: Task.Task List not properly retrieved.\n"
                    + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("WARNING: Missing Class ArrayList<Task.Task>.\n"
                    + e.getMessage());
        }

        return new ArrayList<>();
    }

    /**
     * Writes a given list of tasks onto the disk.
     *
     * @param taskList list of tasks to be written onto the disk
     */
    public void writeToDisk(ArrayList<Task> taskList) {
        try {
            FileOutputStream f = new FileOutputStream(DATA_FILE);
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(taskList);
            o.close();
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: Task.Task list's save file missing.\n" + e.getMessage());
        } catch (IOException e) {
            System.out.println("WARNING: Task.Task List not properly saved.\n" + e.getMessage());
        }
    }

}
