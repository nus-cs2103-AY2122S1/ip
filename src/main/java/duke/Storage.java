package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.WrongCommandFormatException;
import duke.tasktype.Deadline;
import duke.tasktype.Event;
import duke.tasktype.Task;
import duke.tasktype.Todo;


/**
 * Class that represents the storage.
 *
 * @author Houten Teo
 * @version CS2103T week 6
 */
public class Storage {
    private MyList list;
    private String filePath;

    /**
     * Constructor for the storage class
     * @param list the list to load the storage data into
     */
    public Storage(MyList list, String filePath) {
        this.list = list;
        this.filePath = filePath;
    }

    /**
     * Loads the data from Data.txt file into the list if any.
     */
    public void load() {
        try {
            File dataFile = new File(this.filePath);
            Scanner s = new Scanner(dataFile);
            Duke.setFormat(s.nextLine());
            while (s.hasNextLine()) {
                String input = s.nextLine();
                Task t = getTaskFromString(input);
                this.list.loadTask(t);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No data to load");
        }
    }

    /**
     * Takes the string representation of the task and converts it back into
     * the corresponding task object.
     * @param s The string representation of the task.
     * @return The corresponding task object.
     */
    public Task getTaskFromString(String s) {
        Task t = null;
        try {
            t = getTask(s);
        } catch (WrongCommandFormatException e) {
            Ui.formatExceptionMessage(e);
            Ui.loadingError();
        }
        return t;
    }

    /**
     * Converts the string representation of the task into its task object.
     * @param s The string representation
     * @return The task corresponding to the string.
     * @throws WrongCommandFormatException
     */
    public Task getTask(String s) throws WrongCommandFormatException {
        assert (s.length() > 6);
        String taskType = s.substring(0, 3);
        String taskDescription = s.substring(7);
        Task t = null;

        if (s.substring(3, 6).equals("[X]")) {
            switch (taskType) {
            case "[T]":
                t = new Todo(taskDescription, true);
                break;
            case "[D]":
                t = new Deadline(taskDescription, true);
                break;
            case "[E]":
                t = new Event(taskDescription, true);
                break;
            default:
                // Leaves Task t as null
            }
        } else {
            switch (taskType) {
            case "[T]":
                t = new Todo(taskDescription, false);
                break;
            case "[D]":
                t = new Deadline(taskDescription, false);
                break;
            case "[E]":
                t = new Event(taskDescription, false);
                break;
            default:
                //leaves Task t as null
            }
        }
        return t;
    }

    /**
     * Stores the string representation of a task into the Data.txt file.
     */
    public void writeToFile() {
        try {
            int listLength = this.list.getListSize();
            String input = Duke.getFormat() + "\n";
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 0; i < listLength; i++) {
                Task t = this.list.getTask(i);
                input += t.createData() + "\n";
            }
            fw.write(input);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
