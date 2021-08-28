package lania;

import lania.task.Task;
import lania.task.TaskList;
import lania.task.Deadline;
import lania.task.Event;
import lania.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    /** Represents the path of the file to be retrieved or written. */
    private String filePath;

    /**
     * Constructor for the storage class.
     *
     * @param filePath The path of the file to write or read from.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method reads the file if it exists and stores the data
     * into a TaskList.
     *
     * @return The TaskList containing the tasks specified in the file.
     * @throws  IOException If the directory of file cannot be found.
     */
    public TaskList load() throws IOException {
        Files.createDirectories(Paths.get("data/"));
        File f = new File(filePath);
        if (f.createNewFile()) {
            return new TaskList();
        } else {
            TaskList tasks = new TaskList();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String next = s.nextLine();
                String[] split = next.split("\\|", 4);
                Task t;
                if (split[0].equals("T")) {
                    t = new Todo(split[2]);
                } else if (split[0].equals("D")) {
                    t = new Deadline(split[2], split[3]);
                } else {
                    t = new Event(split[2], split[3]);
                }
                if (split[1].equals("X")) {
                    t.markAsDone();
                }
                tasks.update(t);
            }
            s.close();
            return tasks;
        }
    }

    /**
     * Rewrites the entire file with given tasks,
     * line by line using the appendToFile method.
     *
     * @param tasks The user's TaskList.
     * @throws IOException If unable to append to file.
     */
    public void save(TaskList tasks) throws IOException {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            appendToFile(filePath, task.getStringFormat(), i);
        }
    }

    /**
     * Writes if it is the first task, and appends if otherwise
     * so that the entire file can be rewritten.
     *
     * @param filePath The location of the file to write to.
     * @param textToAppend The text that needs to be added.
     * @param i Checks whether it is the first task.
     * @throws IOException If the program cannot create or write to the file indicated.
     */
    private void appendToFile(String filePath, String textToAppend, int i) throws IOException {
        FileWriter fw = new FileWriter(filePath, i != 0);
        fw.write(textToAppend);
        fw.close();
    }
}
