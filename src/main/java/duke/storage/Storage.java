package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.data.TaskList;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.ToDo;

/**
 * This class handles the loading and saving of the TaskList into the hard drive.
 *
 * @author Chesterwongz
 */
public class Storage {
    private static final ArrayList<Task> storageList = new ArrayList<>();
    private static final String DATE_TIME_FORMAT = "dd-MM-yyyy HH:mm";
    @SuppressWarnings("checkstyle:MemberName")
    private final String FILEPATH;

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
                tokenize(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            createFile();
        }
        return storageList;

    }

    private void tokenize(String data) {
        switch (data.charAt(0)) {
        case 'T':
            readToDo(data);
            break;
        case 'D':
            readDeadline(data);
            break;
        case 'E':
            readEvent(data);
            break;
        default:
            // Do nothing.
        }
    }
    private void readDoneStatus(Task task, String data) {
        if (data.charAt(2) == 'X') {
            task.markAsDone();
        }
        storageList.add(task);
    }
    private void readToDo(String data) {
        Task toDoTask = new ToDo(data.substring(4));
        readDoneStatus(toDoTask, data);
    }
    private void readEvent(String data) {
        try {
            String[] splitEvent = data.split("\\|", 4);
            if (splitEvent.length != 4) {
                return;
            }
            DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
            LocalDateTime at = LocalDateTime.parse(splitEvent[3], format);
            Task eventTask = new Event(splitEvent[2], at);
            readDoneStatus(eventTask, data);
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
                readDoneStatus(deadlineTask, data);
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
