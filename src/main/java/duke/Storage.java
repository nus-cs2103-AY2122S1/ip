package duke;

import duke.tasks.Deadline;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.Event;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
}
