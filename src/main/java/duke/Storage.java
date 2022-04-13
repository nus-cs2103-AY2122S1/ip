package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    static final int TASK_INITIAL = 1;
    static final int TASK_STATUS = 4;
    static final int TASK_DETAILS = 7;
    private final String filepath;
    private final TaskList taskList;
    private int counter = 0;

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
    public String printFileContents(TaskList tasks) throws FileNotFoundException {

        File f = new File(filepath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        while (s.hasNext()) {
            String toAdd = s.nextLine();

            if (toAdd.charAt(TASK_INITIAL) == 'T') {
                String task = toAdd.substring(TASK_DETAILS);

                tasks.addTodo(task);
                counter += 1;

            } else if (toAdd.charAt(TASK_INITIAL) == 'E') {
                String task = toAdd.substring(TASK_DETAILS);
                String name = task.substring(0, task.indexOf("(") - 1);
                String when = task.substring(task.indexOf(":") + 2, task.indexOf(")"));

                tasks.addEvent(name + " /at " + when);
                counter += 1;

            } else if (toAdd.charAt(TASK_INITIAL) == 'D') {
                String task = toAdd.substring(TASK_DETAILS);
                String name = task.substring(0, task.indexOf("(") - 1);
                String when = task.substring(task.indexOf(":") + 2, task.indexOf(")"));

                tasks.addDeadline(name + " /by " + when);
                counter += 1;
            }

            if (toAdd.charAt(TASK_STATUS) == 'X') {
                tasks.addDone(counter);
            }
        }

        if (counter == 0) {
            return "Great job! You have no tasks saved!";
        }

        String result = "Here are the tasks previously saved: \n";
        for (int i = 0; i < counter; i++) {

            Task currTask = tasks.getTasks().get(i);
            result += String.format("%d. %s", i + 1, currTask.toString());
            result += "\n";
        }
        assert counter > 0 : "Counter should be larger than 0";
        return result;

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

        for (int i = 0; i < taskList.getTasks().size(); i++) {
            out.println(taskList.getTasks().get(i).toString());
        }
        out.close();
    }

}
