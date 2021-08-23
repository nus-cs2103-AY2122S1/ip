import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements a file handler to be used in reading and writing files.
 *
 * @author Amos Tan
 * @version CS2103T AY21/22 Semester 1
 */
public class DuchessFileHandler {

    /** The save file of the tasks from Duchess. */
    public static File savedDuchess;

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
                todo.setDone(Boolean.parseBoolean(nameAndDone[1]));
                d.add(todo);
                break;
            }
            case 'D': {
                String[] nameByAndDone = task.substring(1).split(",");
                Deadline deadline = new Deadline(nameByAndDone[0], nameByAndDone[1]);
                deadline.setDone(Boolean.parseBoolean(nameByAndDone[2]));
                d.add(deadline);
                break;
            }
            case 'E': {
                String[] nameDayTimeAndDone = task.substring(1).split(",");
                Event event = new Event(nameDayTimeAndDone[0], nameDayTimeAndDone[1], nameDayTimeAndDone[2]);
                event.setDone(Boolean.parseBoolean(nameDayTimeAndDone[3]));
                d.add(event);
                break;
            }
            default: {
                // Should not reach here
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
            FileWriter fw = new FileWriter(savedDuchess);
            for (int i = 1; i < tasks.getSize() + 1; i++) {
                System.out.println("Writing task to file...");
                fw.write(tasks.getTask(i).toFileFormat() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
