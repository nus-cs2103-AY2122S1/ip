package duke.util;

import duke.task.*;
import duke.exception.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_NAME = "./data/tasks.txt";
    private static final File file = new File(FILE_NAME);

    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> loadedTasks = new ArrayList<>();
            while (sc.hasNextLine()) {
                loadedTasks.add(formatToTaskList(sc.nextLine()));
            }
            return loadedTasks;
        } catch (FileNotFoundException e) {
            newFile();
            return new ArrayList<>();
        }
    }

    private static void newFile() {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("IOException: Error creating directory/file. Your data will not be saved!");
        }
    }

    public static void writeToFile(Task task) throws DukeException {
        if (!file.exists()) {
            newFile();
        }

        try {
            FileWriter fw = new FileWriter(FILE_NAME, true);
            fw.write(task.formatToSave() + System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("IOException: Unable to write to file");
        }
    }

    public static void updateData(ArrayList<Task> tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(FILE_NAME);
            for (Task t: tasks) {
                fw.write(t.formatToSave() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("IOException: Unable to write to file");
        }
    }

    private static Task formatToTaskList(String s) throws DukeException {
        String[] details = s.split(" \\| ");
        Task task;

        switch (details[0]) {
            case "T":
                task = new Todo(details[2]);
                break;
            case "D":
                task = new Deadline(details[2], details[3]);
                break;
            case "E":
                task = new Event(details[2], details[3]);
                break;
            default:
                throw new DukeException("Unexpected value: " + details[0]);
        }
        if (details[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }
}
