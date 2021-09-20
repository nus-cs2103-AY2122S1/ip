package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the class that helps to manage the storage of Task files.
 */
public class Storage {

    private static File dir;
    private static File tmp;
    private static Parser parser = new Parser();
    private String filePath;


    /**
     * Constructor for the Storage.
     * @param filePath the file path of the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads existing task lists (If there is one) or creates a new empty task list if a previous one does not exist.
     * @return an ArrayList that acts as a storage for the Tasks.
     * @throws DukeException if you have no pending tasks (list was empty when Duke is started).
     */
    public static ArrayList<duke.Task> load() throws DukeException {
        dir = new File("data");
        dir.mkdirs();
        tmp = new File(dir, "alexa.txt");
        ArrayList<duke.Task> newStorage = new ArrayList<>();
        try {
            boolean successfulCreate = tmp.createNewFile();
            Scanner myReader = new Scanner(tmp);
            boolean hasNoTask = true;
            while (myReader.hasNextLine()) {
                if (hasNoTask) {
                    System.out.println("Welcome back! Here are your last saved tasks!\n");
                }
                hasNoTask = false;
                String data = myReader.nextLine();
                String taskType = data.substring(3, 4);
                switch (taskType) {
                case "T":
                    Todo newToDo = new Todo(data.substring(9));
                    newStorage.add(newToDo);
                    break;
                case "D":
                    String[] deadlineData = parser.storageDeadline(data);
                    Deadline newDeadline = new Deadline(deadlineData[0], deadlineData[1]);
                    newStorage.add(newDeadline);
                    break;
                case "E":
                    String[] eventData = parser.storageEvent(data);
                    Event newEvent = new Event(eventData[0], eventData[1]);
                    newStorage.add(newEvent);
                    break;
                default:
                    break;
                }
                System.out.println("    " + data);
            }
            if (hasNoTask) {
                throw new DukeException("    Nice! You have no pending tasks!");
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("=======================================");
        return newStorage;
    }

    /**
     * Writes new tasks into the text file to be saved.
     * The text file will then be loaded when Duke is started again.
     */
    public static void writeTasks() {
        String sentence = "";
        for (int i = 1; i < TaskList.noOfTasks() + 1; i++) {
            duke.Task currentTask = TaskList.getCurrentTask(i - 1);
            sentence = sentence + i + "." + currentTask.toString() + "\n";
        }
        try {
            PrintWriter writer = new PrintWriter(tmp.getAbsolutePath());
            writer.print("");
            writer.print(sentence);
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
