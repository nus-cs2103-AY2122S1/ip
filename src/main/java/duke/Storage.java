package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.task.Task;

/**
 * The class for accessing and writing to local file which stores task list.
 */
public class Storage {
    private static final String FILE_PATH = new File("").getAbsolutePath().concat("/data/duke.txt");
    private static File storedList;

    /**
     * Loads file from local disc to store list of task.
     */
    public void loadFile() {
        storedList = new File(FILE_PATH);
        try {
            if (!storedList.exists()) {
                if (!storedList.getParentFile().exists()) {
                    storedList.getParentFile().mkdirs();
                }
                storedList.createNewFile();
                System.out.println("Local file created.");
            }
            TaskList.loadList(storedList);
        } catch (IOException e) {
            System.out.println("error when loading file: " + e);
        }
    }

    /**
     * Writes to the file by appending
     *
     * @param s takes in input string
     */
    public static void writeToList(String s) {
        try {
            FileWriter fw = new FileWriter(FILE_PATH, true);
            fw.write(s);
            fw.close();
        } catch (IOException e) {
            System.out.println(":( Error when writing to file");
        }
    }

    /**
     * Saves the file to hard disk
     */
    public static void saveFile() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.println(":( Error when re-writing file");
        }
        ArrayList<Task> arr = TaskList.getTaskList();
        for (Task t : arr) {
            int c = t.getStatusIcon().equals("X") ? 1 : 0;
            String now = t.getType() + " | " + c + " | " + t.getDescription();
            if (!t.getType().equals("T")) {
                now += " | " + t.getTime();
            }
            if (!arr.get(arr.size() - 1).equals(t)) {
                now += "\n";
            }
            writeToList(now);
        }
    }

}
