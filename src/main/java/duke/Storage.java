package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.StorageException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {
    private final File tasksFile;

    /**
     * Creates a Storage object to store data at the provided filepath.
     *
     * @param filePath The filepath to store data at.
     * @throws StorageException An IO Exception if a file cannot be created at the provided filepath.
     */
    public Storage(String filePath) throws StorageException {
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        File tasksFile = new File("data/" + filePath);
        if (!tasksFile.exists()) {
            try {
                tasksFile.createNewFile();
            } catch (IOException e) {
                throw new StorageException("Unable to create new storage file");
            }
        }
        this.tasksFile = tasksFile;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return List of tasks read from the file.
     * @throws StorageException Error if there is a problem with the stored file.
     */
    public List<Task> loadTasks() throws DukeException {
        Scanner s;
        List<Task> tasks = new ArrayList<>();

        // Open file.
        try {
            s = new Scanner(this.tasksFile);
        } catch (FileNotFoundException e) {
            throw new StorageException("Unable to open file");
        }

        // Load tasks.
        try {
            while (s.hasNext()) {
                loadTask(tasks, s.nextLine());
            }
        } finally {
            s.close();
        }

        return tasks;
    }

    /**
     * Load a single task into the list of tasks.
     *
     * @param tasks List of tasks.
     * @param line Line to parse and load.
     * @throws StorageException Error if there is a problem with the file format of the stored file.
     */
    private void loadTask(List<Task> tasks, String line) throws DukeException {
        String[] components = line.split(" \\| ");
        if (components.length < 3) {
            throw new StorageException("Invalid storage file format");
        }

        boolean isCompleted = components[1].equals("1");
        String description = unescapeString(components[2]);
        switch (components[0]) {
        case "T": {
            tasks.add(new Todo(description, isCompleted));
            break;
        }
        case "E": {
            if (components.length != 4) {
                throw new StorageException("Invalid storage file format");
            }
            tasks.add(new Event(description, DateTime.parseDateTime(components[3]), isCompleted));
            break;
        }
        case "D": {
            if (components.length != 4) {
                throw new StorageException("Invalid storage file format");
            }
            tasks.add(new Deadline(description, DateTime.parseDateTime(components[3]), isCompleted));
            break;
        }
        default:
            throw new StorageException("Invalid storage file format");
        }
    }

    /**
     * Saves tasks from the given tasklist to the storage file.
     *
     * @param taskList The tasklist to save.
     * @throws StorageException Exception if file cannot be written to.
     */
    public void saveTasks(TaskList taskList) throws StorageException {
        try {
            FileWriter writer = new FileWriter(this.tasksFile);

            for (Task task : taskList.items()) {
                this.writeTask(writer, task);
            }

            writer.close();
        } catch (IOException e) {
            throw new StorageException("Unable to write to file");
        }
    }

    /**
     * Writes the stringified form of a task to the provided {@link FileWriter}.
     *
     * @param writer Writer to write to.
     * @param task Task to convert to a string.
     * @throws IOException if writer cannot be written to.
     */
    private void writeTask(FileWriter writer, Task task) throws IOException {
        if (task instanceof Todo) {
            writer.write("T | ");
            writer.write(task.getIsCompleted() ? "1" : "0");
            writer.write(" | ");
            writer.write(escapeString(task.getDescription()));
            writer.write(System.lineSeparator());
        } else if (task instanceof Event) {
            writer.write("E | ");
            writer.write(task.getIsCompleted() ? "1" : "0");
            writer.write(" | ");
            writer.write(escapeString(task.getDescription()));
            writer.write(" | ");
            writer.write(DateTime.stringifyDateTime(((Event) task).getTime()));
            writer.write(System.lineSeparator());
        } else if (task instanceof Deadline) {
            writer.write("D | ");
            writer.write(task.getIsCompleted() ? "1" : "0");
            writer.write(" | ");
            writer.write(escapeString(task.getDescription()));
            writer.write(" | ");
            writer.write(DateTime.stringifyDateTime(((Deadline) task).getTime()));
            writer.write(System.lineSeparator());
        }
    }

    /**
     * Escapes string to allow lines with the `|` separator to be used.
     *
     * @param str String to escape.
     * @return Escaped string.
     */
    private String escapeString(String str) {
        return str.replace("|", "||");
    }

    /**
     * Unescapes string.
     *
     * @param str String to unescape.
     * @return Unescaped string.
     */
    private String unescapeString(String str) {
        return str.replace("||", "|");
    }
}
