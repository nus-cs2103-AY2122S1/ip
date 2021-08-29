package duke;

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
public class Storage {

    private String path;
    private static String commandResult = "";

    public Storage(String path) {
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
            Storage.commandResult = "File not found, creating empty save file!";
        } catch (IOException ioException) {
            Storage.commandResult = "Oops! Something went wrong: " + ioException.getMessage();
        }
    }

    /**
     * Returns results of commands(if any).
     *
     * @return result of commands.
     */
    public static String getCommandResult() {
        return Storage.commandResult;
    }

    /**
     * Creates Tasks to be saved in FeatureMain.
     *
     * @param fileLine String from save file containing data
     */
    private void createTask(String fileLine) {
        Task.Type type = Task.Type.valueOf(fileLine.substring(0, 1));
        boolean isDone = Integer.parseInt(fileLine.substring(4, 5)) == 1;
        Task task;

        if (type == Task.Type.T) {
            String description = fileLine.substring(8);
            task = new ToDo(type, isDone, description);
        } else if (type == Task.Type.D) {
            int deadTimeLineLocation = fileLine.indexOf("|", 7);
            String description = fileLine.substring(8, deadTimeLineLocation - 1);
            String by = fileLine.substring(deadTimeLineLocation + 2);
            task = new DeadLine(type, isDone, description, by);
        } else if (type == Task.Type.E) {
            int deadTimeLineLocation = fileLine.indexOf("|", 7);
            String description = fileLine.substring(8, deadTimeLineLocation - 1);
            String at = fileLine.substring(deadTimeLineLocation + 2);
            task = new Event(type, isDone, description, at);
        } else {
            task = new Task("", Task.Type.O);
        }
        Ui.setList(task);
    }
    /**
     * Creates Task to be saved in hard drive.
     *
     * @param task Task to be saved.
     */
    public static void writeTask(Task task) {
        String type = task.getType().toString();
        String isDone = task.isDone() ? "1" : "0";
        String toSave = type + " | " + isDone + " | ";

        if (task.getType() == Task.Type.T) {
            String description = task.getDescription();
            toSave += description;
        } else if (task.getType() == Task.Type.D) {
            String description = task.getDescription();
            String by = task.getTime();
            toSave += description + " | " + by;
        } else if (task.getType() == Task.Type.E) {
            String description = task.getDescription();
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
            Storage.commandResult = "Oops! Something went wrong: " + ioException.getMessage();
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
            Storage.commandResult = "Oops! Something went wrong: " + ioException.getMessage();
        }
    }
}
