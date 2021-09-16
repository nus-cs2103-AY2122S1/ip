package duke.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The Storage class is used to read and write data to and from the data file.
 */
public class Storage {
    private String filePath;

    /**
     * Class constructor which specifies the file path where the Storage object will read/write data from/to.
     *
     * @param filePath The specific file path of the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method which searches for the data file and reads each line of the file, converting it into an ArrayList of Task
     * and returns it.
     *
     * @return ArrayList The ArrayList of tasks that was stored in the data file.
     * @throws FileNotFoundException In the case that the file does not exist.
     */
    public ArrayList<Task> readPastData() throws FileNotFoundException {
        ArrayList<Task> savedTasks = new ArrayList<>();
        File data = new File(this.filePath);
        Scanner dataReader = new Scanner(data);
        while (dataReader.hasNext()) {
            String entry = dataReader.nextLine();
            String[] components = entry.split("[\\|]");
            char prefix = entry.charAt(0);
            switch (prefix) {
            case 'T':
                savedTasks.add(readTodo(components));
                break;
            case 'D':
                savedTasks.add(readDeadline(components));
                break;
            case 'E':
                savedTasks.add(readEvent(components));
                break;
            default:
            }
        }
        return savedTasks;
    }

    private Task readTodo(String[] components) {
        String description = components[2].substring(1);
        ToDo t = new ToDo(description);
        String priority = components[3].substring(1);
        t.changePriority(priority);
        if (checkIfDone(components[1])) {
            t.markAsDone();
        }
        return t;
    }

    private Task readDeadline(String[] components) {
        String description = components[2].substring(1);
        String priority = components[3].substring(1);
        String time = components[4].substring(1);
        Deadline d = new Deadline(description, time);
        d.changePriority(priority);
        if (checkIfDone(components[1])) {
            d.markAsDone();
        }

        return d;
    }

    private Task readEvent(String[] components) {
        String description = components[2].substring(1);
        String priority = components[3].substring(1);
        String time = components[4].substring(1);
        Event e = new Event(description, time);
        e.changePriority(getPriority(priority));
        if (checkIfDone(components[1])) {
            e.markAsDone();
        }
        return e;
    }

    private String getPriority(String priority) {
        switch (priority.toLowerCase()) {
        case("low lvl "):
            return "low";
        case("medium lvl! "):
            return "medium";
        case ("high lvl!!! "):
            return "high";
        default:
            return "";
        }
    }

    private boolean checkIfDone(String component) {
        return (Integer.parseInt(component.substring(1, 2)) == 1);
    }

    /**
     * Method that takes in an ArrayList of task and writes it into the data file.
     *
     * @param tasks The ArrayList of task to be written.
     * @throws IOException On Input writing to file failure.
     */
    public void writeCurrentData(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task t : tasks) {
            fw.write(t.toFile() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Method that creates a data file in the specified file path in the Storage object's filePath field.
     */
    public void createDataFile() {
        File data = new File(this.filePath);
        createDataDir();
        try {
            data.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createDataDir() {
        String dirName = this.filePath.split("/")[0];
        File dir = new File(dirName);
        boolean isSuccessful = dir.mkdir();
        if (isSuccessful) {
            System.out.println("Successfully created dir");
        } else {
            System.out.println("Unsuccessful in creating dir");
        }
    }
}
