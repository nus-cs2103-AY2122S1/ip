package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents storage to store tasks
 */
public class Storage {

    /** File path string to data of tasks to load/save */
    private String filePath;

    /**
     * Storage constructor using file path to load/save tasks.
     *
     * @param filePath Path of file to save tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks in file path to a TaskList.
     *
     * @return TaskList (list of tasks).
     * @throws DukeException If file is not found.
     * Should not happen since existence of file is checked.
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
        return taskList;
    }

    /**
     * Saves the tasks in given TaskList to file path.
     *
     * @param taskList List of tasks to save.
     * @throws DukeException If IOException is caught.
     */
    public void save(TaskList taskList) throws DukeException {
        // Check if there are tasks to save
        if (taskList.size() != 0) {
            File file = new File(filePath);
            try {
                // Create directory if not already existing
                new File(file.getParent()).mkdirs();

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
                throw new DukeException("There was an error saving your tasks!");
            }
        }
    }
}
