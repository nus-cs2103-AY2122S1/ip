package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Stores the TaskList to a local file
 */
public class Storage {
    private static final String FILE_DIRECTORY = "./data";
    private static final String FILE_PATH = "./data/duke.txt";
    private static final String DATE_FORMAT = "dd MMM yyyy";
    private static final String DATE_TIME_FORMAT = "dd MMM yyyy HH:mm";
    private static final int COMMAND_INDEX = 0;
    private static final int COMPLETED_INDEX = 1;
    private static final int PRIORITY_INDEX = 2;
    private static final int TASK_INDEX = 3;
    private static final int DATE_TIME_INDEX = 4;

    /**
     * Creates directory and file to store the TaskList if it is not found.
     */
    public static void createFile() {
        try {
            Path path = Paths.get(FILE_DIRECTORY);
            if (!Files.isDirectory(path)) {
                Files.createDirectory(path);
            }
            Path file = Paths.get(FILE_PATH);
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }

    /**
     * Reads the TaskList from local storage.
     *
     * @return List containing the tasks.
     */
    public static List<Task> readData() {
        Storage.createFile();
        List<Task> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(FILE_PATH));
            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] values = task.split(" \\| ");
                assert values.length >= 4 : "Data should be read correctly";
                assert values[COMPLETED_INDEX].equals("1")
                        || values[COMPLETED_INDEX].equals("0")
                        : "Done should be 1 or 0";
                assert values[COMMAND_INDEX].equals("T")
                        || values[COMMAND_INDEX].equals("E")
                        || values[COMMAND_INDEX].equals("D")
                        : "Task type should be T, D or E";
                String taskItem = values[TASK_INDEX];
                boolean isCompleted = values[COMPLETED_INDEX].equals("1");
                int priority = Integer.parseInt(values[PRIORITY_INDEX]);
                switch(values[COMMAND_INDEX]) {
                case "T":
                    list.add(new ToDo(taskItem, isCompleted, priority));
                    break;
                case "E":
                    LocalDateTime dateTime = LocalDateTime.parse(values[DATE_TIME_INDEX], DateTimeFormatter
                            .ofPattern(DATE_TIME_FORMAT));
                    list.add(new Event(taskItem, isCompleted, dateTime, priority));
                    break;
                case "D":
                    LocalDate date = LocalDate.parse(values[DATE_TIME_INDEX], DateTimeFormatter.ofPattern(DATE_FORMAT));
                    list.add(new Deadline(taskItem, isCompleted, date, priority));
                    break;
                default:
                    throw new InvalidTaskTypeException("Task does not have a valid task type.");
                }
            }
        } catch (FileNotFoundException | InvalidTaskTypeException err) {
            System.out.println(err.getMessage());
        }
        return list;
    }

    /**
     * Writes the TaskList into local storage.
     *
     * @param list List of tasks.
     */
    public static void writeData(List<Task> list) {
        Storage.createFile();
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                String parsedTask = task.parseForStorage();
                if (i < list.size() - 1) {
                    parsedTask += "\n";
                }
                fw.write(parsedTask);
            }
            fw.close();
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }
    }
}
