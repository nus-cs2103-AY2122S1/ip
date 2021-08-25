package seedu.duke;

import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;
import seedu.duke.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a Storage object. A <code>Storage</code> object
 * loads data from the file according to the filepath given
 * and handles any updates to the file.
 */
class Storage {
    private String filePath;

    /**
     * Public constructor for a Storage object.
     *
     * @param filePath The filepath of the file object to be handled.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load data from the file per the filepath.
     *
     * @param dateTasks A HashMap to keep track of tasks happening on specific
     *                  dates.
     * @param taskList The tasklist to be updated as the file content is read.
     * @return The updated tasklist.
     */
    public TaskList loadData(HashMap<LocalDate, ArrayList<Task>> dateTasks,
                             TaskList taskList) {
        try {
            File file = new File(filePath);
            // Create a new file if it does not already exist
            file.createNewFile();

            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String task;
            while((task = reader.readLine()) != null) {
                char type = task.charAt(1);
                boolean isCompleted = task.charAt(4) == 'X';

                DateTimeManager manager = new DateTimeManager(DateTimeFormatter.ISO_DATE);

                String description = parseDescription(task);
                Task newTask;
                LocalDate time;

                switch (type) {
                case 'T':
                    newTask = new ToDo(description);
                    taskList = loadTasks(newTask, taskList, isCompleted);
                    break;
                case 'D':
                    time = parseTime(task, "by: ");
                    newTask = new Deadline(description, time);
                    taskList = loadTasks(newTask, taskList, isCompleted);
                    manager.updateDateTasks(dateTasks, time, newTask);
                    break;
                case 'E':
                    time = parseTime(task, "at: ");
                    newTask = new Event(description, time);
                    taskList = loadTasks(newTask, taskList, isCompleted);
                    manager.updateDateTasks(dateTasks, time, newTask);
                    break;
                default:
                    throw new DukeException("Invalid task.");
                }
                System.out.println(task);
            }
            reader.close();
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            return taskList;
        }
    }

    private TaskList loadTasks(Task task, TaskList taskList, boolean isCompleted) {
        if (isCompleted) {
            task.markAsCompleted();
        }
        return taskList.add(task);
    }

    private LocalDate parseTime(String task, String command) throws DukeException {
        int timeIndex = task.indexOf(command);
        if (timeIndex < 0) {
            throw new DukeException("Invalid task");
        }

        String timeDescription = task.substring(timeIndex + command.length(),
                task.indexOf(')'));
        LocalDate time = LocalDate.parse(timeDescription);
        return time;
    }

    private String parseDescription(String task) throws DukeException {
        int startOfDescription = task.lastIndexOf(']') + 2;
        if (startOfDescription < 0) {
            throw new DukeException("Description cannot be empty.");
        }

        String description = task.substring(startOfDescription);
        int timeIndex = description.indexOf("  (");
        if (timeIndex >= 0) {
            description = description.substring(0, timeIndex);
        }
        return description;
    }

    /**
     * Append the newly added task to the file contents.
     *
     * @param task The task to be appended to the file contents.
     */
    public void addTaskToFile(Task task) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(task.toString() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update the file contents to reflect the updated list after
     * a task has been deleted from it.
     *
     * @param taskList The updated tasklist after a deletion.
     */
    public void deleteTaskFromFile(TaskList taskList) {
        try {
            File file = new File(filePath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(taskList.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edit the file contents to reflect that a particular task has been marked.
     * Compares the updated line with the existing one to be replaced.
     *
     * @param task String representation of the editted task.
     * @param toUpdate String representation of the pre-editted task.
     */
    public void markTaskAsCompleted(String task, String toUpdate) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            String newContent = "";
            while((line = reader.readLine()) != null) {
                if (line.compareTo(toUpdate) == 0) {
                    line = task;
                }
                newContent += line + System.lineSeparator();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(newContent);
            reader.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
