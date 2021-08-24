package ponyo.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import ponyo.data.task.Task;
import ponyo.data.task.TaskList;
import ponyo.data.task.Todo;
import ponyo.data.task.Deadline;
import ponyo.data.task.Event;
import ponyo.data.exceptions.PonyoException;

/**
 * Handles loading and saving of tasks into the file created.
 */
public class Storage {
    protected String filePath;

    /**
     * @param filePath file path of the stored file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private static final String PATH = "src/main/data";
    private static final String FILENAME = "tasks.txt";

    /**
     * @return the list of tasks stored in hard disk.
     */
    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList();
            readTasksFromFile(tasks, filePath);
            return tasks;
        } catch (IOException e) {
            throw new PonyoException("Error loading from file :(");
        }
    }

    /**
     * Checks if the file and folder exists
     *
     * @param filePath path to check
     * @throws IOException if file creation fails
     */
    public void fileFolderCheck(String filePath) throws IOException {
        File file = new File(filePath);
        File directory = new File(PATH);

        if (!directory.exists()) {
            directory.mkdir();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * Read the tasks from the file separated with delimiter (-)
     *
     * @param tasks stores the list of tasks read from the file
     * @param filePath path of file
     * @throws IOException if error reading file
     */
    public void readTasksFromFile(ArrayList tasks, String filePath) throws IOException {
        fileFolderCheck(filePath);
        Scanner read = new Scanner(new File(filePath));
        read.useDelimiter(Pattern.compile("(\\n)| - "));

        while (read.hasNext()) {
            String taskCode = read.next();

            switch (taskCode) {
                case "T":
                    int marked = Integer.parseInt(read.next());
                    String description = read.next();
                    Task t = new Todo(description);
                    if (marked == 1) {
                        t.markAsDone();
                    }

                    tasks.add(t);
                    break;
                case "D":
                    marked = Integer.parseInt(read.next());
                    description = read.next();
                    String by = read.next();
                    t = new Deadline(description, by);
                    if (marked == 1) {
                        t.markAsDone();
                    }

                    tasks.add(t);
                    break;
                case "E":
                    marked = Integer.parseInt(read.next());
                    description = read.next();
                    String at = read.next();
                    t = new Event(description, at);
                    if (marked == 1) {
                        t.markAsDone();
                    }

                    tasks.add(t);
                    break;
            }
        }
        read.close();
    }

    /**
     * Formats the text to be written in the file
     *
     * @param t task to be written into file
     */
    public void fileLineToWrite(Task t) {
        try {
            appendToFile(PATH + "/" + FILENAME, t.toStringInFile() + "\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Retrieve all the contents in the task list.
     * Used when re-writing the contents in the file.
     *
     * @param tasks the task list to be parsed
     */
    public void getFullContents(TaskList tasks) {
        try {
            String allContent = "";
            for (int i = 1; i <= tasks.size(); i++) {
                allContent += tasks.retrieveTask(i).toStringInFile() + "\n";
            }
            overwriteFile(PATH + "/" + FILENAME, allContent);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Appends a given text to the end of the file.
     *
     * @param filePath the path of file to be written
     * @param textToAppend the text to be appended in the file
     * @throws IOException if text appending fails
     */
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        fileFolderCheck(filePath);
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Overwrites the file with the new file content.
     *
     * @param filePath the path of file to be written
     * @param fileContent the text content to be written in the file
     * @throws IOException if overwriting file fails
     */
    public void overwriteFile(String filePath, String fileContent) throws IOException {
        fileFolderCheck(filePath);
        FileWriter fw = new FileWriter(filePath); // create a FileWriter
        fw.write(fileContent);
        fw.close();
    }
}
