package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Storage {

    private File localStorageFile;
    private String localStorageFilePath;

    /**
     * Constructor for class Storage
     *
     * @param filePath path of local storage
     */
    public Storage(String filePath) {
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
                        Task newTask = new ToDo(restOfTheTask);
                        if (String.valueOf(completed).equals("X")) {
                            newTask.markAsCompleted();
                        }
                        toDoList.add(newTask);
                    } else if (taskType == 'E') {
                        String[] parsedEventInput = restOfTheTask.split("\\(at: ", 2);
                        String eventTime = parsedEventInput[1].split("\\)", 2)[0];
                        LocalDate localDate = LocalDate.parse(eventTime, Duke.getFormatter());
                        Task newTask = new Event(parsedEventInput[0], localDate);
                        if (String.valueOf(completed).equals("X")) {
                            newTask.markAsCompleted();
                        }
                        toDoList.add(newTask);
                    } else if (taskType == 'D') {
                        String[] parsedDeadlineInput = restOfTheTask.split("\\(by: ", 2);
                        String deadlineTime = parsedDeadlineInput[1].split("\\)", 2)[0];
                        LocalDate localDate = LocalDate.parse(deadlineTime, Duke.getFormatter());
                        Task newTask = new Event(parsedDeadlineInput[0], localDate);
                        if (String.valueOf(completed).equals("X")) {
                            newTask.markAsCompleted();
                        }
                        toDoList.add(newTask);
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
     * Updates the local storage everytime a change is made
     *
     * @param toDoList the current list of tasks
     */
    public void updateLocalStorage(ArrayList<Task> toDoList) {
        try {
            Files.delete(Paths.get(Duke.getLocalStorageLocation()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileWriter fw;
        try {
            fw = new FileWriter(Duke.getLocalStorageLocation(), true);
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
}
