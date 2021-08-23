import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Storage {


    public void createDirectoryFile() throws IOException {
        if (Files.notExists(Path.of("src/data"))) {
            Files.createDirectory(Path.of("src/ata"));
        }
        if (Files.notExists(Path.of("src/data/duke.txt"))) {
            Files.createFile(Path.of("src/data/duke.txt"));
        }
    }
    /**
     * Loads the TaskList stored in predetermined file
     * @return TaskList
     */
    public TaskList load() {
        TaskList tasks = new TaskList();
        Parser p = new Parser();
        try {
            createDirectoryFile();
            File tasksFile = new File("src/data/duke.txt");
            Scanner s = new Scanner(tasksFile);
            while (s.hasNext()) {
                String[] parseFromFile = p.parseFromFile(s.nextLine());
                switch (parseFromFile[0]) {
                case "todo":
                    tasks.addTask(new Todo(parseFromFile));
                    break;
                case "deadline":
                    tasks.addTask(new Deadline(parseFromFile));
                    break;
                case "event":
                    tasks.addTask(new Event(parseFromFile));
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void write(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter("src/data/duke.txt");
            for (int i = 0; i < taskList.getLength(); i++) {
                String taskToWrite = taskList.getTaskByIndex(i).toWrite();
                fw.write(taskToWrite);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        Storage store = new Storage();
        store.load();
    }
}
