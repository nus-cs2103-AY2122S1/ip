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

    /** Regex of data file format */
    public static final Pattern TASK_FORMAT = Pattern
            .compile("\\[(?<type>[A-Z])\\]\\[(?<status>[X\\s])\\]\\s(?<desc>.*)");

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

        // If task file does not exist, return empty TaskList
        if (!tasks.exists()) {
            return taskList;
        }

        try {
            Scanner sc = new Scanner(tasks);
            while (sc.hasNext()) {
                // Parse string to get task type, status, and description
                Matcher matcher = TASK_FORMAT.matcher(sc.nextLine());
                if (!matcher.matches()) {
                    throw new DukeException("There was a problem loading your saved tasks!");
                }

                String type = matcher.group("type");
                String desc = matcher.group("desc");

                Task toAdd;
                if (type.equals("T")) {
                    toAdd = new Todo(desc);
                } else if (type.equals("D")) {
                    toAdd = Deadline.build(desc);
                } else if (type.equals("E")) {
                    toAdd = Event.build(desc);
                } else {
                    throw new DukeException(String.format("[%s] is not a recognised task type!", type));
                }

                if (matcher.group("status").equals(Task.DONE_MARKER)) {
                    toAdd.setIsDone(true);
                }
                taskList.addTask(toAdd);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
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
        //  Does not need to save anything if there are no tasks to be saved
        if (taskList.size() == 0) return;

        try {
            File file = new File(filePath);

            // Create directory if not already existing
            new File(file.getParent()).mkdirs();

            // Create file if not already existing
            file.createNewFile();

            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < taskList.size(); i++) {
                fw.write(taskList.getTask(i).toString());
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("There was an error saving your tasks!");
        }
    }
}
