import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static String saveLocation;
    private static Ui ui = new Ui();

    public Storage(String filePath) {
        saveLocation = String.valueOf(Paths.get(System.getProperty("user.home"), filePath));
    }

    public static void createFile() {
        try {
            File file = new File(saveLocation);
            if (file.createNewFile()) {
                ui.showFileCreated(file.getName());
            } else {
                ui.showFileExists();
            }
        } catch (IOException e) {
            System.out.println("OOPS! Error occurred while creating file.");
        }
    }

    private static Task parseTask(String input) {
        char taskType = input.charAt(4);
        boolean isDone = input.charAt(7) == 'X';
        String taskName = input.substring(10);
        Task newTask;

        switch (taskType) {
        case 'T':
            newTask = new Todo(taskName, isDone);
            break;
        case 'D':
            newTask = new Deadline(taskName, isDone);
            break;
        case 'E':
            newTask = new Event(taskName, isDone);
            break;
        default:
            throw new IllegalStateException("OOPS! Unexpected task type: " + taskType);
        }

        return  newTask;
    }

    public static List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            File file = new File(saveLocation);

            if (file.createNewFile()) {
                ui.showFileCreated(file.getName());
            } else {
                ui.showFileExists();
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    String data = reader.nextLine();
                    tasks.add(parseTask(data));
                }
                reader.close();
            }
        } catch (IOException e) {
            throw new DukeException("OOPS! File not found.");
        }

        return tasks;
    }

    public void saveFile(String input) {
        try {
            FileWriter writer = new FileWriter(saveLocation);
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
