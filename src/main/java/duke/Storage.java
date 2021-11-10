package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * A class that can store a list of task into a txt file.
 */
public class Storage {
    /**
     * The default directory.
     */
    private static final Path DEFAULT_DIR = Paths.get("data");

    /**
     * The file that will be used to store the tasks.
     */
    private final File tasks;

    /**
     * Constructs a {@code Storage} object that stores tasks in a specific file name. It will create the file or the
     * necessary directory if they do not exist.
     *
     * @param fileName The file name.
     * @throws DukeException If unable to find or create a file.
     */
    public Storage(String fileName) throws DukeException {
        try {
            if (!Files.isDirectory(DEFAULT_DIR)) {
                Files.createDirectories(DEFAULT_DIR);
            }
            Path taskPath = Paths.get(DEFAULT_DIR.toString(), fileName);
            if (Files.notExists(taskPath)) {
                this.tasks = Files.createFile(taskPath).toFile();
            } else {
                this.tasks = taskPath.toFile();
            }
            assert taskPath.toFile().isFile() : "Unable to find or create a file when calling Storage()!";
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Unable to find or create a file!");
        }
    }

    /**
     * Parses a certain line in the file to the corresponding task.
     *
     * @param line The line you want to parse.
     * @return A {@code Task} object corresponding to the input line.
     */
    public static Task lineToTask(String line) {
        String[] data = line.split(" \\| ");
        return Parser.arrayCommandToTask(data);
    }

    /**
     * Parses the entire file to a {@code TaskList} by parsing each line to a task. It will return an empty {@code
     * TaskList} if anything wrong happens.
     *
     * @return A {@code TaskList} based on the file content.
     * @throws DukeException If anything wrong happens.
     */
    public TaskList parseToTaskList() throws DukeException {
        ArrayList<Task> result = new ArrayList<>();
        try {
            List<String> data = Files.readAllLines(tasks.toPath());
            data.forEach(x -> result.add(lineToTask(x)));
        } catch (IOException | DukeException e) {
            result.clear();
            throw new DukeException("OOPS!!! Something wrong happened when reading the file.");
        }
        return new TaskList(result);
    }

    /**
     * Adds the given task into the file.
     *
     * @param task The given task.
     * @throws DukeException If anything wrong happens.
     */
    public void addTask(Task task) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.tasks, true);
            String line = task.taskToLine();
            if (this.tasks.length() == 0) {
                fileWriter.write(line);
            } else {
                fileWriter.write("\n" + line);
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Something wrong happened when modifying the file.");
        }
    }

    /**
     * Resets the file to make it only contains tasks in the given {@code taskList}.
     *
     * @param taskList The {@code taskList} you want to overwrite the file data.
     * @throws DukeException If anything wrong happens.
     */
    public void refreshTask(TaskList taskList) throws DukeException {
        try {
            int numOfTask = taskList.size();
            if (numOfTask == 0) {
                return;
            }
            FileWriter fileWriter = new FileWriter(this.tasks);
            for (int i = 1; i < numOfTask; i++) {
                fileWriter.write(taskList.getTask(i).taskToLine() + "\n");
            }
            fileWriter.write(taskList.getTask(numOfTask).taskToLine());
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Something wrong happened when modifying the file.");
        }
    }
}
