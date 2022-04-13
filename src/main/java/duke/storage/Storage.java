package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Task;
import duke.commands.Todo;
import duke.data.TaskList;

/**
 * Encapsulates a storage to store the tasks of the user.
 * This class helps with operations concerning the
 * saving of new tasks and loading of old tasks from the hard disk.
 *
 * @author Jason Ng
 * @version Duke Level-10
 */
public class Storage {
    /** Filename of the taskList */
    private static final String FileName = "duke.txt";
    /** directory of taskList to be saved */
    private static final String DirName = "./data/";

    /**
     * Saves current taskList into the hard disk.
     * TaskList is saved as a .txt file.
     * The filename of the file is stored in the attribute Filename of the class.
     *
     * @param taskList The taskList to be saved.
     */
    public void save(TaskList taskList) {
        try {
            ArrayList<Task> arr = taskList.getEntire();
            PrintWriter printWriter = new PrintWriter(DirName + FileName);
            StringBuilder sb = makeStringToWriteToFile(arr);
            printWriter.write(sb.toString());
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads a previously stored taskList from the hard disk.
     * The filename of the file is stored in the attribute Filename of the class.
     * If the file does not exist, an empty taskList is instantiated and returned.
     *
     * @return The previously stored taskList, or if it is unavailable, an empty taskList.
     */
    public static ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File newFolder = new File(DirName);
        newFolder.mkdir();
        File file = new File(DirName + FileName);
        try {
            boolean newFileCreated = file.createNewFile();
            if (!newFileCreated) {
                Scanner sc = new Scanner(file);
                System.out.println("Past tasks found. Use command \"list\" to list previous tasks.");
                sc.useDelimiter(",");
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    List<String> splitLine = Arrays.stream(line.split(","))
                            .map(String::trim)
                            .collect(Collectors.toList());
                    String taskType = splitLine.get(0);
                    boolean isDone = splitLine.get(1).equals("1");
                    String description = splitLine.get(2);

                    Task toAdd;
                    switch (taskType) {
                    case "T":
                        toAdd = new Todo(description);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(splitLine.get(3));
                        toAdd = new Deadline(description, by);
                        break;
                    case "E":
                        LocalDateTime at = LocalDateTime.parse(splitLine.get(3));
                        toAdd = new Event(description, at);
                        break;
                    default:
                        toAdd = new Task(description);
                        break;
                    }

                    if (isDone) {
                        toAdd.markDone();
                    }
                    tasks.add(toAdd);
                }
            } else {
                System.out.println("No past tasks found. Starting with a new list.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Creates a StringBuilder object containing all the content to write into save file.
     *
     * @param arr The array of tasks to be saved.
     * @return The StringBuilder object with the tasks written.
     */
    private StringBuilder makeStringToWriteToFile(ArrayList<Task> arr) {
        StringBuilder sb = new StringBuilder();
        for (Task task : arr) {
            sb.append(task.getLogo());
            sb.append(",");
            sb.append(task.checkDone() ? "1" : "0");
            sb.append(",");
            sb.append(task.getDescription());
            if (task instanceof Event) {
                sb.append(",");
                sb.append(((Event) task).getAt());
            }
            if (task instanceof Deadline) {
                sb.append(",");
                sb.append(((Deadline) task).getBy());
            }
            sb.append(System.lineSeparator());
        }
        return sb;
    }
}
