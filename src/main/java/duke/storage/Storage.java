package duke.storage;

import duke.exception.LoadingException;

import duke.task.Deadline;
import duke.task.Todo;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the class to store the data.
 *
 * @author QIN GUORUI
 */
public class Storage {
    /** The content in the data file. */
    private String content = "";

    /** The recommended working directory path. */
    private static String dir = System.getProperty("user.dir");

    /** Relative file path. */
    private String filePath;

    /** The actual file name. */
    private String file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = filePath.substring(4);
    }

    /**
     * Returns the task list stored in data file.
     *
     * @return The task list.
     * @throws LoadingException The exception related to loading.
     */
    public ArrayList<Task> load() throws LoadingException {
        String dir = System.getProperty("user.dir");
        // inserts correct file path separator on *nix and Windows
        // works on *nix
        // works on Windows
        java.nio.file.Path path = java.nio.file.Paths.get(dir,"data");
        boolean directoryExists = java.nio.file.Files.exists(path);
        if (!directoryExists) {
            new File(dir + "/data").mkdir();
        }
        File f = new File(path + file);
        try {
            Scanner s = new Scanner(f);
            StringBuilder sb = new StringBuilder();
            ArrayList<Task> taskList = new ArrayList<>();
            while (s.hasNextLine()) {
                String curr = s.nextLine();
                int lens = curr.length();
                // No content
                if (lens == 0) {
                    return new ArrayList<>();
                }
                String oneLine = curr + System.lineSeparator();
                sb.append(oneLine);

                // Judge data input to add tasks.
                char type = curr.charAt(0);
                char done = curr.charAt(4);
                if (type == 'T') {
                    Todo todo = new Todo(curr.substring(8, lens));
                    if (done == '1') {
                        todo.markAsDone();
                    }
                    taskList.add(todo);
                } else {
                    String[] parts = curr.substring(8, lens).split(" ~ ");
                    String content = parts[0];
                    String by = parts[1];
                    Task deadlineOrEvent;
                    if (type == 'D') {
                        deadlineOrEvent = new Deadline(content, by);
                    } else {
                        deadlineOrEvent = new Event(content, by);
                    }
                    if (done == '1') {
                        deadlineOrEvent.markAsDone();
                    }
                    taskList.add(deadlineOrEvent);
                }
            }
            content = sb.toString();
            return taskList;
        } catch (FileNotFoundException e) {
            try {
                f.createNewFile();
                throw new LoadingException();
            } catch (IOException io) {
                System.out.println("file not created");
                return null;
            }
        }
    }

    /**
     * Writes the text to data file, which overwrites initial storage.
     *
     * @param filePath The path to data file.
     * @param textToAdd The overwritten text.
     * @throws IOException When write to file fails.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Stores the task to data file.
     *
     * @param task The string format of task representation.
     */
    public void store(String task) {
        String dataFile = dir + "/data" + file;
        try {
            String data = transformToData(task);
            appendToFile(dataFile, data + System.lineSeparator());
            content = content + data + System.lineSeparator();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Transforms the task representation to storage form in data file.
     *
     * @param task The task string representation.
     * @return The string format stored in the data file.
     */
    public static String transformToData(String task) {
        int lens = task.length();
        char type = task.charAt(1);
        char done = task.charAt(4);
        String split = "by: ";
        String prefix = "0";
        String dataForm = "";
        if (done == 'X') {
            prefix = "1";
        }
        if (type == 'T') {
            dataForm = "T | " + prefix + " | " + task.substring(8, lens);
        } else  {
            if (type == 'E') {
                split = "at: ";
            }
            String[] parts = task.substring(8, lens).split(split);
            String content = parts[0];
            int lensContent = content.length();
            content = content.substring(0, lensContent - 2);
            String time = parts[1];
            int lensBy = time.length();
            time = time.substring(0, lensBy - 1);
            dataForm = type + " | " + prefix + " | " + content + " ~ " + time;
        }
        return dataForm;
    }

    /**
     * Appends the text to existing data file.
     *
     * @param filePath The path to data file.
     * @param textToAppend The text tend to add.
     * @throws IOException When the append operation fails.
     */
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Replaces the unmarked task as marked, or just removes the task.
     *
     * @param place The index of task in task list.
     * @param tasks The task list.
     */
    public void replace(int place, TaskList tasks){
        try {
            String dataFile = dir + "/data" + file;
            String[] parts = content.split(System.lineSeparator());
            int lens = parts.length;
            String newData = "";
            if (tasks != null) {
                newData = transformToData(tasks.elementToString(place));
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < lens; i++) {
                String temp =parts[i];
                if (i == place) {
                    if (tasks == null) {
                        continue;
                    } else {
                        temp = newData;
                    }
                }
                temp = temp + System.lineSeparator();
                sb.append(temp);
            }
            content = sb.toString();
            writeToFile(dataFile, content);
        } catch (IOException e) {
            System.out.println("Delete failed.");
        }
    }
}
