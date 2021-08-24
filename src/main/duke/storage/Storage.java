package duke.storage;

import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the loading and saving of the TaskList into the hard drive.
 *
 * @author Chesterwongz
 */
public class Storage {
    private final String FILEPATH;
    private static final ArrayList<Task> storageList = new ArrayList<>();
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";

    public Storage(String filePath) {
        FILEPATH = filePath;
    }

    /**
     * Reads and loads the taskList from txt file. Creates new file if it does not exist.
     *
     * @return An ArrayList of Tasks loaded from the txt file.
     */
    public ArrayList<Task> load() {
        try {
            File myObj = new File(FILEPATH);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                switch (data.charAt(0)) {
                case 'T':
                    Task toDoTask = new ToDo(data.substring(4));
                    if (data.charAt(2) == 'X') {
                        toDoTask.markAsDone();
                    }
                    storageList.add(toDoTask);
                    break;
                case 'D':
                    readDeadline(data);
                    break;
                case 'E':
                    readEvent(data);
                    break;
                default:
                    // Do nothing and continue to scan the next line.
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            createFile();
        }
        return storageList;
    }

    private void readEvent(String data) {
        try {
            String[] splitEvent = data.split("\\|", 4);
            if (splitEvent.length == 4) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
                LocalDateTime at = LocalDateTime.parse(splitEvent[3], format);
                Task eventTask = new Event(splitEvent[2], at);
                if (data.charAt(2) == 'X') {
                    eventTask.markAsDone();
                }
                storageList.add(eventTask);
            }
        } catch (DateTimeParseException e) {
            // Invalid data, do nothing.
        }
    }
    
    private void readDeadline(String data) {
        try {
            String[] splitDeadline = data.split("\\|", 4);
            if (splitDeadline.length == 4) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
                LocalDateTime by = LocalDateTime.parse(splitDeadline[3], format);
                Task deadlineTask = new Deadline(splitDeadline[2], by);
                if (data.charAt(2) == 'X') {
                    deadlineTask.markAsDone();
                }
                storageList.add(deadlineTask);
            }
        } catch (DateTimeParseException e) {
            // Invalid data, do nothing.
        }
    }

    /**
     * Creates a file. Called in readFile().
     */
    private void createFile() {
        try {
            File myObj = new File(FILEPATH);
            //noinspection ResultOfMethodCallIgnored
            myObj.createNewFile();
        } catch (IOException e) {
            System.out.println("OOPS!!! An error occurred when creating a file!");
            e.printStackTrace();
        }
    }

    /**
     * Overwrites task list in txt file with updated list.
     */
    public void update(TaskList taskList) {
        try {
            FileWriter myWriter = new FileWriter(FILEPATH); // Overwrite mode.
            StringBuilder fileContent = new StringBuilder();
            for (Task task : taskList.getAllTasks()) {
                fileContent.append(task.toData()).append("\n");
            }
            myWriter.write(fileContent.toString());
            myWriter.close();
        } catch (IOException e) {
            System.out.println("OOPS!!! An error occurred when writing a file!");
            e.printStackTrace();
        }
    }
}
