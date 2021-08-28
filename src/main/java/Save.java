import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Save {
    public static final String HOME = System.getProperty("user.home");
    public static final String SAVE_LOCATION = String.valueOf(Paths.get(HOME, "Duke.txt"));

    public static void createFile() {
        try {
            File file = new File(SAVE_LOCATION);
            if (file.createNewFile()) {
                System.out.println("New Duke file created: " + file.getName());
            } else {
                System.out.println("YAY! Duke file already exists.");
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


    public static List<Task> readFile() {
        List<Task> taskList = new ArrayList<>();

        try {
            File file = new File(SAVE_LOCATION);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                taskList.add(parseTask(data));
            }
            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("OOPS! Error occurred while reading file.");
        }

        return taskList;
    }

    public static void saveFile(String input) {
        try {
            FileWriter writer = new FileWriter(SAVE_LOCATION);
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
