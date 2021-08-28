package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Storage {
    /** Path string to data of tasks to load/save */
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from task text file if exists, otherwise start new text file
     */
    public TaskList load() throws DukeException {
        File tasks = new File(filePath);
        TaskList taskList = TaskList.emptyTaskList();
        if (tasks.exists()) {
            // Read tasks from text file
            Scanner s = null;
            try {
                s = new Scanner(tasks);
            } catch (FileNotFoundException e) {
                throw new DukeException("File not found!");
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
        return taskList;
    }

    /**
     * Saves tasks into a text file
     */
    public void save(TaskList taskList) throws DukeException {
        File file = new File(filePath);
        try {
            // Create file if not already existing
            file.createNewFile();
            FileWriter fw = new FileWriter(filePath);

            // Write current task list into file
            for (Task t : taskList.getTaskList()) {
                fw.write(t.toString());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("There was an error! Try again!");
        }
    }
}
