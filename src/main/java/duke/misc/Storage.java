package duke.misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage class to retrieve and store tasks in Duke.
 */
public class Storage {
    private final String currentDirectory = System.getProperty("user.dir");
    private final Path filePath = Paths.get(currentDirectory, "Data", "duke.txt");
    private final boolean filePathExists = Files.exists(filePath);
    private final boolean fileDirExists = Files.exists(filePath.getParent());

    /**
     * Reads data from duke.txt file.
     *
     * @return ArrayList of tasks.
     * @throws IOException In case directory is invalid or file does not exist.
     */
    public ArrayList<Task> readData() throws IOException {
        ArrayList<Task> al = new ArrayList<>();
        if (!filePathExists) {
            return al;
        }
        File file = new File(filePath.toString());
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            String[] args = data.split(" // ");
            Boolean isTaskDone = Integer.parseInt(args[1]) != 0;
            switch(args[0]) {
            case "Todo":
                al.add(new Todo(args[2], isTaskDone));
                break;
            case "Event":
                al.add(new Event(args[2], args[3], isTaskDone));
                break;
            case "Deadline":
                al.add(new Deadline(args[2], args[3], isTaskDone));
                break;
            default:
                break;
            }
        }
        return al;
    }

    /**
     * Writes data into duke.txt file.
     *
     * @param al The ArrayList to write from.
     * @throws IOException In case directory is invalid or file does not exist.
     */
    public void writeData(ArrayList<Task> al) throws IOException {
        if (!fileDirExists) {
            Path parentPath = filePath.getParent();
            File parentDir = parentPath.toFile();
            parentDir.mkdir();
        }
        FileWriter fw = new FileWriter(filePath.toFile());
        for (int i = 1; i <= al.size(); i++) {
            Task task = al.get(i - 1);
            String taskData = task.getData() + "\n";
            fw.write(taskData);
        }
        fw.close();
    }
}
