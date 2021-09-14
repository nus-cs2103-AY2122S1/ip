package jarvis.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import jarvis.exception.InvalidDateTimeInputException;
import jarvis.exception.InvalidStorageTaskException;
import jarvis.exception.StorageFileException;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.task.Task;
import jarvis.task.TaskList;
import jarvis.task.Todo;

/**
 * Encapsulates a storage object that handles all hard-disk file input and output.
 */
public class Storage {
    private File file;

    /**
     * Constructor for Storage.
     *
     * @param filePath The path of file in which the data is stored.
     * @throws StorageFileException If there is an error when loading the file.
     */
    public Storage(String filePath) throws StorageFileException {
        this.file = new File(filePath);
        if (!file.isFile()) {
            try {
                this.file.getParentFile().mkdirs();
                this.file.createNewFile();
            } catch (IOException e) {
                throw new StorageFileException("Error while loading storage file!");
            }
        }
    }

    /**
     * Loads the tasks from the storage file into an array list.
     *
     * @return A list of tasks.
     * @throws InvalidStorageTaskException If the file contains an invalid task.
     * @throws StorageFileException If there is an error when trying to read data from storage file.
     * @throws InvalidDateTimeInputException If the date time input is invalid in storage file.
     */
    public ArrayList<Task> loadTasksFromFile()
            throws InvalidStorageTaskException, StorageFileException, InvalidDateTimeInputException {
        Scanner s;

        try {
            s = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            throw new StorageFileException("Storage file not found!");
        }

        ArrayList<Task> taskList = new ArrayList<>();

        while (s.hasNextLine()) {
            String[] readLine = s.nextLine().split(";;;");
            if (readLine.length < 3) {
                throw new StorageFileException("Storage file has improper format!");
            }

            String taskType = readLine[0];
            boolean isDone = readLine[1].equals("1");
            Task task;

            switch (taskType) {
            case "T":
                task = new Todo(readLine[2]);
                break;
            case "D":
                task = new Deadline(readLine[2], readLine[3]);
                break;
            case "E":
                task = new Event(readLine[2], readLine[3], readLine[4], readLine[5]);
                break;
            default:
                throw new InvalidStorageTaskException();
            }

            if (isDone) {
                task.markAsDone();
            }
            taskList.add(task);
        }

        return taskList;
    }

    /**
     * Appends the entry to the storage file.
     *
     * @param entry The entry to append.
     * @throws StorageFileException If there is an error writing to storage file.
     */
    public void addToStorageFile(String entry) throws StorageFileException {
        try {
            FileWriter writer = new FileWriter(this.file, true);
            writer.write(entry + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            throw new StorageFileException("Error when writing task to storage file!");
        }
    }

    /**
     * Rewrites the storage file using the list of tasks provided.
     *
     * @param taskList The list of tasks.
     * @throws StorageFileException If there is an error when rewriting the storage file.
     */
    public void rewriteStorageFile(TaskList taskList) throws StorageFileException {
        try {
            FileWriter writer = new FileWriter(this.file);
            writer.write(taskList.toStorageFormatString());
            writer.close();
        } catch (IOException e) {
            throw new StorageFileException("Error when rewriting task(s) to storage file!");
        }
    }

    /**
     * Rewrites the storage file using string provided.
     *
     * @param storageFileContent The string that is the new content of the storage file.
     * @throws StorageFileException If there is an error when rewriting the storage file.
     */
    public void rewriteStorageFile(String storageFileContent) throws StorageFileException {
        try {
            FileWriter writer = new FileWriter(this.file);
            writer.write(storageFileContent);
            writer.close();
        } catch (IOException e) {
            throw new StorageFileException("Error when rewriting string to storage file!");
        }
    }
}
