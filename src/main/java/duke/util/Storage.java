package duke.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.UserInputError;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The Storage class that deals with loading tasks from a file and saving tasks to a file.
 */
public class Storage {

    /**
     * Retrieves data from stored database. Return empty task arraylist if .txt does not exist.
     * Print an error message with either an IOException or invalid user input.
     *
     * @return ArrayList of tasks previously stored in database.
     */
    public static ArrayList<Task> readDatabase() {
        Path filePath = Paths.get(System.getProperty("user.dir"), "database", "database.txt");
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (!Files.isRegularFile(filePath)) {
                return tasks;
            }

            Scanner sc = new Scanner(filePath);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                Task task = Task.stringToTask(data);
                tasks.add(task);
            }
            sc.close();
        } catch (IOException e) {
            System.out.println("read file error");
        } catch (UserInputError e) {
            System.out.println("error in task conversion");
        }

        return tasks;
    }

    /**
     * Write current ist of tasks into the database.
     * Print an error message if there is an IOException.
     *
     * @param taskList TaskList of current tasks.
     */
    public static void writeDatabase(TaskList taskList) {
        Path filePath = Paths.get(System.getProperty("user.dir"), "database", "database.txt");
        Path dirPath = Paths.get(System.getProperty("user.dir"), "database");

        try {
            StringBuilder db = new StringBuilder();
            taskList.toArrayList().forEach(x -> db.append(x.convertTaskToString()).append(System.lineSeparator()));
            byte[] dbByte = db.toString().getBytes();

            if (!Files.isDirectory(dirPath)) {
                Files.createDirectory(dirPath);
            }

            if (Files.isRegularFile(filePath)) {
                Files.delete(filePath);
            }

            Files.write(filePath, dbByte);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
