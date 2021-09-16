package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.ui.Ui;
import task.Task;
import task.TaskDeadline;
import task.TaskEvent;
import task.TaskList;
import task.TaskTodo;

/**
 * Contains all storage functions for Duke.
 */
public class Storage {

    private static final String FILE_LOCATION = "data\\TaskList.txt";
    private static final String PATH_LOCATION = "data";

    /**
     * Saves the task list to a .txt file whose path is specified as FILE_LOCATION.
     *
     * @param tasks task list to save.
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
     * Loads a .txt file from the path specified as FILE_LOCATION.
     *
     * @return Loaded task list.
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
            System.out.println(Ui.MESSAGE_FILE_NOT_FOUND);
            // Create directory if doesn't exist
            createDirectory();
            return new TaskList();

        } catch (ParseException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return new TaskList();
        }
    }

    /**
     * Creates a directory for the save file if non-existent.
     */
    private static void createDirectory() {
        File directory = new File(PATH_LOCATION);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    /**
     * Converts a given string for the txt save file to a valid Task.
     *
     * @param task String from txt save file.
     * @return task.Task corresponding to the string.
     * @throws ParseException Thrown if string from file contains errors.
     */
    private static Task stringToTask(String task) throws ParseException {
        String[] args = task.split("\\t");
        String taskType = args[0];
        try {
            switch (taskType) {
            case "T":
                // Create new To-do
                assert args.length == 3 : "Invalid loaded task";
                return new TaskTodo(args[2], args[1].equals("1"), LocalDateTime.parse(args[3]));
            case "D":
                // Create new Deadline
                assert args.length == 5 || args.length == 6 : "Invalid loaded task";
                return args.length == 5
                        ? new TaskDeadline(args[2], LocalDate.parse(args[3]), null,
                                !args[1].equals("0"), LocalDateTime.parse(args[4]))
                        : new TaskDeadline(args[2], LocalDate.parse(args[3]), args[4],
                                !args[1].equals("0"), LocalDateTime.parse(args[5]));
            case "E":
                // Create new Event
                assert args.length == 5 || args.length == 6 : "Invalid loaded task";
                return args.length == 5
                        ? new TaskEvent(args[2], LocalDate.parse(args[3]), null,
                                !args[1].equals("0"), LocalDateTime.parse(args[4]))
                        : new TaskEvent(args[2], LocalDate.parse(args[3]), args[4],
                                !args[1].equals("0"), LocalDateTime.parse(args[5]));
            default:
                throw new ParseException(Ui.MESSAGE_INVALID_ARG + Ui.MESSAGE_FILE_NOT_READ, 0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(Ui.MESSAGE_INVALID_ARG + Ui.MESSAGE_FILE_NOT_READ, 0);
        }
    }
}

