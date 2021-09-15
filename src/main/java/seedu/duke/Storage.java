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
public class Storage {
    private String filePath;
    private HashMap<LocalDate, ArrayList<Task>> dateTasks;
    private static DateTimeManager manager = new DateTimeManager(DateTimeFormatter.ISO_DATE);

    private enum Letter {
        TODO('T'),
        DEADLINE('D'),
        EVENT('E'),
        INVALID('/');

        private final char type;

        Letter(char type) {
            this.type = type;
        }

        private static Letter parseLetter(char type) {
            switch (type) {
            case 'T':
                return TODO;
            case 'D':
                return DEADLINE;
            case 'E':
                return EVENT;
            default:
                return INVALID;
            }
        }

        private Task updateTaskListWithToDo(String description, boolean isCompleted) {
            return new ToDo(description, isCompleted);
        }

        private Task updateTaskListWithDeadline(String description, LocalDate time, boolean isCompleted,
                                                HashMap<LocalDate, ArrayList<Task>> dateTasks) {
            Task task = new Deadline(description, time, isCompleted);
            manager.updateDateTasks(dateTasks, time, task);
            return task;
        }

        private Task updateTaskListWithEvent(String description, LocalDate time, boolean isCompleted,
                                             HashMap<LocalDate, ArrayList<Task>> dateTasks) {
            Task task = new Event(description, time, isCompleted);
            manager.updateDateTasks(dateTasks, time, task);
            return task;
        }

    }

    /**
     * Public constructor for a Storage object.
     *
     * @param filePath The filepath of the file object to be handled.
     */
    public Storage(String filePath, HashMap<LocalDate, ArrayList<Task>> dateTasks) {
        this.filePath = filePath;
        this.dateTasks = dateTasks;
    }

    /**
     * Load data from the file per the filepath.
     *
     * @param taskList The tasklist to be updated as the file content is read.
     * @return The updated tasklist.
     */
    public TaskList loadData(TaskList taskList) {
        try {
            checkFileExists();
            taskList = parseData(taskList);
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
        finally {
            return taskList;
        }
    }

    private void checkFileExists() {
        try {
            File file = new File(filePath);
            // Create a new file if it does not already exist
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private TaskList parseData(TaskList taskList) throws IOException, DukeException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String task = "";
            while ((task = reader.readLine()) != null) {
                char type = task.charAt(1);
                Letter taskType = Letter.parseLetter(type);
                boolean isCompleted = task.charAt(4) == 'X';
                String description = parseDescription(task);

                // Dummy objects initialised.
                Task newTask = new Task("");
                LocalDate time = LocalDate.now();

                switch (taskType) {
                case TODO:
                    newTask = taskType.updateTaskListWithToDo(description, isCompleted);
                    break;
                case DEADLINE:
                    time = parseTime(task, "by: ");
                    newTask = taskType.updateTaskListWithDeadline(description, time,
                            isCompleted, this.dateTasks);
                    break;
                case EVENT:
                    time = parseTime(task, "at: ");
                    newTask = taskType.updateTaskListWithEvent(description, time,
                            isCompleted, this.dateTasks);
                    break;
                default:
                    throw new DukeException("Invalid task.");
                }
                System.out.println(newTask);
                taskList = taskList.add(newTask);
            }
            reader.close();
            return taskList;
    }

    private LocalDate parseTime(String task, String command) throws DukeException {
        int timeIndex = task.indexOf(command);
        if (timeIndex < 0) {
            throw new DukeException("Invalid task");
        }

        int endOfDateString = task.indexOf(')');
        assert endOfDateString >= 0: "DateString is within brackets.";

        String timeDescription = task.substring(timeIndex + command.length(),
                endOfDateString);
        LocalDate time = LocalDate.parse(timeDescription);
        return time;
    }

    private String parseDescription(String task) throws DukeException {
        int startOfDescription = task.lastIndexOf(']') + 1;
        if (startOfDescription < 0) {
            throw new DukeException("Description cannot be empty.");
        }

        String description = task.substring(startOfDescription).strip();
        int timeIndex = description.indexOf("(");
        if (timeIndex >= 0) {
            description = description.substring(0, timeIndex - 1);
        }
        return description;
    }

    /**
     * Appends the newly added task to the file contents.
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
     * Updates the file contents to reflect the updated list after
     * a task has been deleted from it.
     *
     * @param taskList The updated tasklist after a deletion.
     */
    public void deleteTaskFromFile(TaskList taskList) {
        try {
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

            String line = "";
            String newContent = "";
            while ((line = reader.readLine()) != null) {
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
