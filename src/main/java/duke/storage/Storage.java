package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents the file in the hard disk that stores the tasks.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor for a Storage instance that takes in a file path.
     *
     * @param filePath The path to the file storing the tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieves the tasks from the hard disk.
     *
     * @return An ArrayList containing the retrieved tasks.
     */
    public ArrayList<Task> load() {
        File dukeFile = new File(this.filePath);
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(dukeFile);
            while (fileScanner.hasNext()) {
                // Splitting the line according to the characteristics of a task
                String currentLine = fileScanner.nextLine();
                String[] taskStatus = currentLine.split(Pattern.quote(" | "));
                String taskType = taskStatus[0];
                String taskProgress = taskStatus[1];
                String taskDescription = taskStatus[2];
                Task taskToAdd;

                // Creating a Deadline/Event/Todo instance
                if (taskType.equals("T")) {
                    taskToAdd = new Todo(taskDescription);
                } else if (taskType.equals("D")) {
                    taskToAdd = new Deadline(taskDescription, LocalDate.parse(taskStatus[3]));
                } else {
                    taskToAdd = new Event(taskDescription, LocalDate.parse(taskStatus[3]));
                }
                if (taskProgress.equals("1")) {
                    taskToAdd.markAsDone();
                }

                // Adding the created task to the task list
                taskList.add(taskToAdd);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            java.nio.file.Path directoryPath = Paths.get(System.getProperty("user.home"), "data");
            if (!Files.exists(directoryPath)) {
                File directory = new File(String.valueOf(String.valueOf(directoryPath)));
                directory.mkdir();
            }
            return taskList;
        }
    }

    /**
     * Saves the current tasks to the hard drive.
     *
     * @param taskList The task list.
     */
    public void save(TaskList taskList) {
        // Format the task list into a String
        String textToAdd = "";
        ArrayList<Task> tasksList = taskList.getTaskList();
        for (Task task : tasksList) {
            // Initialising a String to store the characteristics of the task
            String currentLine = "";

            // Formatting the task type
            if (task instanceof Todo) {
                currentLine += "T | ";
            } else if (task instanceof Deadline) {
                currentLine += "D | ";
            } else {
                currentLine += "E | ";
            }

            // Formatting the completion status of the task
            if (task.isDone()) {
                currentLine += "1 | ";
            } else {
                currentLine += "0 | ";
            }

            // Formatting the task description
            currentLine += task.getDescription() + " | ";

            // Formatting the date associated with the task
            if (task instanceof Deadline) {
                currentLine += ((Deadline) task).getDate().toString();
            } else if (task instanceof Event) {
                currentLine += ((Event) task).getDate().toString();
            }

            // Concatenate the formatted line to textToAdd
            textToAdd += currentLine + System.lineSeparator();
        }

        // Write to the hard disk
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            fileWriter.write(textToAdd);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Unable to write to ./data/dukeFile.txt");
        }
    }
}
