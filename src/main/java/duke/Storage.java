package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    protected String filePath;

    /**
     * Creates the 'data' directory and 'jarvis.txt' file in user's hard disk to store user's
     * list of tasks (if the directory and file don't already exit)
     * @param filePath the relative path to location in which the file is to be created
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            File dir = new File("data");
            dir.mkdirs();
            File tmp = new File(dir, "jarvis.txt");
            tmp.createNewFile();
        } catch (IOException e) {
            System.err.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the list of tasks from the user's hard disk and displays it to the user
     * @throws FileNotFoundException if the file ('jarvis.txt') containing the list of tasks
     * cannot be found
     */
    public void retrieveFileContents() throws FileNotFoundException {
        File f = new File(this.filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String currLine = s.nextLine();
            currLine = currLine.substring(2);
            if (currLine.startsWith("[T]")) {
                String description = " " + currLine.substring(7);
                Todo newTodo = new Todo(description);
                TaskList.addTask(newTodo);
                if (currLine.charAt(4) == 'X') {
                    newTodo.markAsDone();
                }
            } else if (currLine.startsWith("[D]")) {
                int currIndex = 7;
                while (!currLine.substring(currIndex).startsWith("by: ")) {
                    currIndex++;
                }
                String description = " " + currLine.substring(7, currIndex - 2);
                String by = currLine.substring(currIndex + 4, currLine.length() - 1);
                Task newDeadline = new Deadline(description, by);
                TaskList.addTask(newDeadline);
                if (currLine.charAt(4) == 'X') {
                    newDeadline.markAsDone();
                }
            } else if (currLine.startsWith("[E]")) {
                int currIndex = 7;
                while (!currLine.substring(currIndex).startsWith("at: ")) {
                    currIndex++;
                }
                String description = " " + currLine.substring(7, currIndex - 2);
                String by = currLine.substring(currIndex + 4, currLine.length() - 1);
                Task newEvent = new Event(description, by);
                TaskList.addTask(newEvent);
                if (currLine.charAt(4) == 'X') {
                    newEvent.markAsDone();
                }
            }
        }
    }
}
