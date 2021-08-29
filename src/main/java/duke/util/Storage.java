package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A class that handles all file related functionality, such as loading from
 * and saving to a save file.
 */
public class Storage {

    private final Path filePath;

    /**
     * Creates an instance of the Storage class.
     *
     * @param filePath File path for the save file.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the file path given on instantiation. Called when Duke starts.
     *
     * @return The list of tasks loaded from the save file.
     * @throws FileNotFoundException If file does not exist.
     * @throws DukeException         If the file contains invalid task data.
     */
    public List<Task> loadTasksFromFile() throws FileNotFoundException, DukeException {
        File saveFile = filePath.toFile();
        Scanner scanner = new Scanner(saveFile);
        List<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String taskLine = scanner.nextLine();
            String[] taskData = taskLine.split("\\|");
            switch (taskData[0]) {
            case ("T"):
                tasks.add(new Todo(taskData[2], Boolean.parseBoolean(taskData[1])));
                break;
            case ("D"):
                tasks.add(new Deadline(taskData[2], Boolean.parseBoolean(taskData[1]),
                        Parser.parseDateFromInput(taskData[3]), Parser.parseTimeFromInput(taskData[4])));
                break;
            case ("E"):
                tasks.add(new Event(taskData[2], Boolean.parseBoolean(taskData[1]),
                        Parser.parseDateFromInput(taskData[3]), Parser.parseTimeFromInput(taskData[4]),
                        Parser.parseTimeFromInput(taskData[5])));
                break;
            default:
                throw new DukeException("Save file contains invalid task data (Invalid task type)");
            }
        }
        return tasks;
    }

    /**
     * Saves tasks to the file path given at instantiation. Called when list is modified.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException If the saving process fails.
     */
    public void saveTasksToFile(TaskList tasks) throws IOException {
        Files.createDirectories(filePath.getParent()); // Create directories if they do not exist
        FileWriter fw = new FileWriter(filePath.toAbsolutePath().toString());
        StringBuilder saveData = new StringBuilder();
        tasks.forEach((task) -> saveData.append(task.toSaveData()).append(System.lineSeparator()));
        fw.write(saveData.toString());
        fw.close();
    }
}
