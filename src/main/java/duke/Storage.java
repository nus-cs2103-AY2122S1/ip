package duke;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class representing the storage for tasks.
 */
public class Storage {
    private static final DateTimeParser DATE_TIME_PARSER = new DateTimeParser(new String[]{"MMM dd yyyy"});
    private final Scanner sc;
    private final String path;

    /**
     * A constructor for the storage.
     *
     * @param path The path which the information for the tasks should be stored.
     * @throws IOException If unexpected IO errors still occur when reading the file.
     */
    public Storage(String path) throws IOException {
        this.path = path;
        File storeFile = new File(path);
        storeFile.getParentFile().mkdirs();
        storeFile.createNewFile();
        FileReader fileIn = new FileReader(path);
        sc = new Scanner(fileIn);
    }

    /**
     * Loads the task data from the path and returns it as an array.
     *
     * @return An array of tasks loaded from the path.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> taskData = new ArrayList<>();
        if (sc == null) {
            return taskData;
        }
        while (sc.hasNextLine()) {
            String ln = sc.nextLine();
            String[] temp = ln.split(" \\| ", -1);
            switch (temp[0]) {
            case "T":
                taskData.add(new Todo(temp[2].equals("1"), temp[1]));
                break;
            case "D":
                taskData.add(new Deadline(
                        temp[2].equals("1"),
                        temp[1],
                        temp[3].equals("") ? null : temp[3],
                        temp[4].equals("") ? null : DATE_TIME_PARSER.parse(temp[4])
                ));
                break;
            case "E":
                taskData.add(new Event(
                        temp[2].equals("1"),
                        temp[1],
                        temp[3].equals("") ? null : temp[3],
                        temp[4].equals("") ? null : DATE_TIME_PARSER.parse(temp[4])
                ));
                break;
            default:
                return new ArrayList<>();
            }
        }
        return taskData;
    }
    /**
     * Backups the task data from the task list into the path.
     *
     * @param taskList The task list of which the task data should be backup-ed from.
     * @throws IOException If unexpected IO errors still occur when writing to the file.
     */
    public void backup(TaskList taskList) throws IOException {
        FileWriter fileOut = new FileWriter(path, false);
        fileOut.write(String.join("\n", taskList.toBackupFormat()));
        fileOut.flush();
        fileOut.close();
    }
}
