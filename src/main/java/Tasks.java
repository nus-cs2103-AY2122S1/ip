import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A collection of tasks io methods
 */
public class Tasks {

    /**
     * Read data from the data file duke.txt
     *
     * @param filePath the file path of the data file
     * @param fileName the file name of the data file
     * @return ArrayList of tasks read
     */
    public static ArrayList<Task> readData(String filePath, String fileName) {
        File dataFile = new File(filePath + fileName);

        try {
            Scanner fileScanner = new Scanner(dataFile);
            ArrayList<Task> tasks = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                String data = fileScanner.nextLine();
                tasks.add(dataStringToTask(data));
            }
            fileScanner.close();
            return tasks;
        } catch (FileNotFoundException e) {
            // return empty array list of task
            return new ArrayList<Task>();
        }
    }

    /**
     * Convert a data string read from duke.txt into task
     *
     * @return the task represented by the string
     */
    private static Task dataStringToTask(String data) {
        String[] taskInfo = data.split(" [|] ");

        String taskType = taskInfo[0];
        Task task;
        switch (taskType) {
        case "T":
            // task is todo
            task = new Todo(taskInfo[2]);

            break;
        case "D":
            // task is deadline
            task = new Deadline(taskInfo[2], taskInfo[3]);
            break;
        case "E":
            // task is event
            task = new Event(taskInfo[2], taskInfo[3]);
            break;
        default:
            // not of any task type
            throw new IllegalArgumentException("Task type not recognized: " + taskType);
        }
        task.isDone = Integer.parseInt(taskInfo[1]) == 1;
        return task;
    }

    /**
     * Write a list of tasks to the duke.txt data file
     *
     * @param tasks    the list of tasks to be written
     * @param path     the path that the data file is in
     * @param fileName the name of the data file
     */
    public static void writeTasksToData(ArrayList<Task> tasks, String path, String fileName) {
        // test write to file
        File taskFile = new File(path + fileName);
        try {
            ArrayList<String> dataStrings = new ArrayList<>();
            for (Task task: tasks) {
                dataStrings.add(taskToDataString(task));
            }

            Files.write(Paths.get(path + fileName), dataStrings);
        } catch (FileNotFoundException fileNotFoundException) {
            try {
                Files.createDirectories(Paths.get(path));
                Files.createFile(Paths.get(path + fileName));
            } catch (IOException ioException) {
                System.out.println("Error caught: " + ioException);
            }
        } catch (IOException ioException) {
            System.out.println("Error caught: " + ioException);
        }
    }

    /**
     * Convert a data string read from duke.txt into task
     *
     * @return the task represented by the string
     */
    private static String taskToDataString(Task task) {
        String taskType = task.getClass().getSimpleName();
        String data = "";
        switch (taskType) {
        case "Todo":
            // task is todo
            data += "T | ";
            data += task.isDone ? "1 | " : "0 | ";
            data += task.description;
            break;
        case "Deadline":
            // task is deadline
            Deadline deadline = (Deadline) task;
            data += "D | ";
            data += deadline.isDone ? "1 | " : "0 | ";
            data += deadline.description + " | " + deadline.by;
            break;
        case "Event":
            // task is event
            Event event = (Event) task;
            data += "E | ";
            data += event.isDone ? "1 | " : "0 | ";
            data += event.description + " | " + event.at;
            break;
        default:
            // not of any task type
            throw new IllegalArgumentException("Task type not recognized: " + taskType);
        }

        return data;
    }

    private static ArrayList<String> taskListToStringList(ArrayList<Task> tasks) {
        for (Task task : tasks) {
            String data = "";
            String taskType = task.getClass().getSimpleName();
            System.out.println(taskType);
        }

        return new ArrayList<String>();
    }
}
