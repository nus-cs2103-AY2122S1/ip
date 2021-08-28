package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private File data;

    /**
     * Constructor for Storage.
     *
     * @param pathname of the save file.
     * @param dir      directory of the save file.
     * @throws IOException when an IO operations fails.
     */
    public Storage(String pathname, String dir) throws IOException {
        File directory = new File(dir);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File data = new File(pathname);
        if (!data.exists()) {
            data.createNewFile();
        }
        this.data = data;
    }

    /**
     * Returns the save file.
     *
     * @return the save file.
     */
    public File load() {
        return this.data;
    }

    /**
     * Adds a task to the save file.
     *
     * @param task the task to be added to the save file.
     * @throws IOException when an IO operation fails.
     */
    public void add(Task task) throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt", true);
        if (task instanceof Todo) {
            Todo todo = (Todo) task;
            fw.write("T | 0 | " + task.getDescription() + "\n");
            fw.close();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            fw.write("D | 0 | " + task.getDescription() + " | " + deadline.getBy() + "\n");
            fw.close();
        } else {
            Event event = (Event) task;
            fw.write("E | 0 | " + event.getDescription() + " | " + event.getAt() + "\n");
            fw.close();
        }
    }

    /**
     * Deletes task from save file.
     *
     * @param deleteIndex the index of the task to be deleted.
     * @throws IOException when an IO operation fails.
     */
    public void delete(int deleteIndex) throws IOException {
        List<String> content = new ArrayList<>(Files.readAllLines(Path.of("data/tasks.txt"), StandardCharsets.UTF_8));
        content.remove(deleteIndex - 1);
        Files.write(Path.of("data/tasks.txt"), content, StandardCharsets.UTF_8);
    }

    /**
     * Marks a task in the save file as done.
     *
     * @param doneIndex the index of the task to be marked as done.
     * @throws IOException when an IO operation fails.
     */
    public void markAsDone(int doneIndex) throws IOException {
        List<String> fileContent = new ArrayList<>(Files.readAllLines(Path.of("data/tasks.txt"),
                        StandardCharsets.UTF_8));

        String oldLine = fileContent.get(doneIndex - 1);
        StringBuilder newLine = new StringBuilder(oldLine);
        newLine.setCharAt(4, '1');

        fileContent.set(doneIndex - 1, newLine.toString());
        Files.write(Path.of("data/tasks.txt"), fileContent, StandardCharsets.UTF_8);
    }
}
