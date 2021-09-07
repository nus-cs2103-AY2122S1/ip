package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

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
     * Loads tasks from the specified file path. Called when Duke starts.
     *
     * @return The TaskList object containing a list of tasks loaded from the save file.
     * @throws FileNotFoundException If the file does not exist.
     * @throws DukeException         If the file contains invalid task data.
     */
    public TaskList loadTasksFromFile() throws FileNotFoundException, DukeException {
        File saveFile = filePath.toFile();
        Scanner scanner = new Scanner(saveFile);
        List<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String taskLine = scanner.nextLine();
            String[] taskData = taskLine.split("\\|");

            String taskType = taskData[0];
            String taskName = taskData[2];
            boolean isDone = Boolean.parseBoolean(taskData[1]);

            switch (taskType) {
            case ("T"):
                tasks.add(new Todo(taskName, isDone));
                break;
            case ("D"):
                LocalDate date = Parser.parseDateFromInput(taskData[3]);
                LocalTime time = Parser.parseTimeFromInput(taskData[4]);
                tasks.add(new Deadline(taskName, isDone, date, time));
                break;
            case ("E"):
                date = Parser.parseDateFromInput(taskData[3]);
                LocalTime startTime = Parser.parseTimeFromInput(taskData[4]);
                LocalTime endTime = Parser.parseTimeFromInput(taskData[5]);
                tasks.add(new Event(taskName, isDone, date, startTime, endTime));
                break;
            default:
                throw new DukeException("Save file contains invalid task data (Invalid task type).");
            }
        }
        return new TaskList(tasks);
    }

    /**
     * Saves tasks to the file path given at instantiation. Called when list is modified.
     *
     * @param taskList The list of tasks to be saved.
     * @throws IOException If the saving process fails.
     */
    public void saveTasksToFile(TaskList taskList) throws IOException {
        // Create directories if they do not exist, so that the file can be created without error
        Files.createDirectories(filePath.getParent());
        FileWriter fw = new FileWriter(filePath.toAbsolutePath().toString());
        // Convert all tasks in the task list into save data format
        StringBuilder saveData = new StringBuilder();
        taskList.forEach((task) -> saveData.append(task.toSaveData())
                .append(System.lineSeparator())); // Each task will be on its own line

        fw.write(saveData.toString());
        fw.close();
    }
}
