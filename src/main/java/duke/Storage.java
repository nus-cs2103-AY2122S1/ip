package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage class saves and loads tasks from the save file.
 */
public class Storage {
    /** The path of the file to read/write */
    private Path filepath;

    /**
     * Constructor for Storage.
     *
     * @param filepath The filepath of the storage file.
     */
    public Storage(Path filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads from the save file if available.
     * Returns an array of strings containing the task,
     * throw DukeException if file is not found.
     *
     * @return ArrayList containing string representation of the tasks.
     * @throws DukeException No current save file.
     */
    public ArrayList<String> load() throws DukeException {
        try {
            ArrayList<String> tasklist = new ArrayList<>();
            File file = new File(filepath.toString());
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                tasklist.add(fileReader.nextLine());
            }
            return tasklist;
        } catch (FileNotFoundException e) {
            createFile(filepath.toString());
            throw new DukeException("File not available.");
        }
    }

    /**
     * Appends the given task string to file.
     *
     * @param taskString The task in string representation.
     */
    public void saveTask(String taskString) {
        assert !taskString.equals("") : "Cannot save empty string";
        try {
            FileWriter writer = new FileWriter(filepath.toString(), true);
            writer.write(taskString + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving task.\n" + e.getMessage());
        }
    }

    /**
     * Rewrites the file with the latest list of tasks.
     *
     * @param tasklist The list of tasks to save to file.
     */
    public void rewriteFile(ArrayList<Task> tasklist) {
        try {
            FileWriter writer = new FileWriter(filepath.toString());
            for (Task task : tasklist) {
                writer.write(task.saveString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to rewrite file\n" + e.getMessage());
        }
    }

    /**
     * Creates a new file for saving data.
     *
     * @param filename The name of the file to be created.
     */
    public void createFile(String filename) {
        assert !filename.equals("");
        try {
            File newFile = new File(filename);
            if (newFile.createNewFile()) {
                System.out.println("New save file created.");
            } else {
                throw new DukeException("Unable to create save file");
            }
        } catch (IOException e) {
            System.out.println("Error creating file");
            e.printStackTrace();
        } catch (DukeException d) {
            System.out.println(d.getMessage());
        }
    }
}
