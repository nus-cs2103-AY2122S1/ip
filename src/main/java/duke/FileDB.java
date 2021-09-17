package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a storage for Tasks added.
 */
public class FileDB {
    /** Defines a default saved file location. */
    private static final String DEFAULT_SAVE = "storage.txt";
    private File fileDB;
    private boolean isFileExists;
    private Parser parser;

    /**
     * Creates a FileDB instance.
     *
     * @param taskList location to load the permanent storage to.
     * @throws DukeIoException when there is a problem with opening a folder.
     * @throws DukeDateParseException when there is a problem with parsing dates.
     */
    public FileDB(TaskList taskList) throws DukeIoException, DukeDateParseException {
        this.fileDB = new File(DEFAULT_SAVE);
        this.parser = new Parser();
        this.isFileExists = false;
        try {
            this.isFileExists = this.fileDB.createNewFile();
            if (!this.isFileExists) {
                this.loadTask(taskList);
                System.out.println("Welcome back!");
            } else {
                System.out.println("I've created a record for you!");
            }
        } catch (IOException e) {
            throw new DukeIoException();
        }
    }

    /**
     * Creates a FileDB instance.
     *
     * @param location the location of where the file should be stored at.
     * @throws DukeIoException when the file is at an inaccessible location.
     */
    public FileDB(String location) throws DukeIoException {
        this.fileDB = new File(location);
        this.parser = new Parser();
        this.isFileExists = false;
        try {
            this.isFileExists = this.fileDB.createNewFile();
        } catch (IOException e) {
            throw new DukeIoException();
        }
    }

    /**
     * Saves the task into the storage.
     *
     * @param task the task to be stored.
     * @throws DukeIoException when no date is present in the task.
     */
    public void save(Task task) throws DukeIoException {
        try {
            String parseTask = this.parser.encodeTask(task);
            FileWriter fileWriter = new FileWriter(this.fileDB, true);
            fileWriter.write(parseTask + '\n');
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeIoException();
        } catch (DukeNoDateException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads previously saved tasks.
     *
     * @param memory location to store tasks from permanent storage.
     * @throws DukeFileException when there is a problem opening the file.
     * @throws DukeDateParseException when there is a problem with a saved date.
     */
    public void loadTask(TaskList memory) throws DukeFileException, DukeDateParseException {
        assert memory.size() == 0 : "memory not clean slate!";
        try {
            Scanner scanner = new Scanner(fileDB);
            while (scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task task = parser.parseFromDB(taskData);
                memory.addTask(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeFileException(e.getMessage());
        }
    }

    /**
     * Clears the permanent storage.
     *
     * @throws DukeIoException when there is a problem opening permanent storage.
     */
    public void clearAll() throws DukeIoException {
        try {
            FileWriter fileWriter = new FileWriter(this.fileDB);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeIoException();
        }
    }
}
