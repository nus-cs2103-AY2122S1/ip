package bruh.storage;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import bruh.tasklist.TaskList;

/**
 * Represents the chatbot's storage function, by saving & loading the task list
 * to & from the disk appropriately.
 */
public class Storage {
    private final Path storageFilePath;

    /**
     * Constructor for the chatbot's storage function.
     * Creates the save directory and file if it is not found.
     *
     * @param pathString The path to the save file.
     */
    public Storage(String pathString) {
        this.storageFilePath = Paths.get(".", pathString);
        try {
            Files.createDirectories(this.storageFilePath.getParent());
            if (!Files.exists(storageFilePath)) {
                Files.createFile(storageFilePath);
            }
        } catch (IOException e) {
            System.out.printf("Failed to create storage file: %s\n", e.getMessage());
        }
    }

    /**
     * Reads the task list from disk & returns it. If not found,
     * generates the specified save directory & save file.
     *
     * @return The task list loaded from disk, an empty task list if not found.
     */
    public TaskList loadTaskList() {
        try (InputStream is = Files.newInputStream(storageFilePath);
             ObjectInputStream ois = new ObjectInputStream(is)) {
            // Only Task objects are written to / removed from the list
            return (TaskList) ois.readObject();
        } catch (IOException e) {
            System.out.println("Save file not found; Creating new task list\n");
            return new TaskList();
        } catch (ClassNotFoundException e) {
            System.out.printf("Failed to load from storage: %s\nCreating new task list\n", e.getMessage());
            return new TaskList();
        }
    }

    /**
     * Saves the specified task list to disk.
     *
     * @param taskList The task list to be saved to disk.
     */
    public void saveToDisk(TaskList taskList) {
        try (OutputStream os = Files.newOutputStream(storageFilePath);
             ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(taskList);
        } catch (IOException e) {
            System.out.printf("Failed to save to disk: %s\n", e.getMessage());
        }
    }
}
