package duke;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;


/**
 * Class that handles storage for a user's data inside a hard disk. Data is saved as a pre-formatted text file
 *
 * @author Aiken Wong
 */
public class Storage {
    protected String filePath;

    /**
     * Initialises Storage object.
     *
     * @param filePath Specifies the directory and filename of the stored user data in the hard disk.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads up user data from the hard disk using the filePath indicated in the Storage instance.
     *
     * @return TaskList containing all the user's current tasks stored in hard disk
     * @throws DukeException
     */
    public TaskList loadTasks() throws DukeException {
        createDirectory();
        File taskList = new File(this.filePath);
        ArrayList<Task> taskArrayList = new ArrayList<Task>();

        try {
            if (taskList.exists()) {
                Scanner scanner = new Scanner(taskList);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();

                    switch (line.charAt(2)) {
                    case 'D':
                        loadDeadline(line, taskArrayList);
                        break;
                    case 'E':
                        loadEvent(line, taskArrayList);
                        break;
                    case 'T':
                        loadTodo(line, taskArrayList);
                        break;
                    default:
                        throw new DukeException("Cannot load task of unknown type");
                    }
                }
            } else {
                taskList.createNewFile();

                assert taskList.exists() : "file at path " + this.filePath + " should exist";
                return new TaskList(new ArrayList<>());
            }

        } catch (DukeException | IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
        return new TaskList(taskArrayList);
    }

    private void createDirectory() {
        File dataDir = new File(this.filePath.substring(0, this.filePath.lastIndexOf('/')));
        dataDir.mkdirs();
    }


    /**
     * Saves the user's tasks into the hard disk. Data is stored as a formatted text file.
     *
     * @param tasks Tasks stored into hard disk.
     */
    public void saveTasks(TaskList tasks) {
        createDirectory();
        File taskListFile = new File(this.filePath);

        String storageString = tasks.getStorageString();

        try {
            if (!taskListFile.exists()) {
                taskListFile.createNewFile();
            }
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(storageString);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    private void loadDeadline(String line, ArrayList<Task> taskArrayList) {
        String[] storedValues = line.substring(5).split("[|]", 4);
        boolean isDone = storedValues[0].trim().equals("X");
        boolean isDateOnly = storedValues[1].trim().equals("X");
        LocalDateTime date = LocalDateTime.parse(storedValues[2].trim());
        String description = storedValues[3].trim();
        taskArrayList.add(new Deadline(description, date, isDone, isDateOnly));
    }

    private void loadEvent(String line, ArrayList<Task> taskArrayList) {
        String[] storedValues = line.substring(5).split("[|]", 5);
        boolean isDone = storedValues[0].trim().equals("X");
        boolean isDateOnly = storedValues[1].trim().equals("X");
        LocalDateTime startDate = LocalDateTime.parse(storedValues[2].trim());
        LocalDateTime endDate = LocalDateTime.parse(storedValues[3].trim());
        String description = storedValues[4].trim();
        taskArrayList.add(new Event(description, startDate, endDate, isDone, isDateOnly));
    }

    private void loadTodo(String line, ArrayList<Task> taskArrayList) {
        String[] storedValues = line.substring(5).split("[|]", 2);
        boolean isDone = storedValues[0].trim().equals("X");
        String description = storedValues[1].trim();
        taskArrayList.add(new Todo(description, isDone));
    }

}
