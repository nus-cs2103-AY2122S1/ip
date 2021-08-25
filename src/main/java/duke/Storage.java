package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class to handle loading and storing tasks in the file specified.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the tasks contained in the file specified. If the file or directory
     * does not exist, a new empty file will be created.
     *
     * @return ArrayList<Task> The ArrayList containing tasks from the file.
     * @throws DukeException If something is wrong with the file.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(filePath);
            String parentDir = file.getParent();
            File directory = new File(parentDir);
            if (!directory.exists()) {
                Path path = Paths.get(parentDir);
                Files.createDirectories(path);
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            Scanner scanner = new Scanner(file);
            ArrayList<Task> taskList = new ArrayList<>();
            while (scanner.hasNext()) {
                String[] data = scanner.nextLine().split("\\|");
                String taskType = data[0].trim();
                String statusIcon = data[1].trim();
                String description = data[2].trim();
                if (taskType.equalsIgnoreCase("T")) {
                    Todo todo = new Todo(description.trim());
                    if (statusIcon.equals("1")) {
                        todo.markDone();
                    }
                    taskList.add(todo);
                } else if (taskType.equalsIgnoreCase("D")) {
                    String by = data[3].trim();
                    Deadline deadline = new Deadline(description, by);
                    if (statusIcon.equals("1")) {
                        deadline.markDone();
                    }
                    taskList.add(deadline);
                } else if (taskType.equalsIgnoreCase("E")) {
                    String at = data[3].trim();
                    Event event = new Event(description, at);
                    if (statusIcon.equals("1")) {
                        event.markDone();
                    }
                    taskList.add(event);
                }
            }
            scanner.close();
            return taskList;
        } catch (Exception e) {
            throw new DukeException("Uh oh there's something wrong with the file.");
        }
    }

    /**
     * Writes the tasks from the TaskList into the file.
     *
     * @throws DukeException If IOException is thrown.
     */
    public void save(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(taskList.toSave());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Uh oh something went wrong with the file!");
        }
    }
}
