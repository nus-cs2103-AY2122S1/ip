package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 *
 * @author felix-ong
 */
public class Storage {
    /** Path to storage location */
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the list of tasks saved if the directory and file exists.
     * If not, creates the directory and file as necessary and returns an empty list.
     *
     * @return List of saved tasks
     */
    public List<Task> loadData() throws DukeException {
        List<Task> tasks = new ArrayList<>();

        // Check if data directory exists
        File file = new File(this.filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String[] taskParts = sc.nextLine().split(",");
                String taskType = taskParts[0];
                boolean isDone = taskParts[1].equals("X");
                String taskDescription = taskParts[2];

                switch (taskType) {
                case "T":
                    tasks.add(new Todo(taskDescription, isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(taskDescription, LocalDateTime.parse(taskParts[3]), isDone));
                    break;
                case "E":
                    tasks.add(new Event(taskDescription, LocalDateTime.parse(taskParts[3]), isDone));
                    break;
                default:
                    System.out.println("Invalid task in data");
                    break;
                }
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

    /**
     * Save the list of tasks in the hard disk.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void saveData(List<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : tasks) {
                fw.write(task.toSaveString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
