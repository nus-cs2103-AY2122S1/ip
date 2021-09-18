package bubbles.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import bubbles.tasks.Task;
import bubbles.tasks.TaskList;

/**
 * A class that deals with loading bubbles.tasks from the file
 * saved on the hard disk, followed by saving the tasks in the Bubbles bot
 * back into the file after the program ends.
 */
public class Storage {
    private File bubbles;
    private Parser parser;
    private TaskList taskList;

    /**
     * A public constructor to initialise a Storage Object for the
     * Bubbles bot. Each Storage Object has the File that it reads from and writes to,
     * the parser that decodes the user's inputs, and a task list for the Bubbles bot.
     */
    public Storage() {
        this.parser = new Parser();
    }

    /**
     * Loads the reference file of the Storage Object based on the filePath
     * argument provided.
     *
     * @param filePath The path to the designated file with respect to the root working
     *                 directory.
     */
    public void loadFile(String filePath) {
        bubbles = new File(filePath);

        try {
            taskList = readFile();
        } catch (FileNotFoundException e) {
            createFile(filePath);
            taskList = new TaskList();
        }
    }

    private void createFile(String filePath) {
        String[] arr = filePath.split("/");

        File file = new File(filePath);
        File dir = new File(arr[0]);
        dir.mkdir();
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("There is an error while creating the database!");
        }
    }

    private TaskList readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(bubbles);
        TaskList taskList = new TaskList();

        while (sc.hasNext()) {
            String task = sc.nextLine();
            Object[] inputs = parser.formatTask(task);

            taskList.addTask((String) inputs[0], (String) inputs[1], (Boolean) inputs[2]);
        }

        return taskList;
    }

    /**
     * Overwrites the content of the File (referenced by this Storage
     * Object) with the tasks in the taskList. Done when the Bubbles bot is
     * terminated.
     */
    public void writeTasks() {
        ArrayList<Task> tasks = taskList.getTasks();

        try {
            FileWriter fw = new FileWriter(bubbles, false);

            for (Task t : tasks) {
                String task = t.format();

                fw.write(task);
                fw.write(System.lineSeparator());
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Getter for the taskList of this Storage Object.
     *
     * @return The taskList of this Storage Object.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }
}
