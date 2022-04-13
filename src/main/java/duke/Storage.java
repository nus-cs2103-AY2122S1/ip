package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Storage {
    private static final String LOCAL_STORAGE_LOCATION = "/LocalStorage.txt";
    private static final DateTimeFormatter FORMAT_FROM_LOCAL_STORAGE = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    /** The user's home path string on their computer. */
    private final String HOME_PATH_STRING = System.getProperty("user.home");

    private File localStorageFile;
    private String localStorageFilePath;

    /**
     * Constructor for class Storage
     */
    public Storage() {
        String filePath = HOME_PATH_STRING + LOCAL_STORAGE_LOCATION;
        File localStorageFile = new File(filePath);
        try {
            boolean filecreated = localStorageFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException("No local storage found");
        } finally {
            this.localStorageFile = localStorageFile;
            this.localStorageFilePath = filePath;
        }
    }

    /**
     * Loads the list of tasks from local storage
     *
     * @return list of tasks
     * @throws DukeException If there is no local file to be found
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> toDoList = new ArrayList<>();
        Scanner scanner; // create a Scanner using the File as the source
        try {
            scanner = new Scanner(localStorageFile);
        } catch (FileNotFoundException e) {
            throw new DukeException("You don't have a local file, so I will create a new one just for you!");
        }
        try (Stream<String> localStorageStream = Files.lines(Paths.get(localStorageFilePath))) {
            localStorageStream.parallel().forEach(lineFromLocalStorage -> {
                if (lineFromLocalStorage.contains("[") && lineFromLocalStorage.contains("]")) {
                    char taskType = lineFromLocalStorage.charAt(1);
                    char completed = lineFromLocalStorage.charAt(4);
                    String restOfTheTask = lineFromLocalStorage.substring(7);
                    if (taskType == 'T') {
                        this.addTodo(restOfTheTask, completed, toDoList);
                    } else if (taskType == 'E') {
                        this.addEvent(restOfTheTask, completed, toDoList);
                    } else if (taskType == 'D') {
                        this.addDeadline(restOfTheTask, completed, toDoList);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.close();
        return toDoList;
    }

    /**
     * Adds the Todo from local storage back into toDoList
     *
     * @param restOfTheTask name of the task
     * @param completed whether the task is completed
     * @param toDoList the toDoList to add to
     */
    private void addTodo(String restOfTheTask, char completed, ArrayList<Task> toDoList) {
        Task newTask = new ToDo(restOfTheTask);
        if (String.valueOf(completed).equals("X")) {
            newTask.markAsCompleted();
        }
        toDoList.add(newTask);
    }

    /**
     * Adds the Event from local storage back into toDoList
     *
     * @param restOfTheTask name of the task
     * @param completed whether the task is completed
     * @param toDoList the toDoList to add to
     */
    private void addEvent(String restOfTheTask, char completed, ArrayList<Task> toDoList) {
        String[] parsedEventInput = restOfTheTask.split("\\(at: ", 2);
        String eventTime = parsedEventInput[1].split("\\)", 2)[0];
        LocalDate localDate = LocalDate.parse(eventTime, Storage.getFormatter());
        Task newTask = new Event(parsedEventInput[0], localDate);
        if (String.valueOf(completed).equals("X")) {
            newTask.markAsCompleted();
        }
        toDoList.add(newTask);
    }

    /**
     * Adds the Deadline from local storage back into toDoList
     *
     * @param restOfTheTask name of the task
     * @param completed whether the task is completed
     * @param toDoList the toDoList to add to
     */
    private void addDeadline(String restOfTheTask, char completed, ArrayList<Task> toDoList) {
        String[] parsedDeadlineInput = restOfTheTask.split("\\(by: ", 2);
        String deadlineTime = parsedDeadlineInput[1].split("\\)", 2)[0];
        LocalDate localDate = LocalDate.parse(deadlineTime, Storage.getFormatter());
        Task newTask = new Event(parsedDeadlineInput[0], localDate);
        if (String.valueOf(completed).equals("X")) {
            newTask.markAsCompleted();
        }
        toDoList.add(newTask);
    }

    /**
     * Updates the local storage everytime a change is made
     *
     * @param toDoList the current list of tasks
     */
    public void updateLocalStorage(ArrayList<Task> toDoList) {
        try {
            Files.delete(Paths.get(this.localStorageFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fw;
        try {
            fw = new FileWriter(this.localStorageFilePath, true);
            for (Task task : toDoList) {
                try {
                    fw.write(task.toString() + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns location of local storage
     *
     * @return location of local storage
     */
    public static String getLocalStorageLocation() {
        assert !LOCAL_STORAGE_LOCATION.equals("") : "Local storage should be defined";
        return LOCAL_STORAGE_LOCATION;
    }

    /**
     * Returns the formatter used for local storage
     *
     * @return formatter used for local storage
     */
    public static DateTimeFormatter getFormatter() {
        return FORMAT_FROM_LOCAL_STORAGE;
    }
}
