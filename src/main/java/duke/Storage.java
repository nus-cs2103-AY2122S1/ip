package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filepath;
    private String path;

    /**
     * Constructor of Storage object.
     *
     * @param filepath filepath of duke.txt.
     * @param path     path of the folder to store the file.
     */
    public Storage(String filepath, String path) {
        this.filepath = filepath;
        this.path = path;
    }

    /**
     * Opens the file on disk where user's past tasks were saved.
     * Loads the previous tasks saved in duke.txt upon start.
     *
     * @return list of tasks to be used to construct TaskList.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Path directoryPath = Paths.get(path);
            Files.createDirectories(directoryPath); // Create directory if it does not exist
            File taskRecord = new File(filepath);
            if (!taskRecord.createNewFile()) {
                // File already exists, parse file and add tasks to ArrayList
                System.out.println("Retrieving your existing task list... *quack*");
                tasks = readSaved(taskRecord);
            }
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred: " + e);
        }
        return tasks;
    }

    /**
     * Scans user's records and parses through lines of tasks to convert into Task form.
     *
     * @param taskRecord record of user's past tasks to be parsed.
     * @return list of tasks to be used to construct TaskList.
     */
    public static ArrayList<Task> readSaved(File taskRecord) {
        ArrayList<Task> savedTasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(taskRecord);
            while (scanner.hasNext()) {
                String taskLine = scanner.nextLine();
                String[] taskAttributes = taskLine.split(" / ");
                switch (taskAttributes[0]) {
                case "T":
                    savedTasks.add(new Todo(taskAttributes[2], taskAttributes[1].equals("1")));
                    break;
                case "D":
                    savedTasks.add(new Deadline(taskAttributes[2], taskAttributes[3],
                            taskAttributes[1].equals("1")));
                    break;
                case "E":
                    savedTasks.add(new Event(taskAttributes[2], taskAttributes[3],
                            taskAttributes[1].equals("1")));
                    break;
                default:
                    Ui.showError("OOPS! There is an unknown value in the data file.");
                    break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("OOPS! An error occurred: " + e);
        }
        return savedTasks;
    }

    /**
     * Appends task to the end of the file when new task is added.
     *
     * @param task task to be added to file.
     */
    public static void appendTask(Task task) {
        try {
            File taskRecord = new File("data/duke.txt");
            FileWriter writer = new FileWriter(taskRecord, true);
            writer.write(task.saveText() + "\n");
            writer.close();
        } catch (IOException e) {
            Ui.showError("OOPS! An error occurred: " + e);
        }
    }

    /**
     * Rewrite the entire file whenever a task is deleted or marked as done.
     *
     * @param tasks user's TaskList to be stored.
     */
    public static void rewriteTaskRecord(TaskList tasks) {
        try {
            File taskRecord = new File("data/duke.txt");
            FileWriter writer = new FileWriter(taskRecord, false);
            writer.write(""); //Wipe the file
            writer = new FileWriter(taskRecord, true);
            for (Task task : tasks.taskList) {
                writer.write(task.saveText() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            Ui.showError("OOPS! An error occurred: " + e);
        }
    }
}
