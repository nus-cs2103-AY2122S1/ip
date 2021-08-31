package lebron;

import lebron.task.Deadline;
import lebron.task.Events;
import lebron.task.Task;
import lebron.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a storage class to handle writing and loading from a file.
 *
 * @author Nigel Tan
 */
public class Storage {

    private File file;
    private String filePath;

    /**
     * Constructor.
     *
     * @param filePath the file path
     * @throws IOException
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
        //if file does not exist
        if (!file.isFile()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    /**
     * Loads the file contents and saves it in an arraylist.
     *
     * @param filePath the file path
     * @return an ArrayList containing tasks
     * @throws FileNotFoundException
     */
    public ArrayList<Task> loadFileContents(String filePath) throws FileNotFoundException {
        final String HORIZONTAL_LINE = "    ____________________________________________________________\n";
        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] splitWords = line.split(" \\| ", 4);
            String taskName = splitWords[0];
            String isDone = splitWords[1];
            Task task;
            switch (taskName) {
            case "T":
                task = new ToDo(splitWords[2]);
                if (isDone.equals("1")) {
                    task.mark();
                }
                taskList.add(task);
                break;
            case "D":
                String deadline = splitWords[3];
                task = new Deadline(splitWords[2], deadline);
                if (isDone.equals("1")) {
                    task.mark();
                }
                taskList.add(task);
                break;
            case "E":
                String at = splitWords[3];
                task = new Events(splitWords[2], at);
                if (isDone.equals("1")) {
                    task.mark();
                }
                taskList.add(task);
                break;
            default:
                System.out.println(HORIZONTAL_LINE
                        + "    :( OOPS! I'm sorry, but I don't know what that means.\n"
                        + HORIZONTAL_LINE);
                break;
            }

        }
        return taskList;
    }

    /**
     * Saves the current list of tasks into the specified file.
     *
     * @param taskList the list of tasks
     * @throws IOException if the stream is invalid
     */
    public void saveToFile(ArrayList<Task> taskList) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            String textToAppend = taskList.get(i).toFile();
            builder.append(textToAppend + "\n");
        }
        FileWriter fw = new FileWriter(filePath);
        fw.write(builder.toString());
        fw.close();
    }
}
