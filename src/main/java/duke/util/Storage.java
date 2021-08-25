package duke.util;

import duke.exceptions.UnclearInstructionException;
import duke.exceptions.UnknownException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    /**
     * Constructor method of Storage.
     * 
     * @param filePath file path for data storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from data storage.
     *
     * @return List of tasks.
     */
    public List<Task> loadStorage() {
        File file = new File(filePath);
        if (!file.exists()) {
            createEmptyFile(file);
            return new ArrayList<>();
        }

        try {
            return readFromFile(file);
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: cannot find the file");
            return new ArrayList<>();
        }
    }

    /**
     * Reads tasks from file.
     *
     * @return List of tasks.
     * @throws UnknownException  If task type from the file is not "T", "E" or "D".
     */
    public List<Task> readFromFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        List<Task> tasks = new ArrayList<>();


        try {
            while (sc.hasNextLine()) {
                String text = sc.nextLine();
                String[] itemDetails = text.split("/");

                Task task;
                String task_type = itemDetails[0];

                if (task_type.equals("T")) {
                    task = new Todo(itemDetails[2], itemDetails[1]);

                } else if (task_type.equals("D")) {
                    task = new Deadline(itemDetails[2], itemDetails[1], itemDetails[3]);

                } else if (task_type.equals("E")) {
                    task = new Event(itemDetails[2], itemDetails[1], itemDetails[3]);

                } else {
                    throw new UnknownException();
                }
                tasks.add(task);
            }
        } catch (UnknownException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }

    /**
     * Creates empty file
     */
    public void createEmptyFile(File file) {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: cannot create an empty file for Storage.");
        }
    }

    /**
     * Writes to file
     */
    public void writeToFile(List<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : list) {
            fw.write(task.toFileString() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Saves list of tasks to data storage.
     */
    public void saveToStorage(List<Task> tasks) {
        try {
            writeToFile(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: cannot save to Storage.");
        }
    }
}
