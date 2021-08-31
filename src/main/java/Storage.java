package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Storage;
import duke.Task;
import duke.TaskList;

public class Storage {

    protected String filepath;
    protected TaskList taskList;
    private int counter = 0;
    static String dash = "__________________________________";

    /**
     * Creates a Storage object that takes in filepath and a TaskList object.
     *
     * @param filepath Filepath of text file to read and write data.
     * @param taskList TaskList of user.
     */
    public Storage(String filepath, TaskList taskList) {
        this.filepath = filepath;
        this.taskList = taskList;
    }


    /**
     * Reads in an existing text file (if it exists) and prints the existing tasks.
     *
     * @throws FileNotFoundException Raise Exception if text file does not exist.
     */
    public void printFileContents() throws FileNotFoundException {

        // System.out.println(filePath);
        File f = new File(filepath); // create a File for the given file path
        // System.out.println(f.exists());
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String toAdd = s.nextLine();

            if (toAdd.charAt(1) == 'T') {
                String task = toAdd.substring(7);
                taskList.addTodo(task);
                counter += 1;

            } else if (toAdd.charAt(1) == 'E') {
                String task = toAdd.substring(7);
                String name = task.substring(0, task.indexOf("(") - 1);
                String when = task.substring(task.indexOf(":") + 2, task.indexOf(")"));

                taskList.addEvent(name + " /at " + when);
                counter += 1;

            } else if (toAdd.charAt(1) == 'D') {
                String task = toAdd.substring(7);
                String name = task.substring(0, task.indexOf("(") - 1);
                String when = task.substring(task.indexOf(":") + 2, task.indexOf(")"));

                taskList.addDeadline(name + " /by " + when);
                counter += 1;
            }

            if (toAdd.charAt(4) == 'X') {
                taskList.addDone(counter);
            }
        }

        for (int i = 0; i < counter; i++) {
            Task currTask = taskList.tasks.get(i);
            System.out.println(i + 1 + "." + currTask.toString());
        }
        System.out.println(dash);
    }

    /**
     * Converts the TaskList into text and stores data in a text file.
     *
     * @param lst Existing list of tasks.
     * @throws IOException Raises Exception if function fails.
     */
    public void writeToFile(ArrayList<Task> lst) throws IOException {

        File directory = new java.io.File(System.getProperty("user.dir") + "/data");
        if (!directory.exists()) {
            directory.mkdir();
        }
        PrintWriter out = new PrintWriter("data/tasks.txt");

        for (int i = 0; i < taskList.tasks.size(); i++) {
            out.println(taskList.tasks.get(i).toString());
        }
        out.close();
    }

}