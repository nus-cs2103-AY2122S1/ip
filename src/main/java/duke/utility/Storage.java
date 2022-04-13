package duke.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * <h2>Storage</h2>
 * A class that helps read previous task logs from local memory and write to that task log based on user input.
 */

public class Storage {

    enum EditTaskLogOperations {
        MARK_AS_COMPLETED,
        DELETE
    }

    private final String filePath;
    private String storageLoadStatusMessage = null;


    /**
     * Creates a link to a locally saved task log or creates a new file for future logging of tasks.
     * @param filePath the path to the file to read from and write to or location to create the new file if it does not
     *                exist.
     * @throws IOException if file does not exist and cannot be created.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        // try to load the file, if not, create it
        File previousLog = new File(this.filePath);
        boolean newFileCreated = previousLog.createNewFile();
        if (newFileCreated) { // file already exists
            this.storageLoadStatusMessage = "Specified file not found.\nNew file created at " + filePath
                    + "\nTasks will be saved there.";
        } else { // file already exists
            this.storageLoadStatusMessage = "Successfully established connection with file " + filePath
                    + "\nPrevious tasks imported."
                    + "\nAll changes to tasks will be saved there.";
        }
    }

    /**
     * Parses the locally saved task log from the file path provided and generates a list of tasks to import.
     * @return a list of tasks generated from parsing the locally saved task log.
     * @throws FileNotFoundException if the stipulated file can neither be read from (e.g. does not exist) nor
     * be created.
     */
    public List<Task> loadPreviousTasks() throws FileNotFoundException {
        List<Task> previousTasks = new ArrayList<Task>();
        Scanner sc = new Scanner(new File(this.filePath));
        while (sc.hasNextLine()) {
            String entry = sc.nextLine();
            String[] tokens = entry.split(";");
            boolean isCompleted = tokens[1].equals("<T>");
            String taskName = tokens[2];
            // format: duke.task type | isCompleted | event name | date/time
            switch (tokens[0]) {
            case "T":
                previousTasks.add(ToDo.createTask(taskName, isCompleted));
                break;
            case "E":
                previousTasks.add(Event.createTask(taskName, isCompleted, LocalDateTime.parse(tokens[3].trim(),
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
                break;
            default: // "D"
                assert tokens[0].equals("D") : "invalid task type in taskLog";
                previousTasks.add(Deadline.createTask(taskName, isCompleted, LocalDateTime.parse(tokens[3].trim(),
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
                break;
            };
        }
        return previousTasks;
    }


    void append(String taskType, String isCompleted, String detail) throws IOException {
        assert isCompleted.equals("<T>") || isCompleted.equals("<F>") : "Invalid task completion status";
        FileWriter fw = new FileWriter(this.filePath, true); // append flag true -> append, not overwrite
        fw.write(taskType + ";" + isCompleted + ";" + detail + "\n");
        fw.close();
    }


    void append(String taskType, String isCompleted, String taskName, String dateTime) throws IOException {
        this.append(taskType, isCompleted, taskName + ";" + dateTime);
    }


    void modifyExistingLog(int lineToChange, EditTaskLogOperations operation) throws IOException {
        int currentLine = 0;
        Scanner sc = new Scanner(new File(this.filePath));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            String entry = sc.nextLine();
            if (currentLine != lineToChange) {
                sb.append(entry);
            } else { // generate new entry
                if (operation == EditTaskLogOperations.MARK_AS_COMPLETED) {
                    sb.append(entry.replaceAll(";<F>;", ";<T>;"));
                } else if (operation == EditTaskLogOperations.DELETE) {
                    currentLine++;
                    continue;
                }
            }
            sb.append("\n");
            currentLine++;
        }
        FileWriter fw = new FileWriter(this.filePath, false); // append flag false -> overwrite file
        fw.write(sb.toString());
        fw.close();
        sc.close();
    }


    public String getStorageLoadStatusMessage() {
        return this.storageLoadStatusMessage;
    }
}
