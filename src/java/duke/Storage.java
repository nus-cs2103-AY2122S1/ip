package duke;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import tasks.*;

public class Storage {
    private static final String DIRECTORY = "src/data";
    private static final String FILENAME = "duke.txt";
    private static final String PATH = DIRECTORY + "/" + FILENAME;

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

    public static TaskList readFile() throws IOException {

        checkForFile(Storage.PATH);
        Scanner sc = new Scanner(new File(Storage.PATH));
        sc.useDelimiter(Pattern.compile("(\\n)| - "));
        
        ArrayList<Task> tasks = new ArrayList();

        while(sc.hasNext()) {
            String t = sc.next();

            switch(t) {
                case "T":
                    int i = Integer.parseInt(sc.next());
                    String desc = sc.next();
                    Task task = new Todo(desc);
                    if (i == 1) {
                        task.taskDone();
                    }
                    tasks.add(task);
                    break;
                case "D":
                    i = Integer.parseInt(sc.next());
                    desc = sc.next();
                    String by = sc.next();
                    task = new Deadline(desc, by);
                    if (i == 1) {
                        task.taskDone();
                    }
                    tasks.add(task);
                    break;
                case "E":
                    i = Integer.parseInt(sc.next());
                    desc = sc.next();
                    String at = sc.next();
                    task = new Events(desc, at);
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

    public static void writeLine(Task task) {
        try {
            addToFile(DIRECTORY + "/" + FILENAME, task.toStringForFile() + "\n");
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void addToFile(String path, String line) throws IOException {
        checkForFile(path);
        FileWriter writer = new FileWriter(path, true);
        writer.write(line);
        writer.close();
    }

    public static void overWrite(String path, String line) throws IOException {
        checkForFile(path);
        FileWriter writer = new FileWriter(path);
        writer.write(line);
        writer.close();
    }

    public static String formatDate(String unformattedDate) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(unformattedDate);
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
