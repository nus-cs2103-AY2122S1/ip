package duke;

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

import duke.task.Task;

/**
 * Manages the reading and writing of files onto the disk.
 */
public class Storage {
    private final File dataFile;

    /**
     * Initialises a new Storage with the given file location to save.
     * If file does not exist, one will be created.
     *
     * @param filepath path to a .txt file
     */
    public Storage(String filepath) {
        dataFile = new File(filepath);

        //Create data file if missing
        if (!dataFile.exists()) {
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
        if (dataFile.length() == 0) {
            return new ArrayList<>();
        }

        // Else, deserialize data
        try {
            FileInputStream fileStream = new FileInputStream(dataFile);
            ObjectInputStream inputStream = new ObjectInputStream(fileStream);

            return (ArrayList<Task>) (inputStream.readObject());
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: Task list's save file missing.\n"
                    + e.getMessage());
        } catch (IOException e) {
            System.out.println("WARNING: Task List not properly retrieved.\n"
                    + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("WARNING: Missing Class ArrayList<Task>.\n"
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
            FileOutputStream fileStream = new FileOutputStream(dataFile);
            ObjectOutputStream outputStream = new ObjectOutputStream(fileStream);

            outputStream.writeObject(taskList);
            outputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("WARNING: Task list's save file missing.\n" + e.getMessage());
        } catch (IOException e) {
            System.out.println("WARNING: Task List not properly saved.\n" + e.getMessage());
        }
    }

}
