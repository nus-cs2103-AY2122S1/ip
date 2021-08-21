import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 *  This class handles searching for and handling
 *  the save file for the tasks.
 *  Creates the said file and directory if they do not exist.
 *
 * @author Ryan Tian Jun.
 */
public class TaskSave {

    private String path;

    public TaskSave(String path) {
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                createTask(scanner.nextLine());
            }
        } catch (FileNotFoundException fileNotFoundException) {
            createFileAndDirectory();
        } finally {
            this.path = path;
        }
    }

    /**
     * Creates save file and directory if they do not exist.
     */
    private void createFileAndDirectory() {
        String directoryName = "data";
        String fileName = "duke.txt";
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(directoryName + "/" + fileName);
        try {
            FileWriter writeFile = new FileWriter(file.getAbsoluteFile());
            writeFile.write("");
            writeFile.close();
        } catch (IOException ioException) {
            System.out.println("Oops! Something went wrong: " + ioException.getMessage());
        }
    }

    /**
     * Creates Tasks to be saved in FeatureMain.
     *
     * @param fileLine String from save file containing data
     */
    private void createTask(String fileLine) {
        Task.TYPE type = Task.TYPE.valueOf(fileLine.substring(0, 1));
        boolean isDone = Integer.parseInt(fileLine.substring(4, 5)) == 1;
        Task task;

        if (type == Task.TYPE.T) {
            String description = fileLine.substring(8);
            task = new ToDo(type, isDone, description);
        } else if (type == Task.TYPE.D) {
            int deadTimeLineLocation = fileLine.indexOf("|", 7);
            String description = fileLine.substring(8, deadTimeLineLocation - 1);
            String by = fileLine.substring(deadTimeLineLocation + 2);
            task = new DeadLine(type, isDone, description, by);
        } else if (type == Task.TYPE.E) {
            int deadTimeLineLocation = fileLine.indexOf("|", 7);
            String description = fileLine.substring(8, deadTimeLineLocation - 1);
            String at = fileLine.substring(deadTimeLineLocation + 2);
            task = new Event(type, isDone, description, at);
        } else {
            task = new Task("", Task.TYPE.O);
        }
        FeatureMain.setList(task);
    }
    /**
     * Creates Task to be saved in hard drive.
     *
     * @param task Task to be saved.
     */
    public static void writeTask(Task task) {
        String type = task.type.toString();
        String isDone = task.isDone ? "1" : "0";
        String toSave = type + " | " + isDone + " | ";

       if (task.type == Task.TYPE.T) {
           String description = task.description;
           toSave += description;
       } else if (task.type == Task.TYPE.D) {
           String description = task.description;
           String by = task.getTime();
           toSave += description + " | " + by;
       } else if (task.type == Task.TYPE.E) {
           String description = task.description;
           String at = task.getTime();
           toSave += description + " | " + at;
       } else {

       }

        String directoryName = "data";
        String fileName = "duke.txt";

        File file = new File(directoryName + "/" + fileName);
        try {
            FileWriter writeFile = new FileWriter(file.getAbsoluteFile(), true);
            writeFile.write(toSave + "\n");
            writeFile.close();
        } catch (IOException ioException) {
            System.out.println("Oops! Something went wrong: " + ioException.getMessage());
        }
    }

    /**
     * Deletes old save to write new data
     */
    public static void wipeOldSave() {
        String directoryName = "data";
        String fileName = "duke.txt";

        File file = new File(directoryName + "/" + fileName);
        try {
            FileWriter writeFile = new FileWriter(file.getAbsoluteFile());
            writeFile.write("");
            writeFile.close();
        } catch (IOException ioException) {
            System.out.println("Oops! Something went wrong: " + ioException.getMessage());
        }
    }
}
