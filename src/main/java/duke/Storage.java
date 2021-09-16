package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;

/**
 * This class handles file access.
 */
public class Storage {
    /** The path of the .txt file storing task. */
    private final String filePath;

    /**
     * Constructs a storage instance using the given file path.
     *
     * @param filePath The given file path.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a task list read from the .txt file.
     *
     * @return A task list read from the .txt file.
     */
    public ArrayList<Task> readFromTaskTxt() {
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();
            while (sc.hasNextLine()) {
                Task task = Parser.parseDocument(sc.nextLine());
                tasks.add(task);
            }
            sc.close();
            return tasks;
        } catch (FileNotFoundException e) {
            int index = filePath.lastIndexOf('/');
            String directoryName = filePath.substring(0, index + 1);
            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdir();
            }
            return new ArrayList<Task>();
        }
    }

    /**
     * Write the task list to the .txt file
     *
     * @param tasks The given task list.
     */
    public void writeToTaskTxt(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                String textToAdd = task.toTxtFormat() + System.lineSeparator();
                fw.write(textToAdd);
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
