package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class to write data to and retrieve data from a file.
 */
public class Storage {

    /** The path of the file used for storage */
    private String filePath;

    /**
     * The constructor of the Storage class.
     *
     * @param filePath the path of the file used for storage
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the data from an already existing file into a new list of tasks, and creates
     * a new file if the file does not exist yet.
     *
     * @return the list of tasks
     * @throws DukeException
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File(filePath);
            if (f.exists()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String next = sc.nextLine();
                    Task t = parseLine(next);
                    tasks.add(t);
                }
                sc.close();
            } else {
                if (!f.getParentFile().exists()) {
                    f.getParentFile().mkdir();
                }
            }
            f.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error retrieving/reading from data file, creating new file instead.");
        }
        return tasks;
    }

    /**
     * Writes the list of tasks to a storage file.
     *
     * @param tasks the list of tasks to be written to the storage file
     */
    public void writeToFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : tasks.getListOfTasks()) {
                fw.write(t.getRecordString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to data file.");
        }
    }

    /**
     * Parses a line from the storage file and get the Task object it represents.
     *
     * @param line the line to be parsed
     * @return the Task to be added into the list of tasks
     */
    public Task parseLine(String line) {
        assert(line != null && !line.equals(""));

        String[] x = line.split("\\|");
        String taskType = x[0].strip();
        boolean isDone = !x[1].strip().equals("0");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy HH:mm");

        if (taskType.equals("T")) {
            return new ToDo(x[2].strip(), isDone);
        } else if (taskType.equals("D")) {
            String by = x[x.length - 1].strip();
            LocalDateTime byy = LocalDateTime.parse(by, formatter);
            return new Deadline(x[2].strip(), isDone, byy);
        } else { // Event
            String at = x[x.length - 1].strip();
            LocalDateTime att = LocalDateTime.parse(at, formatter);
            return new Event(x[2].strip(), isDone, att);
        }
    }
}
