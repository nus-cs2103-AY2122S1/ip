package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import task.Task;
import task.TaskDeadline;
import task.TaskEvent;
import task.TaskList;
import task.TaskTodo;

/**
 * Contains all storage functions for Duke
 */
public class Storage {

    private static final String FILE_LOCATION = "data\\TaskList.txt";
    private static final String PATH_LOCATION = "data";

    /**
     * Saves the task list to a .txt file whose path
     * is specified as FILE_LOCATION
     *
     * @param tasks task list to save
     */
    public static void saveList(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(Storage.FILE_LOCATION, false);
            for (Task task : tasks) {
                writer.write(task.saveString());
                writer.write("\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a .txt file from the path
     * specified as FILE_LOCATION
     *
     * @return Loaded task list
     */
    public static TaskList loadList() {
        try {
            File file = new File(Storage.FILE_LOCATION);
            Scanner text = new Scanner(file);

            ArrayList<Task> loaded = new ArrayList<>();
            while (text.hasNextLine()) {
                String task = text.nextLine();
                loaded.add(stringToTask(task));
            }
            return new TaskList(loaded);

        } catch (FileNotFoundException e) {
            System.out.println("Save file not found, creating new file");
            // Create directory if doesn't exist
            File directory = new File(PATH_LOCATION);
            if (!directory.exists()) {
                directory.mkdir();
            }
            return new TaskList();

        } catch (ParseException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new TaskList();
        }
    }

    /**
     * Converts a given string for the .txt save file to a valid Task
     *
     * @param task String from .txt save file
     * @return task.Task corresponding to the string
     * @throws ParseException Thrown if string from file contains errors
     */
    private static Task stringToTask(String task) throws ParseException {
        String[] args = task.split("\\t");
        String taskType = args[0];
        try {
            switch(taskType) {
            case "T":
                return new TaskTodo(args[2], args[1].equals("1"));
            case "D":
                return args.length == 4
                        ? new TaskDeadline(args[2], LocalDate.parse(args[3]), null, !args[1].equals("0"))
                        : new TaskDeadline(args[2], LocalDate.parse(args[3]), args[4], !args[1].equals("0"));
            case "E":
                return args.length == 4
                        ? new TaskEvent(args[2], LocalDate.parse(args[3]), null, !args[1].equals("0"))
                        : new TaskEvent(args[2], LocalDate.parse(args[3]), args[4], !args[1].equals("0"));
            default:
                throw new ParseException("Failed to read task; file not read", 0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException("Invalid task found; file not read", 0);
        }
    }
}
