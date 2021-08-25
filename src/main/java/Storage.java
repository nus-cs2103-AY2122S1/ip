import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles the loading and saving of user tasks.
 */
public class Storage {
    // The location of the file to load.
    private String filepath;

    /**
     * Constructor for a Storage instance.
     *
     * @param filepath The location of the file to load.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Imports the data from the hard disk to the user's list.
     *
     * @throws FileNotFoundException If there are problems with reading the file.
     */
    public ArrayList<Task> loadListData() throws FileNotFoundException {

        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(filepath);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            addTaskToList(sc.nextLine(), tasks);
        }

        return tasks;
    }

    /**
     * Adds the data from a line in the .txt file to the task list.
     *
     * @param lineToAdd The line of the file to add.
     * @param tasks The array representing the task list.
     */
    public static void addTaskToList(String lineToAdd, ArrayList<Task> tasks) {
        String taskDetails = lineToAdd.substring(7);

        if (lineToAdd.charAt(1) == 'T') {
            Task currentTask = new ToDo(taskDetails);
            tasks.add(currentTask);
            if (lineToAdd.charAt(4) == 'X') {
                currentTask.markAsDone();
            }
        } else if (lineToAdd.charAt(1) == 'D') {
            int separator = taskDetails.indexOf(" (by: ");
            String taskName = taskDetails.substring(0, separator);
            String timeFull = taskDetails.substring(separator + 6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            Task currentTask;

            if (timeFull.length() > 12) {
                String time = timeFull.substring(12, timeFull.length() - 1);
                LocalDate date = LocalDate.parse(timeFull.substring(0, 11), formatter);
                currentTask = new Deadline(taskName, date, time);
            } else {
                LocalDate date = LocalDate.parse(timeFull.substring(0, 11), formatter);
                currentTask = new Deadline(taskName, date);
            }
            tasks.add(currentTask);

            if (lineToAdd.charAt(4) == 'X') {
                currentTask.markAsDone();
            }
        } else {
            int separator = taskDetails.indexOf(" (at: ");
            String taskName = taskDetails.substring(0, separator);
            String timeFull = taskDetails.substring(separator + 6);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
            Task currentTask;

            if (timeFull.length() > 12) {
                String time = timeFull.substring(12, timeFull.length() - 1);
                LocalDate date = LocalDate.parse(timeFull.substring(0, 11), formatter);
                currentTask = new Event(taskName, date, time);
            } else {
                LocalDate date = LocalDate.parse(timeFull.substring(0, 11), formatter);
                currentTask = new Event(taskName, date);
            }
            tasks.add(currentTask);

            if (lineToAdd.charAt(4) == 'X') {
                currentTask.markAsDone();
            }
        }
    }


    /**
     * Saves the last element in the user's list to the .txt file.
     *
     * @throws IOException If there are problems with writing into the file.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        // Writes the data into the file.
        FileWriter fw = new FileWriter(this.filepath);
        String textToAdd = "";
        for (Task task : tasks) {
            String taskName = task.toString();
            textToAdd += taskName + "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }
}
