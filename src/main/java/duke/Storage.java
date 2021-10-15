package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class will be responsible for loading and storing the taskList.
 */
public class Storage {
    protected File f;

    /**
     * Constructor method for Storage.
     *
     * @param filePath User must specify where they wish their file be created.
     */
    public Storage(String filePath) {
        this.f = new File(filePath);
    }

    /**
     * Loads the currently existing taskList memory to Duke. If the file
     * does not exist, then it will be created.
     *
     * @return Returns the taskList for the TaskList class to be initialized.
     * @throws IOException Error control for potential IOException.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> result = new ArrayList<>();
        // if the directory "data" does not exist, then create the directory
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        // check if "duke.txt" exists. If yes, load the info to taskList. Else, create the file.
        if (f.createNewFile()) {
            return result;
        }
        Scanner s = new Scanner(f);
        while (s.hasNextLine()) {
            String line = s.nextLine();
            assert !line.equals("") : "line should not be an empty string";
            String[] segments = line.split(" \\| ");
            Task t = retrieveTask(segments);
            if (segments[1].equals("1")) {
                t.markAsDone();
            }
            result.add(t);
        }
        return result;
    }

    /**
     * Creates a task with an appropriate type based on the input.
     *
     * @return Returns the task with a correct type.
     * @throws IOException Error control for potential IOException.
     */
    private Task retrieveTask(String[] segments) {
        switch (segments[0]) {
            case "T":
                return new Todo("todo " + segments[2]);
            case "D":
                return new Deadline(segments[2], LocalDate.parse(segments[3]));
            case "E":
                return new Event(segments[2], LocalDate.parse(segments[3]));
            case "F":
                return new FixedTask(segments[2], segments[3]);
            default:
                throw new IllegalStateException("Unexpected value: " + segments[0]);
        }
    }

    /**
     * Stores the current taskList from Duke to the device.
     *
     * @throws IOException Error control for potential IOException.
     */
    public void store(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(f);
        for (Task t : tasks.tasks) {
            String line = "";
            if (t instanceof Todo) {
                line += String.format("T | %d | %s\n", t.isDone ? 1 : 0, t.description);
            } else if (t instanceof Event) {
                line += String.format("E | %d | %s | %s\n",
                        t.isDone? 1 : 0, t.description, ((Event)t).at);
            } else if (t instanceof Deadline) {
                line += String.format("D | %d | %s | %s\n",
                        t.isDone ? 1 : 0, t.description, ((Deadline)t).by);
            } else if (t instanceof FixedTask) {
                line += String.format("F | %d | %s | %s\n",
                        t.isDone ? 1 : 0, t.description, ((FixedTask)t).fixedDuration);
            }
            fw.write(line);
        }
        fw.close();
    }
}
