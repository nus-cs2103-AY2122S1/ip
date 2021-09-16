package duke.main;

import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
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
            List<Task> taskList = taskString.map(Parser::generateTaskFromLine).collect(Collectors.toList());
            return new TaskList(taskList);
        } catch (IOException e) {
            makeTasksFile();
            return new TaskList();
        } catch (ArrayIndexOutOfBoundsException e) {
            resetTasks();
            throw new DukeException("OOPS!!! Your tasks might be corrupted.");
        }
    }

    private void makeTasksFile() {
        File newFile = new File(filePath.toString());
        try {
            newFile.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Write taskList to Storage
     *
     * @param tasklist that is written to Storage
     */
    public void writeToStorage(TaskList tasklist) {
        try {
            List<String> formattedTaskList = tasklist.formatForStorage();
            Files.write(filePath, formattedTaskList, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("OOPS!!! I can't store any changes you make. \n");
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
            throw new DukeException("OOPS!!! Continuing without saving.\n");
        }
    }

}
