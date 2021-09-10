package aisu.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import aisu.exception.AisuException;
import aisu.task.Deadline;
import aisu.task.Event;
import aisu.task.Task;
import aisu.task.Todo;
import aisu.tasklist.TaskList;

/**
 * A storage to save and load tasks from the tasklist.
 *
 * @author Liaw Xin Yan
 */
@SuppressWarnings("ALL")
public class Storage {
    private final String dirPath;
    private final String fileName;
    private File file;

    /**
     * Constructor to initialise the storage with a directory path and file name for storing the tasklist in.
     * @param dirPath Directory pathname for where the text file should be saved at.
     * @param fileName The name of the text file to store the data.
     */
    public Storage(String dirPath, String fileName) {
        this.dirPath = dirPath;
        this.fileName = fileName;
        this.createFile();
    }

    /**
     * Creates a file to store tasklist.
     *
     * @return A boolean to represent whether the file was successfully created.
     */
    private boolean createFile() {
        System.out.println("Checking for old directories...");
        System.out.println(new File(this.dirPath).mkdirs() ? "New directory created." : "Directory already exists.");
        // if directory does not exist, create it.
        // using .exists() to check is not a good solution! Use .mkdirs() directly,
        // it will not throw an error if it does exist and will not overwrite an existing directory.

        try {
            this.file = new File(this.dirPath + "/" + this.fileName);
            System.out.println("Checking for existing file: " + this.fileName + "...");
            System.out.println(this.file.createNewFile()
                    ? "New " + this.fileName + " created."
                    : this.fileName + " already exists.");
            return true;
        } catch (IOException e) {
            System.out.println("Error: File cannot be created.");
            return false;
        }
    }

    /**
     * Loads tasklist from existing file.
     *
     * @return Loaded tasklist.
     * @throws AisuException If file doesn't exist at filepath.
     */
    public List<Task> load() throws AisuException {
        List<Task> result = new ArrayList<>();
        try {
            Scanner s = new Scanner(this.file);
            while (s.hasNext()) {

                String[] temp = s.nextLine().split(";;");
                Task currTaskToLoad;
                String taskLetter = temp[0];
                boolean isMarkedDone = temp[1].equals("1");

                switch(taskLetter) {
                case "T":
                    currTaskToLoad = new Todo(temp[2]);
                    break;
                case "D":
                    currTaskToLoad = new Deadline(temp[2], temp[3]);
                    break;
                case "E":
                    currTaskToLoad = new Event(temp[2], temp[3]);
                    break;
                default:
                    throw new AisuException("No such task! Unchecked Exception Error...");
                }

                if (isMarkedDone) {
                    currTaskToLoad.markAsDone();
                }

                // Add tags to task if there are tags
                String[] tags = s.nextLine().split(";;");
                for (String tagName : tags) {
                    currTaskToLoad.addTag(tagName);
                }

                result.add(currTaskToLoad);
            }
            return result;
        } catch (FileNotFoundException e) {
            throw new AisuException("File not found! Unable to load previous tasklist.");
        }
    }

    /**
     * Saves the tasklist to the storage at the filepath.
     *
     * @param currList The current tasklist to be saved.
     */
    public void save(TaskList currList) throws AisuException {
        // save list to file in format with ';;' as dividers and on a new line for each entry. Rewrites the entire file.
        List<Task> list = currList.getListData();
        try {
            FileWriter fw = new FileWriter(this.dirPath + "/" + this.fileName);

            for (Task t : list) {
                fw.write(t.parseData() + System.lineSeparator());
            }

            fw.close();

        } catch (IOException e) {
            System.out.println("Error saving file. Attempting to create the file...");

            // if file/folder doesn't exist, create a new one and call save again.
            boolean hasCreatedFile = this.createFile();

            if (hasCreatedFile) {
                this.save(currList);
            } else {
                throw new AisuException("There is something wrong with the system.");
            }
        }
    }

}
