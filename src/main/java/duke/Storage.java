package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {
    /** Path string to data of tasks to load/save */
    private final static String PATH = "data/tasks.txt";

    /**
     * Loads tasks from task text file if exists, otherwise start new text file
     */
    public static void loadTasks(TaskList taskList) {
        File tasks = new File(PATH);
        if (tasks.exists()) {
            // Read tasks from text file
            Scanner s = null;
            try {
                s = new Scanner(tasks);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (s.hasNext()) {
                // Parse string to get task type, status, and description
                String regex = "\\[(?<type>[A-Z])\\]\\[(?<status>[X\\s])\\]\\s(?<desc>.*)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(s.nextLine());

                // Check task type, status, description
                while (matcher.find()) {
                    String type = matcher.group("type");
                    String desc = matcher.group("desc");

                    Task toAdd;
                    if (type.equals("T")) {
                        toAdd = new Todo(desc);
                    } else if (type.equals("D")) {
                        toAdd = Deadline.build(desc);
                    } else {
                        toAdd = Event.build(desc);
                    }

                    if (matcher.group("status").equals("X")) {
                        toAdd.setDone();
                    }
                    taskList.addTask(toAdd);
                }
            }
        }
    }

    /**
     * Saves tasks into a text file
     */
    public static void saveTasks(TaskList taskList) {
        File file = new File(PATH);
        try {
            // Create file if not already existing
            file.createNewFile();
            FileWriter fw = new FileWriter(PATH);

            // Write current task list into file
            for (Task t : taskList.getTaskList()) {
                fw.write(t.toString());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
