package duke.components;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the storage file that Duke can read from and write to.
 */
public class Storage {
    private final File dataFile;
    private final String filePath;

    public Storage(String filePath) {
        this.dataFile = new File(filePath); // "./data/data.txt"
        this.filePath = filePath;
    }

    /**
     * Reads tasks stored in data file and add them into TaskList.
     * Creates the date file if it does not exist.
     * @param taskList The Task List of Duke to receive tasks from data file.
     */
    public void loadInto(TaskList taskList) {

        String dataPath = "./data/data.txt";

        try {
            Path path = Paths.get(dataPath);
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            System.out.println("Directories for data.txt are created.");
        } catch (IOException e) {
            System.out.println("Failed to create data directories (they may already exist)" + e.getMessage());
        }

        // a List to store all Tasks read from data.txt
        ArrayList<String> dataRead = new ArrayList<>();

        // if data.txt exists
        try {
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                dataRead.add(scanner.nextLine());
            }
            for (int i = 0; i < dataRead.size(); i++) {
                String toAdd = dataRead.get(i);

                // case Todo
                if (toAdd.charAt(1) == 'T') {
                    String[] parts = toAdd.split("] ");
                    String todoName = parts[1];
                    Todo todoToAdd = new Todo(todoName);
                    if (toAdd.charAt(4) == 'X') {
                        todoToAdd.setDone();
                    }
                    taskList.addTaskFromDataFile(todoToAdd);
                }

                // case Deadline
                if (toAdd.charAt(1) == 'D') {
                    String[] parts1 = toAdd.split("] ");
                    String nameTime = parts1[1];
                    String[] parts2 = nameTime.split(" \\(by: ");
                    String ddlName = parts2[0];
                    String ddlTimeStr = parts2[1].substring(0, parts2[1].length() - 1);
                    DateTimeFormatter formatterDdl = DateTimeFormatter.ofPattern(
                            "EEE, dd/MMM/yyyy hh:mm a");
                    LocalDateTime dateTime = LocalDateTime.parse(ddlTimeStr, formatterDdl);
                    Deadline ddlToAdd = new Deadline(ddlName, dateTime);
                    if (toAdd.charAt(4) == 'X') {
                        ddlToAdd.setDone();
                    }
                    taskList.addTaskFromDataFile(ddlToAdd);
                }

                // case Event
                if (toAdd.charAt(1) == 'E') {
                    String[] parts1 = toAdd.split("] ");
                    String nameTime = parts1[1];
                    String[] parts2 = nameTime.split(" \\(at: ");
                    String eveName = parts2[0];
                    String eveTimeStr = parts2[1].substring(0, parts2[1].length() - 1);
                    DateTimeFormatter formatterEve = DateTimeFormatter.ofPattern(
                            "EEE, dd/MMM/yyyy hh:mm a");
                    LocalDateTime dateTime = LocalDateTime.parse(eveTimeStr, formatterEve);
                    Event eveToAdd = new Event(eveName, dateTime);
                    if (toAdd.charAt(4) == 'X') {
                        eveToAdd.setDone();
                    }
                    taskList.addTaskFromDataFile(eveToAdd);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("data.txt is not found." + e.getMessage());
        }
    }

    /**
     * Writes a task to the data file.
     * @param taskToWrite The string representation of the task to be written.
     */
    public void writeTaskToFile(String taskToWrite) {
        try {
            writeToFile(filePath, taskToWrite);
        } catch (IOException e) {
            System.out.println("Something is wrong... " + e.getMessage());
        }
    }

    /**
     * Appends a task to the end of the data file.
     * @param taskToAppend The string representation of the task to be appended.
     */
    public void appendTaskToFile(String taskToAppend) {
        try {
            appendToFile(filePath, taskToAppend);
        } catch (IOException e) {
            System.out.println("Something is wrong... " + e.getMessage());
        }
    }

    /**
     * Appends a text to the end of a file.
     * @param filePath The path to the file to be appended to.
     * @param textToAppend The text to be appended.
     * @throws IOException If there is an error while appending.
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Writes a text to the end of a file.
     * @param filePath The path to the file to be written into.
     * @param textToAdd The text to be written.
     * @throws IOException If there is an error while writing.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Erases the content of a file.
     */
    public void eraseFileContent() {
        try {
            new FileWriter(filePath, false).close();
        } catch (IOException e) {
            System.out.println("Something is wrong... " + e.getMessage());
        }
    }

}
