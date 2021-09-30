package duke;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    private File file;

    public Storage() {
        File folder = new File("data");
        folder.mkdir();
        file = new File(folder, "duke.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Returns the tasks loaded from the file
     * @return List containing the loaded tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner reader = new Scanner(this.file);
            while (reader.hasNextLine()) {
                String task = reader.nextLine();
                tasks.add(loadTask(task));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Returns an individual task loaded from the file
     * @param task String representation of the task in the file
     * @return Task containing the loaded task
     */
    public static Task loadTask(String task) {
        String[] parsed = task.split(" \\| ");
        String type = parsed[0];
        String description = parsed[2];
        Task loadedTask;
        if (type.equals("T")) {
            loadedTask = new Todo(description);
        } else if (type.equals("D")) {
            loadedTask = new Deadline(description, LocalDate.parse(parsed[3]));
        } else {
            loadedTask = new Event(description, LocalDate.parse(parsed[3]));
        }
        if (parsed[1].equals("1")) {
            loadedTask.markAsDone();
        }
        return loadedTask;
    }

    /**
     * Saves the tasks in the file
     * @param tasks List containing the current tasks
     */
    public void save(TaskList tasks) {
        this.file.delete();
        try {
            this.file.createNewFile();
            PrintWriter writer = new PrintWriter(this.file);
            for (Task task : tasks.getTasks()) {
                writer.println(task.addToFile());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
