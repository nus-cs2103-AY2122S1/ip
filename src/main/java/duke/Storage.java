package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * A class that helps in storing the class offline.
 *
 * @author mrmrinal
 */
public class Storage {

    private static final String PATH_FOLDER = "." + File.separator + "data" + File.separator;
    private final String path;
    private final File file;

    /**
     * Constructor to create new Storage instance.
     * @param filePath the path of the data.txt file
     */
    public Storage(String filePath) {
        this.path = filePath;
        file = new File(path);
    }

    /**
     * Method that loads the offline txt file tasks into the bot.
     *
     * @return List that the dukebot uses
     */
    public List<Task> loadIntoDuke() {
        List<Task> items = new ArrayList<>();
        assert this.path.equals("." + File.separator + "data"
                + File.separator + "duke.txt")
                : "File path is not correct";
        try {
            if (!file.createNewFile()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    detectTask(sc, items);
                }
            }
            return items;
        } catch (Exception e) {
            fixFileFolderProblems();
            return items;
        }
    }

    private void detectTask(Scanner sc, List<Task> items) {
        String task = sc.nextLine();
        if (task.startsWith("T")) {
            assert task.startsWith("T | 0 |") || task.startsWith("T | 1 |")
                    : "Error with todo formatting";
            items.add(new ToDo(
                    task.substring(8), Integer.parseInt(task.substring(4, 5))));
        } else if (task.startsWith("D")) {
            assert task.startsWith("D | 0 |") || task.startsWith("D | 1 |")
                    : "Error with deadline formatting";
            String taskSubstring = task.substring((8));
            items.add(new Deadline(
                    taskSubstring.substring(0, taskSubstring.indexOf("|")),
                    taskSubstring.substring(taskSubstring.indexOf("|") + 2),
                    Integer.parseInt(task.substring(4, 5))
            ));
        } else {
            String taskSubstring = task.substring((8));
            assert task.startsWith("E | 0 |") || task.startsWith("E | 1 |")
                    : "Error with event formatting";
            items.add(new Event(
                    taskSubstring.substring(0, taskSubstring.indexOf("|")),
                    taskSubstring.substring(taskSubstring.indexOf("|") + 2),
                    Integer.parseInt(task.substring(4, 5))
            ));
        }
    }

    /**
     * Adds New task to storage.
     *
     * @param task Task to be added to storage
     */
    public void addNewTask(Task task) {
        try {
            String input = task.toStorageString();
            Files.write(Paths.get(path), input.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes tasks in ArrayList to the offline storage.
     *
     * @param taskList Tasklist storing all tasks in the bot
     */
    public void writeToFile(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(path, false);
            PrintWriter pw = new PrintWriter(fw, false);
            pw.flush();
            pw.close();
            fw.close();
            for (int i = 0; i < taskList.getSize(); i++) {
                String input = taskList.getTask(i + 1).toStorageString();
                Files.write(Paths.get(path), input.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@@author mrmrinal-reused
    //Reused from https://github.com/bharathcs/ip/blame/master/src/main/java/duke/Storage.java
    // with minor modifications
    private void fixFileFolderProblems() {
        try {
            if (!Files.isDirectory(Path.of(PATH_FOLDER))) {
                Files.createDirectory(Path.of(PATH_FOLDER));
            }
            if (!Files.isRegularFile(Path.of(path))) {
                Files.createFile(Path.of(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //@author
}
