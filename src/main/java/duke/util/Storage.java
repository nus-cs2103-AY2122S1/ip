package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;

/**
 * The Storage class encapsulates information
 * and methods pertaining to storing task data in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class Storage {
    private final String relativePath = "./";
    private String filePath;
    private File file;

    /**
     * Creates and initalizes a new Storage instance.
     * <p>
     * Duke's data will be stored in a text file denoted by the
     * specified directory and file names in the currect directory.
     *
     * @param dirName the specified directory name to store Duke's data.
     * @param fileName the specified file name to store Duke's data.
     */
    public Storage(String dirName, String fileName) {
        try {
            File dir = new File(relativePath + dirName);

            /* case when folder does not exist */
            if (!dir.exists()) {
                dir.mkdir();
            }

            this.filePath = relativePath + dirName + "/" + fileName;
            this.file = new File(filePath);
            /* case when file does not exist */
            if (this.file.createNewFile()) {
                writeDefaultTasks();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void writeDefaultTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        Task deadlineTask = new Deadline("buy turkey", "2021-09-10");
        deadlineTask.markDone();
        tasks.add(deadlineTask);
        tasks.add(new ToDo("buy stuffing for turkey"));
        tasks.add(new Event("josh coming over", "tmr night"));
        tasks.add(new ToDo("decorate the place"));
        saveTasks(tasks);
    }

    /**
     * Loads the tasks from the text file specified by the file object.
     *
     * @return Returns the list of tasks loaded.
     */
    public List<Task> loadTasks() {
        Scanner sc = null;
        try {
            sc = new Scanner(this.file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        List<Task> tasks = new ArrayList<>();

        while (sc.hasNext()) {
            String[] tokens = sc.nextLine().split("-");
            String type = tokens[0];
            boolean isDone = tokens[1].equals("1");
            String description = tokens[2];
            Task task = null;

            if (type.equals("T")) {
                task = new ToDo(description);
            } else if (type.equals("D")) {
                task = new Deadline(description, tokens[3]);
            } else if (type.equals("E")) {
                task = new Event(description, tokens[3]);
            } else {
                /* err */
            }

            if (isDone) {
                task.markDone();
            }

            tasks.add(task);
        }

        return tasks;
    }

    /**
     * Saves the tasks to the file specified by filePath.
     *
     * @param tasks The list of tasks to be saved.
     * @throws IOException On failed loading of file.
     */
    public void saveTasks(List<Task> tasks) throws IOException {
        assert tasks != null : "The task list to be saved should not be null.";

        /* re-write the entire contents of the file */
        FileWriter fw = new FileWriter(this.filePath);
        String delim = "-";
        for (Task task : tasks) {
            fw.write(String.join(delim, task.getSaveParameters()));
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}