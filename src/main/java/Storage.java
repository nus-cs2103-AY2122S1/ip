import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    public boolean save(List<Task> tasks) throws DukeException {
        Path dataPath = Paths.get("data");
        boolean dataPathExists = Files.exists(dataPath);
        if (!dataPathExists) {
            File dataDir = new File("data");
            dataDir.mkdir();
        }

        Path fileDir = Paths.get("data","duke.txt");
        try {
            File file = new File(fileDir.toString());
            PrintWriter writer = new PrintWriter(file);
            tasks.forEach(task -> writer.write(task.format() + System.lineSeparator()));
            writer.close();
        } catch (FileNotFoundException e) {
            throw new DukeException(e.getMessage());
        }
        return true;
    }

    public static void main(String[] args) {
        Storage storage = new Storage();
        ToDo task1 = new ToDo("Go run");
        Event task2 = new Event("Dry run", "Today");
        Deadline task3 = new Deadline("Homework", "next week");
        try{
            storage.save(List.of(task1, task2, task3));
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
