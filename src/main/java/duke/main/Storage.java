package duke.main;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Handles the loading/saving of tasks from local storage.
 */
public class Storage {
    private final Path filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath Location at which tasks are stored
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Loads tasks from text storage.
     *
     * @return Loaded TaskList.
     */
    public TaskList loadTaskList() {
        try {
            Stream<String> taskString = Files.lines(filePath);
            List<Task> taskList = taskString.map(this::loadTaskFromString).collect(Collectors.toList());
            return new TaskList(taskList);
        } catch (IOException e) {
            throw new DukeException("\t OOPS!!! I can't find your tasks.\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\t OOPS!!! Your tasks might be corrupted.");
        }
    }

    /**
     * Parses the task string and returns the corresponding task.
     *
     * @param line String for a task retrieved from storage.
     * @return Task that is parsed.
     */
    private Task loadTaskFromString(String line) {
        //task storage string formatted as taskType | isComplete | description | time (optional)
        String[] fragments = line.split(" \\| ");

        String taskType = fragments[0];
        boolean isComplete = Boolean.parseBoolean(fragments[1]);
        String description = fragments[2];
        String time = fragments.length == 4 ? fragments[3] : null;

        return generateTask(taskType, isComplete, description, time);
    }

    /**
     * Generate a task from the given parameters.
     *
     * @param taskType        String type of task.
     * @param isCompletedTask boolean true if task is completed, else false.
     * @param taskDescription String description of the task.
     * @param time            String time associated with the task.
     * @return
     */
    private Task generateTask(String taskType, boolean isCompletedTask, String taskDescription, String time) {
        Task foundTask;
        switch (taskType) {
            case "T":
                foundTask = new ToDo(taskDescription, isCompletedTask);
                break;
            case "D":
                foundTask = new Deadline(taskDescription, time, isCompletedTask);
                break;
            case "E":
                foundTask = new Event(taskDescription, time, isCompletedTask);
                break;
            default:
                throw new DukeException("\t OOPS!!! I can't find your tasks.\n");
        }
        return foundTask;
    }

    /**
     * Write taskList to Storage
     *
     * @param tasklist that is written to Storage
     */
    public void writeToStorage(TaskList tasklist) {
        try {
            Files.write(filePath, tasklist.formatForStorage(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("\t OOPS!!! I can't store any changes you make. \n");
        }
    }

    /**
     * Clear text file containing tasks.
     */
    public String resetTasks() {
        try {
            Files.newBufferedWriter(filePath);
            return Ui.getResetTasksMessage();
        } catch (IOException e) {
            throw new DukeException("\t OOPS!!! Continuing without saving.\n");
        }
    }

}
