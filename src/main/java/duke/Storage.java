package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The Duke storage class.
 */
public class Storage {

    /** The relative path to the directory */
    private final String DIRECTORY;

    /** The file name */
    private final String FILE;

    /** The contents of the file as a List of Strings */
    private final List<String> FILE_CONTENTS;

    /**
     * Constructor for Storage.
     * @param directory The path to the directory.
     * @param file The file name.
     */
    public Storage(String directory, String file) {
        DIRECTORY = directory;
        FILE = file;
        FILE_CONTENTS = new ArrayList<>();
    }

    /**
     * Returns an ArrayList of Task from the saved data.
     * @return an ArrayList of Task from the saved data.
     * @throws DukeException upon load error.
     */
    public ArrayList<Task> load() throws DukeException{
        // Make directory and/or file if they don't exist.
        File dataDir = new File(DIRECTORY);
        dataDir.mkdirs();
        File dataFile = new File(DIRECTORY + "/" + FILE);
        try {
            dataFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create a new file");
            return null;
        }

        // Read each line in the saved file.
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner fileReader = new Scanner(dataFile);
            while (fileReader.hasNextLine()) {
                String rawData = fileReader.nextLine();
                FILE_CONTENTS.add(rawData);
                String[] datas = rawData.split(" \\| ");
                String taskType = datas[0];
                boolean isDone = datas[1].equals("1");
                Task task = null;
                switch (taskType) {
                case "T":
                    // Add a todo task.
                    task = new Todo(datas[2]);

                    break;
                case "D":
                    // Add a deadline task.
                    task = new Deadline(datas[2], datas[3]);

                    break;
                case "E":
                    // Add an event task.
                    task = new Event(datas[2], datas[3]);
                }
                if (task != null) {
                    if (isDone) {
                        task.markDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException(DukeException.Errors.FILE_NOT_FOUND.toString());
        }
        return tasks;
    }

    /**
     * Returns the String of the queried line.
     * @param id The line number.
     * @return The String of the queried line.
     * @throws DukeException When the line is not found.
     */
    public String getFileLine(int id) throws DukeException {
        try {
            return FILE_CONTENTS.get(id);
        } catch (Exception e) {
            throw new DukeException(DukeException.Errors.TASK_NOT_FOUND.toString());
        }
    }

    /**
     * Adds a task to the saved file.
     * @param task The task as a String.
     * @throws DukeException When saving the file fails.
     */
    public void addToFile(String task) throws DukeException {
        FILE_CONTENTS.add(task);
        commitChanges();
    }

    /**
     * Removes a task from the saved file.
     * @param id The line to be removed.
     * @throws DukeException When saving the file fails.
     */
    public void removeFromFile(int id) throws DukeException {
        FILE_CONTENTS.remove(id);
        commitChanges();
    }

    /**
     * Updates a task to the saved file.
     * @param id The line to be updated.
     * @param task The task as a String.
     * @throws DukeException When saving the file fails.
     */
    public void updateLineFile(int id, String task) throws DukeException {
        FILE_CONTENTS.set(id, task);
        commitChanges();
    }

    /**
     * Saving the file content to the hard drive.
     * @throws DukeException when saving the file fails.
     */
    private void commitChanges() throws DukeException {
        try {
            Files.write(Paths.get(DIRECTORY + "/" + FILE), FILE_CONTENTS, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException(DukeException.Errors.SAVE_FAIL.toString());
        }
    }
}
