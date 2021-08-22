

import exceptions.NoSuchTaskException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates the information for storing and accessing user's data.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructs a new Storage object with the specified file path.
     *
     * @param filePath File path of the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if the specified files and directories exist.
     * Creates the files and directories if it does not exist.
     *
     * @throws IOException Error in creation of files and/or directories.
     */
    private void checkToMake() throws IOException {
        Path path = Paths.get(this.filePath);
        File dir = path.getParent().toFile();

        // makes the specified directory if it does not exist
        if (!dir.exists()) {
            dir.mkdir();
        }

        File dataFile = new File(this.filePath);

        // makes the specified file if it does not exist
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

    }

    /**
     * Saves the tasks added by the user into the data file.
     *
     * @param taskList The list containing the tasks added by the user.
     * @throws IOException Data file cannot be found or written to.
     */
    public void saveTask(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        BufferedWriter bw = new BufferedWriter(fw);

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            bw.write(String.format("%s-%s-%s-%s\n", task.getTag(), task.getStatusIcon(), task.getDescription(), task.getDueDate()));
        }

        bw.close();
        fw.close();
    }

    /**
     * Retrieves the data saved from the data file.
     *
     * @return An arraylist containing all the activities that were added by the user previously.
     * @throws FileNotFoundException Data file could not be found.
     * @throws NoSuchTaskException The data file contains information/tag that is not recognised by the chat bot.
     */
    private ArrayList<Task> loadData() throws FileNotFoundException, NoSuchTaskException {
        File dataFile = new File(this.filePath);
        Scanner scanner = new Scanner(dataFile);
        ArrayList<Task> taskList = new ArrayList<>();


        while (scanner.hasNextLine()) {
            String nextData = scanner.nextLine();

            // get the relevant details from the data
            String[] details = nextData.split("-");
            String type = details[0];
            boolean isDone = details[1] == "[X]";
            String desc = details[2];
            String dueDate = details[3];


            if (type.equals("T")) {
                Task t = new Todo(desc, isDone);
                taskList.add(t);
            } else if (type.equals("D")) {
                Task t = new Deadline(desc, dueDate, isDone);
                taskList.add(t);
            } else if (type.equals("E")) {
                Task t = new Event(desc, dueDate, isDone);
                taskList.add(t);
            } else {
                throw new NoSuchTaskException();
            }
        }

        scanner.close();
        return taskList;
    }

    /**
     * Loads the task into the chat bot.
     *
     * @return An arraylist containing all the activities that were retrieved from the data file.
     * @throws IOException Data file cannot be created, found or opened.
     * @throws NoSuchTaskException The data file contains information/tag that is not recognised by the chat bot.
     */
    public ArrayList<Task> loadTask() throws IOException, NoSuchTaskException {
        checkToMake();
        return loadData();
    }
}
