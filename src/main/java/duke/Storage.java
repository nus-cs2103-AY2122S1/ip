package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;



/**
 * Storage class for saving the tasks to the hard disk.
 */
public class Storage {
    private String filePath;
    private PrintWriter writer;
    private TaskList ls;

    /**
     * Constructor for Storage.
     *
     * @param filePath Path for the .txt file that the tasks are stored in.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Rewrites the .txt file according to the updated TaskList.
     *
     * @param ls The current TaskList.
     */
    public void rewriteFile(TaskList ls) {
        this.ls = ls;
        try {
            FileWriter fw = new FileWriter(filePath, false);
            writer = new PrintWriter(fw);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ls.getSize(); i++) {
            Task task = ls.getTask(i);
            String taskString = task.toString();
            String desc = task.getDesc();
            String addOns = task.additionalDates();
            if (taskString.startsWith("\t[T]")) {
                writer.println("T" + (task.isDone() ? " | 1 | " : " | 0 | ") + desc);
            } else if (taskString.startsWith("\t[D]")) {
                writer.println("D" + (ls.getTask(i).isDone() ? " | 1 | " : " | 0 | ")
                        + desc + " | " + addOns);
            } else {
                writer.println("E" + (task.isDone() ? " | 1 | " : " | 0 | ")
                        + desc + " | " + addOns);
            }
        }
        writer.close();
    }

    /**
     * Loads the saved tasks in the hard disk into a TaskList object.
     *
     * @return A TaskList object representative of the current tasks.
     * @throws IOException If there are issues with reading the file in the hard disk.
     * @throws DukeException If the user input is invalid.
     */
    public TaskList load() throws IOException, DukeException {
        TaskList ls = new TaskList();
        File directory = new File("duke.txt");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File data = new File(filePath);
        data.createNewFile();
        Scanner s = new Scanner(data);
        while (s.hasNext()) {
            ls.addTask(parseTask(s.nextLine()));
        }
        return ls;
    }

    /**
     * Parses the String representation of the tasks and returns their Task representation.
     *
     * @param input String representation of the task.
     * @return Task representation of the task.
     * @throws DukeException If the user input is invalid.
     */
    public Task parseTask(String input) throws DukeException {
        if (input.startsWith("T")) {
            String taskDesc = input.substring(7);
            Todo tTask = new Todo(taskDesc);
            return tTask;
        } else if (input.startsWith("D")) {
            String taskDesc = input.substring(7);
            String taskDate = getDate(input);
            Deadline dTask = new Deadline(taskDesc, taskDate);
            return dTask;
        } else {
            String taskDesc = input.substring(7);
            String taskDate = getDate(input);
            Event eTask = new Event(taskDesc, taskDate);
            return eTask;
        }
    }

    /**
     * Gets the date of the tasks for Deadlines and Events.
     *
     * @param input String representation of the task.
     * @return String representation of the date of the task.
     */
    public String getDate(String input) {
        int endIndex = 0;
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '|') {
                if (count == 3) {
                    endIndex = i;
                }
            }
        }
        return input.substring(endIndex);
    }

}
