import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataManager {
    private static final File data = new File("./data/data.txt");

    public static ArrayList<Task> readData() {
        try {
            Scanner sc = new Scanner(data);
            ArrayList<Task> loadedTasks = new ArrayList<>();
            while (sc.hasNext()) {
                loadedTasks.add(convertTxtToTasks(sc.nextLine()));
            }
            return loadedTasks;
        } catch (FileNotFoundException e) {
            createNewFile();
            return new ArrayList<>();
        }
    }

    public static void writeToFile(Task task) throws DukeException {
        if (!data.exists()) {
            createNewFile();
        }

        try {
            FileWriter fw = new FileWriter("./data/data.txt", true);
            fw.write(task.convertToTxt() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("IOException: Unable to write to file");
        }
    }

    public static void updateData(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter("./data/data.txt");
            for (Task t: tasks) {
                fw.write(t.convertToTxt());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("IOException: Unable to write to file");
        }
    }

    private static Task convertTxtToTasks(String txt) {
        String[] extracted = txt.split(" \\| ");
        String taskType = extracted[0];
        Task task;

        switch (taskType) {
        case "T":
            task = new ToDo(extracted[2]);
            break;
        case "D":
            task = new Deadline(extracted[2], extracted[3]);
            break;
        case "E":
            task = new Event(extracted[2], extracted[3]);
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + taskType);
        }
        if (extracted[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    private static void createNewFile() {
        try {
            data.getParentFile().mkdirs();
            data.createNewFile();
        } catch (IOException e) {
            System.err.println("IOException: Unable to create directory/file. Your data will not be saved!");
        }
    }
}
