package duke.storage;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Task;
import duke.commands.Todo;
import duke.data.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Encapsulates a storage to store the tasks of the user.
 * This class helps with operations concerning the
 * saving of new tasks and loading of old tasks from the hard disk.
 *
 * @author: Jason Ng
 * @version: Duke Level-8
 */
public class Storage {
    /** Filename of the taskList */
    private static final String FileName = "duke.txt";

    /**
     * Saves current taskList into the hard disk.
     * TaskList is saved as a .txt file.
     * The filename of the file is stored in the attribute Filename of the class.
     *
     * @param taskList The taskList to be saved.
     */
    public void Save(TaskList taskList) {
        try {
            ArrayList<Task> arr = taskList.getEntire();
            PrintWriter printWriter = new PrintWriter(FileName);
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
            printWriter.write(sb.toString());
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * Loads a previously stored taskList from the hard disk.
     * The filename of the file is stored in the attribute Filename of the class.
     * If the file does not exist, an empty taskList is instantiated and returned.
     *
     * @return The previously stored taskList, or if it is unavailable, an empty taskList.
     */
    public static ArrayList<Task> Load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FileName);
        try {
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
        } catch (FileNotFoundException e) {
            System.out.println("No past tasks found. Starting with a new list.");
        }
        return tasks;
    }
}
