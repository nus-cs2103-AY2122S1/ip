package duke.utility;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <h2>Storage</h2>
 * A class that helps read previous task logs from local memory and write to that task log based on user input.
 */

public class Storage {
    
    private final String filePath;

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
        if (! previousLog.createNewFile()) { // file already exists
            System.out.println("Successfully established connection with file " + filePath
                    + "\nPrevious duke.task log imported."
                    + "\nAll changes to task log will be saved there.");
        } else { // new file is created
            System.out.println("Specified file not found.\nNew file created: " + filePath
                    + "\nTask log will be saved there.");
        }
    }
    
    /**
     * Parses the locally saved task log from the file path provided and generates a list of tasks to import.
     * @return a list of tasks generated from parsing the locally saved task log.
     * @throws FileNotFoundException if the stipulated file can neither be read from (e.g. does not exist) nor
     * be created.
     */
    public List<Task> loadPreviousTasks() throws FileNotFoundException {
        List<Task> prevTasks = new ArrayList<Task>();
        Scanner sc = new Scanner(new File(this.filePath));
        while (sc.hasNextLine()) {
            String task = sc.nextLine();
            String[] tokens = task.split(";");
            boolean isCompleted = tokens[1].equals("T");
            String taskName = tokens[2];
            // format: duke.task type | isCompleted | event name | date/time
            switch (tokens[0]) {
            case "T":
                prevTasks.add(ToDo.createTask(taskName, isCompleted));
                break;
            case "E":
                prevTasks.add(Event.createTask(taskName, isCompleted, LocalDateTime.parse(tokens[3].trim(),
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
                break;
            case "D":
                prevTasks.add(Deadline.createTask(taskName, isCompleted, LocalDateTime.parse(tokens[3].trim(),
                        DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
                break;
            };
        }
        return prevTasks;
    }

    void append(String e, String f, String taskName, String dateTime) throws IOException {
        this.append(e, f, taskName + ";" + dateTime);
    }

    void append(String type, String isCompleted, String detail) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true); // append flag true -> append, not overwrite
        fw.write(type + ";" + isCompleted + ";" + detail + "\n");
        fw.close();
    }
    
    void changeTaskLogToCompleted(int lineNum) throws IOException {
        int currentLine = 0;
        Scanner sc = new Scanner(new File(this.filePath));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) { // read the entire file except the line to change, which is ignored
            String entry = sc.nextLine();
            if (currentLine != lineNum) { // line to be modified
                sb.append(entry);
            } else { // generate new entry
                sb.append(entry.replaceAll(";F;", ";T;"));
            }
            sb.append("\n");
            currentLine++;
        }
        FileWriter fw = new FileWriter(this.filePath, false); // append flag false -> overwrite file
        fw.write(sb.toString());
        fw.close();
        sc.close();
    }

    void deleteTaskLogEntry(int lineIdx) throws IOException {
        int currentLine = 0;
        Scanner sc = new Scanner(new File(this.filePath));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) { // read the entire file except the line to change, which would be ignored
            String entry = sc.nextLine();
            if (currentLine != lineIdx) { // line to be modified
                sb.append(entry);
                sb.append("\n");
            }
            currentLine++;
        }
        FileWriter fw = new FileWriter(this.filePath, false); // append false -> overwrite file
        fw.write(sb.toString());
        fw.close();
        sc.close();   
    }
}
