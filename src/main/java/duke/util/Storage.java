package duke.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tasks.Task;

public class Storage {

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

    public static void writeDatabase(ArrayList<Task> list) {
        Path filePath = Paths.get(System.getProperty("user.dir"), "database", "database.txt");
        Path dirPath = Paths.get(System.getProperty("user.dir"), "database");

        try {
            StringBuilder db = new StringBuilder();
            list.forEach(x -> db.append(x.taskToString() + System.lineSeparator()));
            byte[] dbByte = db.toString().getBytes();
            if (!Files.isDirectory(dirPath)) {
                Files.createDirectory(dirPath);
            }
            if (Files.isRegularFile(filePath)) {
                Files.delete(filePath);
            }
            Files.write(filePath, dbByte);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
