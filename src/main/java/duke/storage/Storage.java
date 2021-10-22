package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

/**
 * Stores all the tasks.
 */
public class Storage {

    private final String filePath;

    /**
     * Creates storage with the file path provided.
     *
     * @param filePath the file path where all the tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void createNewFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    private Todo parseTodo(String[] splitInput) {
        return new Todo(splitInput[2], splitInput[1].equals("1"));
    }

    private Deadline parseDeadline(String[] splitInput) {
        LocalDate date = LocalDate.parse(splitInput[3], DateTimeFormatter.ofPattern("d/M/yyyy"));
        LocalTime time = splitInput.length == 5 ? LocalTime.parse(splitInput[4]) : null;
        return new Deadline(splitInput[2], splitInput[1].equals("1"), date, time);
    }

    private Event parseEvent(String[] splitInput) {
        LocalDate date = LocalDate.parse(splitInput[3], DateTimeFormatter.ofPattern("d/M/yyyy"));
        LocalTime time = splitInput.length == 5 ? LocalTime.parse(splitInput[4]) : null;
        return new Event(splitInput[2], splitInput[1].equals("1"), date, time);
    }

    private Task parseTask(String line) throws DukeException {
        char type = line.charAt(0);
        String[] splitInput = line.split(" \\| ");
        if (type == 'T') {
            return parseTodo(splitInput);
        } else if (type == 'D') {
            return parseDeadline(splitInput);
        } else if (type == 'E') {
            return parseEvent(splitInput);
        }
        throw new DukeException("Unknown task format!");
    }

    private void addTasksToFile(File file, ArrayList<Task> tasks) throws IOException, DukeException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while (line != null) {
            tasks.add(parseTask(line));
            line = br.readLine();
        }
    }

    /**
     * Loads the text file and creates a text file if file does not exist.
     *
     * @return the array list with all the tasks read from the file.
     */
    public ArrayList<Task> loadFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                createNewFile(file);
            } else {
                addTasksToFile(file, tasks);
            }
            return tasks;
        } catch (IOException | DukeException e) {
            e.printStackTrace();
            return tasks;
        }
    }

    private String tasksToFileFormat(ArrayList<Task> tasks) {
        ArrayList<String> displayTasks = new ArrayList<>();
        for (Task task : tasks) {
            displayTasks.add(task.fileFormat());
        }
        return String.join(String.format("%n"), displayTasks);
    }

    private void writeToFile(String tasks) throws IOException {
        FileWriter writer = new FileWriter(this.filePath);
        writer.write(String.join(String.format("%n"), tasks));
        writer.close();
    }

    /**
     * Updates the tasks given to the file.
     *
     * @param taskList the tasks that will be updated to the file.
     */
    public void updateTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();
        try {
            String displayTasks = tasksToFileFormat(tasks);
            writeToFile(displayTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
