package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 *
 * @author Adam Ho
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes the list of tasks to a text file.
     * @throws IOException The exception is thrown if an error occurred while writing to the text file.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        ArrayList<Task> taskList = tasks.getTaskList();
        for (Task t : taskList) {
            String taskDetails;
            String done = t.isDone ? "1" : "0";
            String command = "todo";
            switch (command) {
            case "todo":
                taskDetails = "T" + " uwu " + done + " uwu " + t.description + "\n";
                break;
            case "deadline":
                Deadline d = (Deadline) t;
                taskDetails = "D" + " uwu " + done + " uwu " + d.description + " uwu " + d.by + "\n";
                break;
            case "event":
                Event e = (Event) t;
                taskDetails = "E" + " uwu " + done + " uwu " + e.description + " uwu " + e.at + "\n";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + command);
            }
            fw.write(taskDetails);
        }
        fw.close();
    }

    /**
     * Reads the list of tasks from a text file.
     * @throws FileNotFoundException The exception is thrown if the text file does not exist in the directory.
     */
    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();

        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String[] taskFormat = s.nextLine().split(" uwu ");
            Task t;
            if (taskFormat[0].equals("T")) {
                t = new Todo(taskFormat[2]);
            } else if (taskFormat[0].equals("D")) {
                t = new Deadline(taskFormat[2], taskFormat[3]);
            } else {
                t = new Event(taskFormat[2], taskFormat[3]);
            }
            t.isDone = taskFormat[1].equals("1");
            tasks.add(t);
        }
        return tasks;
    }
}
