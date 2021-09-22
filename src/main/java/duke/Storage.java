package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents the persistent storage for tasks in duke.Duke.
 */
public class Storage {
    private String filePath;

    /**
     * @param filePath Path to tasks data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }
    }

    /**
     * Loads file data to an ArrayList of tasks.
     *
     * @return ArrayList of tasks loaded from file.
     * @throws DukeException If file format was invalid.
     * @throws FileNotFoundException If file was not found.
     */
    public ArrayList<Task> load() throws DukeException, FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        ArrayList<Task> taskList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] lineSplit = line.split(" \\| ");
            String lineType = lineSplit[0];
            Task newTask;
            switch (lineType) {
            case "T":
                newTask = new Todo(lineSplit[2]);
                break;
            case "D":
                newTask = new Deadline(lineSplit[2], lineSplit[3]);
                break;
            case "E":
                newTask = new Event(lineSplit[2], lineSplit[3]);
                break;
            default:
                throw new DukeException("Invalid task storage format");
            }
            if (lineSplit[1].equals("1")) {
                newTask.markAsDone();
            }
            taskList.add(newTask);
        }
        scanner.close();
        return taskList;
    }

    /**
     * Saves an ArrayList of tasks into file.
     *
     * @param tasks ArrayList of tasks to be stored in file.
     * @throws IOException If unable to write to file.
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        String data = "";
        for (Task task : tasks) {
            int isDone = task.isDone() ? 1 : 0;
            switch (task.getType()) {
            case TODO:
                data += String.format("T | %d | %s\n",
                        isDone, task.getDescription());
                break;
            case EVENT:
                Event event = (Event) task;
                data += String.format("E | %d | %s | %s\n",
                        isDone, task.getDescription(), event.getDate());
                break;
            case DEADLINE:
                Deadline deadline = (Deadline) task;
                data += String.format("D | %d | %s | %s\n",
                        isDone, task.getDescription(), deadline.getDate());
                break;
            default: // should not happen
                break;
            }
        }
        writer.write(data);
        writer.close();
    }
}
