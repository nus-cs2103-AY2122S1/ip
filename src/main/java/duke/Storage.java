package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with loading duke.tasks from the file and saving duke.tasks in the file.
 */
public class Storage {
    private String filepath;
    private String path;
    
    public Storage(String filepath, String path) {
        this.filepath = filepath;
        this.path = path;
    }
    
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Path directoryPath = Paths.get(path);
            Files.createDirectories(directoryPath); //Create directory if it does not exist
            File taskRecord = new File(filepath);
            if (!taskRecord.createNewFile()) {
                //File already exists, parse file and add duke.tasks to ArrayList
                System.out.println("Retrieving your existing task list... *quack*");
                tasks = readSaved(taskRecord);
            }
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred: " + e);
        }
        return tasks;
    }

    public static ArrayList<Task> readSaved (File taskRecord) {
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
                        System.out.println("OOPS! There is an unknown value in the data file.");
                        break;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("OOPS! An error occurred: " + e);
        }
        return savedTasks;
    }

    public static void appendTask(Task task) {
        try {
            File taskRecord = new File("data/duke.txt");
            FileWriter writer = new FileWriter(taskRecord, true);
            writer.write(task.saveText() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred: " + e);
        }
    }

    public static void rewriteTaskRecord(TaskList tasks) {
        try {
            File taskRecord = new File("data/duke.txt");
            FileWriter writer = new FileWriter(taskRecord, false);
            writer.write(""); //Wipe the file
            writer = new FileWriter(taskRecord, true);
            for (Task task : tasks.taskList)  {
                writer.write(task.saveText() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("OOPS! An error occurred: " + e);
        }
    }
}
