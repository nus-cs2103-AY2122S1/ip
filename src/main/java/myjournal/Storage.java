package myjournal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import myjournal.task.Deadline;
import myjournal.task.Event;
import myjournal.task.Task;
import myjournal.task.Todo;

/**
 * Creates the object Storage.
 *
 * @author felissafaustine
 */
public class Storage {
    private File file;

    /**
     * Constructs the object Storage.
     *
     * @param filePath The path to the file.
     */
    public Storage(String filePath) {
        File file = new File(filePath);
        this.file = file;
    }

    /**
     * Saves the file to hard disk.
     *
     * @param newFile The new file that needs to be saved.
     * @throws IOException The exception thrown when the file is corrupted.
     */
    public void saveFile(String newFile) throws IOException {
        FileWriter writer = new FileWriter(this.file, false);
        writer.write(newFile);
        writer.close();
    }

    /**
     * Loads the file form hard disk.
     *
     * @return The list of the tasks in the file.
     * @throws FileNotFoundException The exception thrown when the file is not found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        String currLine;
        ArrayList<Task> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            currLine = sc.nextLine();
            String taskDescription = currLine.substring(5);
            boolean isDone = currLine.charAt(2) == '1';
            String[] arr = taskDescription.split(Pattern.quote("|"));
            String description = arr[0];
            if (currLine.charAt(0) == 'T') {
                Todo todo = new Todo(description);
                todo.setState(isDone);
                tasks.add(todo);
            } else if (currLine.charAt(0) == 'E') {
                Event event = new Event(description, arr[1]);
                event.setState(isDone);
                tasks.add(event);
            } else {
                Deadline deadline = new Deadline(description, arr[1]);
                deadline.setState(isDone);
                tasks.add(deadline);
            }
        }
        return tasks;
    }
}
