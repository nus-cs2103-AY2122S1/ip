package duchess.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.ToDo;

/**
 * This class implements a file handler to be used in reading and writing files.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class DuchessFileHandler {

    /** The home of the user to search for existing Duchess.*/
    private static final String DATA_FOLDER = "data";
    /** The file name of the existing Duchess.*/
    private static final String FILE_LOCATION = "data/duchess.txt";
    /** The save file of the tasks from Duchess. */
    private static File savedDuchessFile;

    /**
     * Extracts the DuchessList from the save file.
     * @param existingDuchessFile The save file.
     * @return The DuchessList from the save file.
     */
    public static DuchessList extractListFromFile(File existingDuchessFile) {
        String tasksAsString = "";
        try {
            Scanner sc = new Scanner(existingDuchessFile);
            while (sc.hasNextLine()) {
                tasksAsString += sc.nextLine() + "\n";
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return tasksAsString.isBlank() ? new DuchessList() : convertStringToList(tasksAsString);
    }

    /**
     * Converts the given tasks as String to a DuchessList.
     * @param tasksAsString The given tasks as String.
     * @return The DuchessList with the tasks converted from the String
     */
    public static DuchessList convertStringToList(String tasksAsString) {
        DuchessList d = new DuchessList();
        String[] taskStringArray = tasksAsString.split("\n");
        for (String task : taskStringArray) {
            char identifier = task.charAt(0);
            switch (identifier) {
            case 'T': {
                String[] nameAndDone = task.substring(1).split(",");
                ToDo todo = new ToDo(nameAndDone[0]);
                todo.setDoneStatus(Boolean.parseBoolean(nameAndDone[1]));
                d.add(todo);
                break;
            }
            case 'D': {
                String[] nameByAndDone = task.substring(1).split(",");
                Deadline deadline = new Deadline(nameByAndDone[0], Deadline.convertTextToDate(nameByAndDone[1]));
                deadline.setDoneStatus(Boolean.parseBoolean(nameByAndDone[2]));
                d.add(deadline);
                break;
            }
            case 'E': {
                String[] nameDayTimeAndDone = task.substring(1).split(",");
                Event event = new Event(nameDayTimeAndDone[0], Event.convertTextToDate(nameDayTimeAndDone[1]),
                        Event.convertTextToDate(nameDayTimeAndDone[2]));
                event.setDoneStatus(Boolean.parseBoolean(nameDayTimeAndDone[3]));
                d.add(event);
                break;
            }
            default: {
                assert false : "Code execution should not reach here. Something went wrong. "
                        + "Save file is in wrong format.";
                return null;
            }
            }
        }
        return d;
    }

    /**
     * Writes the tasks as String to the save file.
     * @param tasks The tasks inputted by the user of Duchess.
     */
    public static void writeToFile(DuchessList tasks) {
        try {
            FileWriter fw = new FileWriter(savedDuchessFile);
            for (int i = 1; i < tasks.getSize() + 1; i++) {
                fw.write(tasks.getTask(i).toFileFormat() + "\n");
            }
            System.out.println("Writing task to file...");
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Loads the DuchessList from storage if found.
     * @return The DuchessList saved to storage.
     */
    public static DuchessList load() {
        // Solution below adapted from https://www.w3schools.com/java/java_files_create.asp
        try {
            File savedDuchess = new File(FILE_LOCATION);
            DuchessFileHandler.savedDuchessFile = savedDuchess;
            if (savedDuchess.createNewFile()) {
                System.out.println("File created: " + savedDuchess.getName());
                return new DuchessList();
            } else {
                System.out.println("Found saved duchess file!");
                return DuchessFileHandler.extractListFromFile(savedDuchess);
            }
        } catch (IOException e) {
            // Directory does not exist
            System.out.println("Directory does not exist, creating one now.");
            File dir = new File(DATA_FOLDER);
            if (!dir.exists()) {
                dir.mkdir();
            }
            DuchessFileHandler.savedDuchessFile = new File(FILE_LOCATION);
            return new DuchessList();
        }
    }

    /**
     * Clears saved data.
     */
    public static void clearData() {
        File savedDuchess = new File(FILE_LOCATION);
        savedDuchess.delete();
    }
}
