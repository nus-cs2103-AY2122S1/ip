package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * The Storage class encapsulates Duke's data and operations involving this data.
 */
public class Storage {
    /** The path of the data directory. */
    private static final Path DATA_DIRECTORY_PATH = Paths.get("data");

    /** The path of the save file containing the user's tasks. */
    private static final Path SAVE_FILE_PATH = Paths.get("data", "duke.txt");

    /** The save file containing the user's tasks. */
    private File saveFile;

    /**
     * Constructs a storage object that initializes a data directory and save file
     * if not already present, and loads the save file.
     *
     * @throws DukeException If there is an error initializing the data directory or save file.
     */
    public Storage() throws DukeException {
        try {
            if (!Files.exists(DATA_DIRECTORY_PATH)) {
                Files.createDirectory(DATA_DIRECTORY_PATH);
            }
            if (!Files.exists(SAVE_FILE_PATH)) {
                Files.createFile(SAVE_FILE_PATH);
            }
            this.saveFile = SAVE_FILE_PATH.toFile();
        } catch (IOException e) {
            throw new DukeException("There was an error creating a save file for Duke.");
        }
    }

    /**
     * Gets all of the saved files contents and returns them a list of strings,
     * with each string representing each line in the save file.
     *
     * @return A list of strings, where each string is a line in the save file,
     * and each line is separated by a new line character.
     * @throws DukeException If there is an error accessing the save file.
     */
    public List<String> getSavedContents() throws DukeException {
        try {
            return Files.readAllLines(SAVE_FILE_PATH);
        } catch (IOException e) {
            throw new DukeException("There was an error retrieving your save file for Duke.");
        }
    }

    /**
     * Appends a new task to the save file.
     *
     * @param task The task object to be added to the save file.
     * @throws DukeException If there is an error accessing the save file.
     */
    public void appendToSave(Task task) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.saveFile, true);
            fw.write(task.getSaveFormat());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("There was an error accessing your save file.");
        }
    }

    /**
     * Overwrites the save file with a new task list.
     *
     * @param taskList The task list whose contents are to be written to the save file.
     * @throws DukeException If there is an error accessing the save file.
     */
    public void overwriteSave(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.saveFile);
            fw.write(taskList.getSaveFormat());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("There was an error accessing your save file.");
        }
    }
}
