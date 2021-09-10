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

    private String filePath;
    static private File dir;
    static private File tmp;

    /**
     * Constructor for the Storage.
     * @param filePath the file path of the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * It loads existing task lists (If there is one) or creates a new empty task list if a previous one does not exist.
     * @return an ArrayList that acts as a storage for the Tasks.
     * @throws DukeException if you have no pending tasks (list was empty when Duke is started).
     */
    public static ArrayList<duke.Task> load() throws DukeException {
        dir = new File("data");
        dir.mkdirs();
        tmp = new File(dir, "alexa.txt");
        try {
            boolean successfulCreate = tmp.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        ArrayList<duke.Task> newStorage = new ArrayList<>();
        try {
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
                        int indexOfOpenBracketD = data.indexOf("(");
                        int indexOfCloseBracketD = data.indexOf(")");
                        String deadlineDate = data.substring(indexOfOpenBracketD + 4, indexOfCloseBracketD);
                        String deadlineTitle = data.substring(9, indexOfOpenBracketD);
                        Deadline newDeadline = new Deadline(deadlineTitle, deadlineDate);
                        newStorage.add(newDeadline);
                        break;
                    case "E":
                        int indexOfOpenBracketE = data.indexOf("(");
                        int indexOfCloseBracketE = data.indexOf(")");
                        String eventDate = data.substring(indexOfOpenBracketE + 4, indexOfCloseBracketE);
                        String eventTitle = data.substring(9, indexOfOpenBracketE);
                        Event newEvent = new Event(eventTitle, eventDate);
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
        } catch (FileNotFoundException e) {
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
