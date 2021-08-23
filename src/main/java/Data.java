import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Data {

    public static final File data = new File("./data/data.txt");

    /**
     * Loads the Tasks stored in the form of an ArrayList<Task>.
     * @return An ArrayList of type Task with all Tasks previously stored.
     */
    public static ArrayList<Task> loadData() {
        try {
            Scanner scanner = new Scanner(data);
            ArrayList<Task> loadedTasks = new ArrayList<>();
            while (scanner.hasNext()) {
                try {
                    loadedTasks.add(txtToTasks(scanner.nextLine()));
                } catch (IllegalStateException e) {
                    System.err.println(e);
                }
            }
            return loadedTasks;
        } catch (FileNotFoundException e) {
            makeNewFile();
            return new ArrayList<Task>();
        }
    }

    /**
     * Writes the Task to the current txt file.
     */
    public static void writeToFile(Task task) throws DukeException {
        if (!data.exists()) {
            makeNewFile();
        }
        try {
            FileWriter writer = new FileWriter("./data/data.txt", true);
            writer.write(task.toTxt() + System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Oops! It seems like I am unable to update your Tasks");
        }
    }

    /**
     * Updates the Tasks stored in the txt file.
     */
    public static void updateData(ArrayList<Task> dukeList) throws DukeException {
        try {
            FileWriter writer = new FileWriter("./data/data.txt");
            for (Task task : dukeList) {
                writer.write(task.toTxt());
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("Oops! It seems I am unable to update your Tasks");
        }
    }

    /**
     * Converts txt String into a Task object.
     * @param txt the String that is read from the txt file.
     * @return a new Task interpreted from the txt file.
     */
    public static Task txtToTasks(String txt) {
        String[] read = txt.split(" \\| ");
        String taskCategory = read[0];
        Task task;

        switch(taskCategory) {
            case "D":
                task = new Deadline(read[2], read[3]);
                break;
            case "E":
                task = new Event(read[2], read[3]);
                break;
            case "T":
                task = new Todo(read[2]);
                break;
            default:
                throw new IllegalStateException("Oops! It seems there is a category I do not understand.");
        }
        if (read[1].equals("1")) {
            task.markDone();
        }
        return task;
    }

    /**
     * Creates a new txt file if there currently is nothing stored.
     */
    public static void makeNewFile() {
        try {
            data.getParentFile().mkdirs();
            data.createNewFile();
        } catch (IOException e) {
            System.err.println("Oops! Unable to create a new file. Your Tasks will not be saved.");
        }
    }
}
