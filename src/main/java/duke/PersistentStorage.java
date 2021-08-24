package duke;

import duke.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class PersistentStorage {
    private String filepath;
    
    public PersistentStorage(String filepath) {
        this.filepath = filepath;
    }

    public Tasklist loadTasks() throws DukeException {
        File file = new File(this.filepath);
        if (!file.exists()) {
            // File doesn't exist
            // Create the necessary files and load an empty Tasklist
            file.getParentFile().mkdirs();
            return new Tasklist();

        } else {
            // File exists
            // Read data from file
            try {
                Scanner fileReader = new Scanner(file);
                Tasklist storedTasks = new Tasklist();

                String fileData = "";
                while (fileReader.hasNext()) {
                    fileData += fileReader.nextLine() + "\n";
                }

                // Check if fileData is empty
                if (fileData.equals("")) {
                    fileReader.close();
                    return new Tasklist();
                }

                // Parse fileData and return a Tasklist
                String[] allTasks = fileData.split("\n");

                for (String task : allTasks) {
                    //Split task string into tokens
                    String[] tokens = task.split(" \\| ");

                    String taskType = tokens[0];
                    boolean isDone = (tokens[1].equals("1") ? true : false);
                    String description = tokens[2];
                    String rawDateTimeInfo;

                    if (tokens.length == 3) {
                        ToDo item = new ToDo(description, isDone);
                        storedTasks.addTask(item);

                    } else {
                        rawDateTimeInfo = tokens[3];
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        LocalDateTime dateTimeInfo = LocalDateTime.parse(rawDateTimeInfo, formatter);
                        if (taskType.equals("D")) {
                            // Task is a Deadline
                            Deadline item = new Deadline(description, dateTimeInfo, isDone);
                            storedTasks.addTask(item);
                        } else {
                            // Task is an Event
                            Event item = new Event(description, dateTimeInfo, isDone);
                            storedTasks.addTask(item);
                        }
                    }
                }
                fileReader.close();
                return storedTasks;

            } catch (FileNotFoundException e) {
                throw new DukeException("Error loading file!");
            }
        }
    }

    public boolean saveTasks(Tasklist tasklist) throws DukeException {
        File file = new File(this.filepath);
        
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            // Convert tasks into string representation
            String taskData = "";
            ArrayList<Task> allTasks = tasklist.getAllTasks();
            for (int i = 0; i < tasklist.getTotalTasks(); i++) {
                Task task = allTasks.get(i);
                taskData += (i == tasklist.getTotalTasks() - 1 
                    ? task.getFileRepr()
                    : task.getFileRepr() + "\n");
            }

            // Write data to file
            FileWriter writer = new FileWriter(file);
            writer.write(taskData);
            writer.close();

            return true;

        } catch (IOException e) {
            throw new DukeException("An error occurred while trying to save data to your file :(");
        }
        
    }
}
