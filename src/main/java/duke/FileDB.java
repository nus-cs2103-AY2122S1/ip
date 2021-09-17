package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * @FileDB represents a storage for Tasks added.
 */
public class FileDB {
    /** Defines a default saved file location. */
    private static String DEFAULT_SAVE = "storage.txt";
    private File fileDB;
    private boolean isFileExists;
    private Parser parser;


    public FileDB(TaskList taskList) throws DukeIOException, DukeDateParseException {
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
            throw new DukeIOException();
        }
    }

    /**
     * Creates a FileDB instance.
     *
     * @param location the location of where the file should be stored at.
     * @throws DukeIOException when the file is at an inaccessible location.
     */
    public FileDB(String location) throws DukeIOException{
        this.fileDB = new File(location);
        this.parser = new Parser();
        this.isFileExists = false;
        try {
            this.isFileExists = this.fileDB.createNewFile();
        } catch (IOException e) {
            throw new DukeIOException();
        }
    }

    /**
     * Saves the task into the storage.
     *
     * @param task the task to be stored.
     * @throws DukeIOException when no date is present in the task.
     */
    public void save(Task task) throws DukeIOException {
        try {
            String parseTask = this.parser.parseTask(task);
            FileWriter fileWriter = new FileWriter(this.fileDB, true);
            fileWriter.write(parseTask + '\n');
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeIOException();
        } catch (DukeNoDateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void loadTask(TaskList memory) throws DukeFileException, DukeDateParseException {
        assert memory.size() == 0 : "memory not clean slate!";
        try {
            Scanner scanner = new Scanner(fileDB);
            while(scanner.hasNextLine()) {
                String taskData = scanner.nextLine();
                Task task = parser.parseFromDB(taskData);
                memory.addTask(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeFileException(e.getMessage());
        }
    }

    public void clearAll() throws DukeIOException {
        try {
            FileWriter fileWriter = new FileWriter(this.fileDB);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeIOException();
        }

    }
}
