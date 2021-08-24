package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Deals with laoding tasks from the file and saving tasks in the file.
 */
public class Storage {
    private File file;
    private Ui ui;

    /**
     * Constructs a storage to store tasks. It takes in an Ui instance.
     *
     * @param ui An instance to deal with interactions with the user.
     */
    public Storage(Ui ui) {
        this.ui = ui;
        try {
            String dir = System.getProperty("user.dir");
            Path path = Paths.get(dir, "data");

            // create the data folder if it does not exist
            if (!Files.exists(path)) {
                path = Files.createDirectory(path);
            }

            File file = path.resolve("duke.txt").toFile();
            this.file = file;
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            // this exception should not occur because the input(path) is fixed
        }
    }

    /**
     * Load tasks from the file and add each line to an arraylist.
     *
     * @return An arraylist of strings, each representing a task information.
     */
    public ArrayList<String> load() {
        try {
            Scanner sc = new Scanner(this.file);
            ArrayList<String> tasksInfo = new ArrayList<>();
            while (sc.hasNextLine()) {
                tasksInfo.add(sc.nextLine());
            }
            return tasksInfo;
        } catch (IOException e) {
            this.ui.showLoadingError();
            return null;
        }
    }

    /**
     * Save tasks in the file according to the arraylist of tasks given.
     *
     * @param tasks An arraylist of tasks to be saved.
     */
    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(this.file);
            for (int i = 0; i < tasks.size(); i++) {
                String taskInfo = tasks.get(i).stringToStore();
                fw.write(taskInfo);
            }
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.out.println("Failed to update storage.");
        }
    }
}
