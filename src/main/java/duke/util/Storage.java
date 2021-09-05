package duke.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * The Storage class that deals with loading tasks from a file and saving tasks to a file.
 */
public class Storage {

    /**
     * Retrieves data from stored database. Return empty task arraylist if .txt does not exist.
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

            Scanner reader = new Scanner(filePath);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                Task task = Task.stringToTask(data);
                tasks.add(task);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("read file error");
        }

        return tasks;
    }

    /**
     * Write current ist of tasks into the database.
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
