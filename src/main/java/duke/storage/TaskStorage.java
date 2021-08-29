package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * Responsible for reading and writing tasks to the memory
 */
public class TaskStorage {
    static final String DATA_FILE_DIR = "./data";
    static final String DATA_FILE_PATH = "./data/tasks.txt";

    private File dataFile;

    /**
     * Constructor of <code>TaskStorage</code>
     *
     * @throws IOException if cannot initialize the data file
     */
    public TaskStorage() throws IOException {
        this.dataFile = initializeDataFile();
    }

    private File initializeDataFile() throws IOException {
        File dataDir = new File(DATA_FILE_DIR);
        File dataFile = new File(DATA_FILE_PATH);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
        return dataFile;
    }

    /**
     * Reads all tasks from the memory
     *
     * @return the list of tasks read from the memory
     */
    public ArrayList<Task> readTasksFromMemory() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(this.dataFile);
            while (scanner.hasNextLine()) {
                try {
                    Task task = parseTask(scanner.nextLine());
                    tasks.add(task);
                } catch (DukeException e) {
                    // ignore task with invalid format
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.printf("File not found: %s\n", this.dataFile.toString());
        }
        return tasks;
    }

    private Task parseTask(String taskString) throws DukeException {
        String[] tokens = taskString.split(" ");
        switch (tokens[0]) {
        case "todo":
            return new TodoTask(
                    tokens[1],
                    Boolean.parseBoolean(tokens[2]));
        case "event":
            return new EventTask(
                    tokens[1],
                    Boolean.parseBoolean(tokens[2]),
                    LocalDateTime.parse(tokens[3]));
        case "deadline":
            return new DeadlineTask(
                    tokens[1],
                    Boolean.parseBoolean(tokens[2]),
                    LocalDateTime.parse(tokens[3]));
        default:
            throw new DukeException("Task with invalid format!");
        }
    }

    private String taskToMemString(Task task) {
        if (task instanceof TodoTask) {
            @SuppressWarnings("unchecked")
            TodoTask todoTask = (TodoTask) task;
            return String.format(
                    "todo %s %b",
                    todoTask.getContent(),
                    todoTask.isDone());
        } else if (task instanceof EventTask) {
            @SuppressWarnings("unchecked")
            EventTask eventTask = (EventTask) task;
            return String.format(
                    "event %s %b %s",
                    eventTask.getContent(),
                    eventTask.isDone(),
                    eventTask.getDate().toString());
        } else if (task instanceof DeadlineTask) {
            @SuppressWarnings("unchecked")
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return String.format(
                    "deadline %s %b %s",
                    deadlineTask.getContent(),
                    deadlineTask.isDone(),
                    deadlineTask.getDeadline().toString());
        } else {
            // never reach this branch
            return null;
        }
    }

    /**
     * Writes a list of tasks to memory
     * @param tasks the array lists of tasks
     * @throws IOException if cannot write to memory
     */
    public void writeToMem(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(DATA_FILE_PATH);
        String data = "";
        for (Task task : tasks) {
            data += taskToMemString(task) + System.lineSeparator();
        }
        fileWriter.write(data);
        fileWriter.close();
    }
}
