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
    private String directory;

    /** The file name */
    private String file;

    /** The contents of the file as a List of Strings */
    private List<String> fileContents;

    /**
     * Constructor for Storage.
     * @param directory The path to the directory.
     * @param file The file name.
     */
    public Storage(String directory, String file) {
        this.directory = directory;
        this.file = file;
        fileContents = new ArrayList<>();
    }

    /**
     * Returns an ArrayList of Task from the saved data.
     * @return an ArrayList of Task from the saved data.
     * @throws DukeException upon load error.
     */
    public ArrayList<Task> load() throws DukeException {
        // Make directory and/or file if they don't exist.
        File dataDir = new File(directory);
        dataDir.mkdirs();
        File dataFile = new File(directory + "/" + file);
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
                fileContents.add(rawData);
                Task task = Parser.parseSavedFile(rawData);
                tasks.add(task);
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
            return fileContents.get(id);
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
        assert task != null : "[duke.Storage.addToFile]: task parameter should not be null.";
        fileContents.add(task);
        commitChanges();
    }

    /**
     * Removes a task from the saved file.
     * @param id The line to be removed.
     * @throws DukeException When saving the file fails.
     */
    public void removeFromFile(int id) throws DukeException {
        fileContents.remove(id);
        commitChanges();
    }

    /**
     * Updates a task in the file as done.
     * @param id The task line to be updated.
     * @throws DukeException When saving the file fails.
     */
    public void markLineDone(int id) throws DukeException {
        int doneIndex = 4;
        String task = this.getFileLine(id);
        String newTask = task.substring(0, doneIndex) + "1" + task.substring(doneIndex + 1);
        this.updateLineFile(id, newTask);
    }

    /**
     * Updates a task to the saved file.
     * @param id The line to be updated.
     * @param task The task as a String.
     * @throws DukeException When saving the file fails.
     */
    private void updateLineFile(int id, String task) throws DukeException {
        assert task != null : "[duke.Storage.updateLineFile]: task parameter should not be null.";
        fileContents.set(id, task);
        commitChanges();
    }

    /**
     * Saving the file content to the hard drive.
     * @throws DukeException when saving the file fails.
     */
    private void commitChanges() throws DukeException {
        try {
            Files.write(Paths.get(directory + "/" + file), fileContents, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException(DukeException.Errors.SAVE_FAIL.toString());
        }
    }
}
