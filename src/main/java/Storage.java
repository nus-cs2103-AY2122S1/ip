import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    public boolean save(List<Task> tasks) throws DukeException {
        Path dirPath = Paths.get("data");
        boolean dataPathExists = Files.exists(dirPath);
        if (!dataPathExists) {
            File dataDir = new File("data");
            dataDir.mkdir();
        }

        Path filePath = Paths.get("data","duke.txt");
        try {
            File file = new File(filePath.toString());
            PrintWriter writer = new PrintWriter(file);
            tasks.forEach(task -> writer.write(task.format() + System.lineSeparator()));
            writer.close();
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
        return true;
    }

    private Task convertStringToTask(String string) {
        String[] arr = string.split(",");
        Task task = null;
        if (arr[0].trim().contains("T")) {
            task = new ToDo(arr[2].trim(), arr[1].equals("1"));
        } else if (arr[0].trim().contains("E")) {
            task = new Event(arr[2].trim(), arr[1].equals("1"), arr[3].trim());
        } else if (arr[0].trim().contains("D")) {
            task = new Deadline(arr[2].trim(), arr[1].equals("1"), arr[3].trim());
        }
        return task;
    }

    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        Path filePath = Paths.get("data", "duke.txt");
        File file = new File(filePath.toString());
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                tasks.add(convertStringToTask(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            save(List.of());
        }
        return tasks;
    }

    public static void main(String[] args) {
        Storage storage = new Storage();
        ToDo task1 = new ToDo("Go run");
        Event task2 = new Event("Dry run", "Today");
        Deadline task3 = new Deadline("Homework", "next week");
        try {
            storage.save(List.of(task1, task2, task3));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        try {
            List<Task> tasks = storage.load();
            tasks.forEach(System.out::println);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
