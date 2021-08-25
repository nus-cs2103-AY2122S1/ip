package duke.storage;

import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates a storage object that provides functionality
 * with regard to reading and writing from a file
 */
public class Storage {

    // instance variable
    private final String filePath;

    // constructors

    /**
     * Constructor for the Storage object
     *
     * @param filePath the path to the location of the file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * A method to read the data from the file and load it as
     * the TaskList
     *
     * @return the TaskList that was saved in the file
     * @throws IOException when the file cannot be found
     */
    public TaskList load() throws IOException {
        TaskList listTask = new TaskList(new ArrayList<>());
        try {
            File f = new File(this.filePath);

            if (!f.exists()) {
                Files.createDirectories(Paths.get(filePath).getParent());
                f.createNewFile();
            }

            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String description;
                String[] tokens = sc.nextLine().split("\\s\\|\\s", 3);
                String type = tokens[0];
                boolean isDone = Integer.parseInt(tokens[1]) == 1;
                String param = tokens[2];

                switch (type) {
                case ("T"):
                    description = param;
                    listTask.add(new Todo(description, isDone));
                    break;
                case ("E"):
                    String[] eventDetails = param.split("\\s\\|\\s");
                    description = eventDetails[0];
                    String date = eventDetails[1];
                    listTask.add(new Event(description, date, isDone));
                    break;
                case ("D"):
                    String[] deadlineDetails = param.split("\\s\\|\\s");
                    description = deadlineDetails[0];
                    String deadline = deadlineDetails[1];
                    listTask.add(new Deadline(description, deadline, isDone));
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listTask;
    }

    /**
     * A method to write the current task list to the file
     *
     * @param task the current TaskList to be written to the file
     */
    public void write(TaskList task) {
        try {
            File f = new File(this.filePath);

            if (!f.exists()) {
                Files.createDirectories(Paths.get(filePath));
                f.createNewFile();
            }

            FileWriter writer = new FileWriter(filePath);

            for (Task t : task.getList()) {
                writer.write(t.databaseString() + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
