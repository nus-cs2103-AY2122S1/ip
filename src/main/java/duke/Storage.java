package duke;

import tasks.*;

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

    /**
     * Reads the file to retrieve all the tasks.
     * 
     * @return The TaskList with all the tasks present in the file.
     * @throws IOException Handles any errors that can occur when interacting with the file.
     */
    public static TaskList readFile() throws IOException {
        checkForFile(Storage.PATH);
        Scanner sc = new Scanner(new File(Storage.PATH));
        sc.useDelimiter(Pattern.compile("(\\n)| - "));
        ArrayList<Task> tasks = new ArrayList();

        while(sc.hasNext()) {
            String t = sc.next();
            int i = Integer.parseInt(sc.next());
            String desc = sc.next();
            Task.Priority priority;
            int descLen = desc.length();
            String newDesc;

            switch(t) {
            case "T":
                String p = sc.next();
                p.toUpperCase();
                if (p.contains("H")) {
                    priority = Task.Priority.HIGH;
                    newDesc = desc.substring(0, descLen - 4);
                } else if (p.contains("M")) {
                    priority = Task.Priority.MEDIUM;
                    newDesc = desc.substring(0, descLen - 6);
                } else {
                    priority = Task.Priority.LOW;
                    newDesc = desc.substring(0, descLen);
                }
                Task task = new Todo(newDesc, priority);
                if (i == 1) {
                    task.taskDone();
                }
                tasks.add(task);
                break;
            case "D":
                String by = sc.next();
                p = sc.next();
                p.toUpperCase();
                if (p.contains("H")) {
                    priority = Task.Priority.HIGH;
                    newDesc = desc.substring(0, descLen - 4);
                } else if (p.contains("M")) {
                    priority = Task.Priority.MEDIUM;
                    newDesc = desc.substring(0, descLen - 6);
                } else {
                    priority = Task.Priority.LOW;
                    newDesc = desc.substring(0, descLen);
                }
                task = new Deadline(newDesc, by, priority);
                if (i == 1) {
                    task.taskDone();
                }
                tasks.add(task);
                break;
            case "E":
                String at = sc.next();
                p = sc.next();
                p.toUpperCase();
                if (p.contains("H")) {
                    priority = Task.Priority.HIGH;
                    newDesc = desc.substring(0, descLen - 4);
                } else if (p.contains("M")) {
                    priority = Task.Priority.MEDIUM;
                    newDesc = desc.substring(0, descLen - 6);
                } else {
                    priority = Task.Priority.LOW;
                    newDesc = desc.substring(0, descLen);
                }
                task = new Events(newDesc, at, priority);
                if (i == 1) {
                    task.taskDone();
                }
                tasks.add(task);
                break;
            }
        }
        sc.close();
        return new TaskList(tasks);
    }

    /**
     * Retrieve all the existing tasks in TaskList and write it to the file. 
     * Rewrites the file everytime thus preventing duplicates.
     * 
     * @param tasks A List of tasks after the bot has been in use.
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
