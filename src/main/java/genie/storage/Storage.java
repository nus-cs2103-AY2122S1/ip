package genie.storage;

import genie.tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private static final String DIRECTORY = "src/main/data";
    private static final String FILENAME = "duke.txt";
    private static final String PATH = DIRECTORY + "/" + FILENAME;

    /**
     * Checks if file and directory exist, creates new file or directory 
     * if they do not exist.
     * 
     * @param path String of the path to look up
     * @throws IOException Handles any errors that can occur when interacting with the file.
     */
    public static void checkForFile(String path) throws IOException {
        File file = new File(path);
        File dir = new File(DIRECTORY);

        if (!dir.exists()) {
            dir.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static String[] parseTaskDetails(Scanner lineScanner) {
        int size = 0;
        String[] taskDetails = new String[4];
        while (lineScanner.hasNext()) {
            taskDetails[size] = lineScanner.next();
            size++;
        }
        lineScanner.close();
        return taskDetails;
    }


    /**
     * Reads the file to retrieve all the genie.tasks.
     * 
     * @return The TaskList with all the genie.tasks present in the file.
     * @throws IOException Handles any errors that can occur when interacting with the file.
     */

    public static TaskList readFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        checkForFile(Storage.PATH);

        Scanner fileScanner = new Scanner(new File(PATH));
        while (fileScanner.hasNext()) {
            tasks.add(readEachTask(fileScanner));
        }
        fileScanner.close();
        return new TaskList(tasks);
    }
    
    public static Task readEachTask(Scanner fileScanner) throws IOException {
        Scanner lineScanner = new Scanner(fileScanner.nextLine());
        lineScanner.useDelimiter(Pattern.compile("(\\n)| - "));

        String taskCode = lineScanner.next();

        String[] taskDetails = parseTaskDetails(lineScanner);
        boolean isMarkedDone = taskDetails[0].equals("1");
        String description = taskDetails[1];
        String p = taskDetails[2];
        String date = taskDetails.length > 3 ? taskDetails[3] : null;
        
            Task.Priority priority;

            switch(taskCode) {
            case "T":
                p.toUpperCase();
                if (p.contains("HIGH")) {
                    priority = Task.Priority.HIGH;
                } else if (p.contains("MEDIUM")) {
                    priority = Task.Priority.MEDIUM;
                } else {
                    priority = Task.Priority.LOW;
                }
                Task task = new Todo(description, priority);
                if (isMarkedDone) {
                    task.taskDone();
                }
                return task;
            case "D":
                p.toUpperCase();
                if (p.contains("HIGH")) {
                    priority = Task.Priority.HIGH;
//                    newDesc = desc.substring(0, descLen - 4);
                } else if (p.contains("MEDIUM")) {
                    priority = Task.Priority.MEDIUM;
//                    newDesc = desc.substring(0, descLen - 6);
                } else {
                    priority = Task.Priority.LOW;
//                    newDesc = desc.substring(0, descLen);
                }
                task = new Deadline(description, date, priority);
                if (isMarkedDone) {
                    task.taskDone();
                }
                return task;
            case "E":
                p.toUpperCase();
                if (p.contains("HIGH")) {
                    priority = Task.Priority.HIGH;
//                    newDesc = desc.substring(0, descLen - 4);
                } else if (p.contains("MEDIUM")) {
                    priority = Task.Priority.MEDIUM;
//                    newDesc = desc.substring(0, descLen - 6);
                } else {
                    priority = Task.Priority.LOW;
//                    newDesc = desc.substring(0, descLen);
                }
                task = new Events(description, date, priority);
                if (isMarkedDone) {
                    task.taskDone();
                }
                return task;
            }
        
        return new Task("", Task.Priority.HIGH);
    }


    /**
     * Retrieve all the existing genie.tasks in TaskList and write it to the file. 
     * Rewrites the file everytime thus preventing duplicates.
     * 
     * @param tasks A List of genie.tasks after the bot has been in use.
     */
    public static void getAllTasks(List<Task> tasks) {
        try {
            String all = "";
            for (Task t : tasks) {
                all += t.toStringForFile() + "\n";
                overWrite(DIRECTORY + "/" + FILENAME, all);
            }
        }
        catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Adds a new task to the file. 
     * 
     * @param task the Task to write to File
     */
    public static void writeLine(Task task) {
        try {
            addToFile(DIRECTORY + "/" + FILENAME, task.toStringForFile() + "\n");
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /**
     * Adds the task to the file
     * 
     * @param path Path of the file to access
     * @param line The String to write to the file
     * @throws IOException
     */
    public static void addToFile(String path, String line) throws IOException {
        checkForFile(path);
        FileWriter writer = new FileWriter(path, true);
        writer.write(line);
        writer.close();
    }

    /**
     * Adds the task to the file
     *
     * @param path Path of the file to access
     * @param line The String to write to the file
     * @throws IOException
     */
    public static void overWrite(String path, String line) throws IOException {
        checkForFile(path);
        FileWriter writer = new FileWriter(path);
        writer.write(line);
        writer.close();
    }

    /**
     * Method that reformats the date input by the user.
     * 
     * @param unformattedDate The Date to format
     * @return Formatted version of the date
     * @throws DateTimeParseException Error that is thrown if the unformatted date 
     * has issues
     */
    public static String formatDate(String unformattedDate) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(unformattedDate);
        return date.format(DateTimeFormatter.ofPattern("d MMM uuu"));
    }
}
