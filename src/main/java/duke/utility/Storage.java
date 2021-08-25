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

public class Storage {
    
    private final String filePath;
    
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
    
    public List<Task> loadPreviousTasks() throws FileNotFoundException {
        List<Task> prevTasks = new ArrayList<Task>();
        Scanner s = new Scanner(new File(this.filePath));
        while (s.hasNextLine()) {
            String task = s.nextLine();
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
    
    void changeTaskLogToCompleted(int lineNum) throws IOException {
        int i = 0;
        Scanner sc = new Scanner(new File(this.filePath));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) { // read the entire file except the line to change, which would be ignored
            String entry = sc.nextLine();
            if (i != lineNum) { // line to be modified
                sb.append(entry);
            } else { // generate new entry
                sb.append(entry.replaceAll(";F;", ";T;"));
            }
            sb.append("\n");
            i++;
        }
        FileWriter fw = new FileWriter(this.filePath, false); // append false -> overwrite file
        fw.write(sb.toString());
        fw.close();
        sc.close();
    }
    
    void append(String e, String f, String taskName, String dateTime) throws IOException {
        this.append(e, f, taskName + ";" + dateTime);
    }
    
    void append(String type, String isCompleted, String detail) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true); // append flag set to true to prevent clearing
        fw.write(type + ";" + isCompleted + ";" + detail + "\n");
        fw.close();
    }

    void deleteTaskLogEntry(int lineIdx) throws IOException {
        int i = 0;
        Scanner sc = new Scanner(new File(this.filePath));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) { // read the entire file except the line to change, which would be ignored
            String entry = sc.nextLine();
            if (i != lineIdx) { // line to be modified
                sb.append(entry);
                sb.append("\n");
            }
            i++;
        }
        FileWriter fw = new FileWriter(this.filePath, false); // append false -> overwrite file
        fw.write(sb.toString());
        fw.close();
        sc.close();   
    }
}
