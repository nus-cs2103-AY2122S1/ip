package duke.storage;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import duke.task.Task;
import duke.parser.Parser;

/**
 * Class responsible for reading and writing to file for persistent storage
 */
public class Storage {

    private Path dirPath;
    private Path filePath;

    /**
     * Constructor for Storage
     *
     * @param dirPath Path object to the directory containing the file
     * @param filePath Path object to the file storing the task
     */
    public Storage(Path dirPath, Path filePath) {
        this.dirPath = dirPath;
        this.filePath = filePath;
    }

    /**
     * Reads tasks from file, converts them to Task objects,
     * and returns it as an ArrayList<Task>
     *
     * @return ArryaList of Task objects generated from file
     */
    public ArrayList<Task> getTasks() {
        try {
            System.out.println(System.getProperty("user.dir"));
            File taskFile = new File(this.filePath.toString());
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(taskFile);
            while (sc.hasNextLine()) {
                tasks.add(Parser.parseStorage(sc.nextLine()));
            }
            sc.close();
            return tasks;
        } catch (IOException e) {
            System.out.println(e.toString());
            return new ArrayList<>();
        }
    }

    /**
     * Writes tasks from an ArrayList<Task> to file for persistent storage
     *
     * @param tasks ArrayList of tasks to store
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            File taskDir = new File(this.dirPath.toString());
            if (!taskDir.exists()) {
                taskDir.mkdir();
            }
            File taskFile = new File(this.filePath.toString());
            taskFile.createNewFile();
            FileWriter writer = new FileWriter(this.filePath.toString());
            for (Task task : tasks) {
                writer.write(task.saveString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
