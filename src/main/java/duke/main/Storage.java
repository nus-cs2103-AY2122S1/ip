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
    public TaskList load() {
        try {
            List<Task> taskList = Files.lines(filePath).map((line) -> {
                String[] fragments = line.split(" \\| ");
                String type = fragments[0];
                boolean done = Boolean.parseBoolean(fragments[1]);
                String description = fragments[2];
                String time = fragments.length == 4 ? fragments[3] : null;
                Task foundTask;
                switch (type) {
                    case "T":
                        foundTask = new ToDo(description, done);
                        break;
                    case "D":
                        foundTask = new Deadline(description, time, done);
                        break;
                    case "E":
                        foundTask = new Event(description, time, done);
                        break;
                    default:
                        throw new DukeException("\t☹ OOPS!!! I can't find your tasks.\n");
                }
                return foundTask;
            }).collect(Collectors.toList());
            return new TaskList(taskList);
        } catch (IOException e) {
            throw new DukeException("\t☹ OOPS!!! I can't find your tasks.\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("\t☹ OOPS!!! Your tasks might be corrupted.");
        }
    }

    /**
     * Write taskList to Storage
     *
     * @param tasklist that is written to Storage
     */
    public void write(TaskList tasklist) {
        try {
            Files.write(filePath, tasklist.formatForStorage(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("\t☹ OOPS!!! I can't store any changes you make. \n");
        }
    }

    /**
     * Clear text file containing tasks.
     */
    public void resetTasks() {
        try {
            System.out.println("\tClearing tasks...\n");
            Files.newBufferedWriter(filePath);
            System.out.println("\tYou can now start anew...\n");
        } catch (IOException e) {
            throw new DukeException("\t☹ OOPS!!! Continuing without saving.\n");
        }
    }

}
