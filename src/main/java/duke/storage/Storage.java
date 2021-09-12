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
                String currentLine = fileScanner.nextLine();
                taskList.add(translateStringToTask(currentLine));
            }
            return taskList;
        } catch (FileNotFoundException e) {
            java.nio.file.Path directoryPath = Paths.get("data");
            if (!Files.exists(directoryPath)) {
                File directory = new File(String.valueOf(String.valueOf(directoryPath)));
                directory.mkdir();
            }
            return taskList;
        }
    }

    private Task translateStringToTask(String lineOfWords) {
        // Splitting the line according to the characteristics of a task
        String[] taskStatus = lineOfWords.split(Pattern.quote(" | "));
        String taskType = taskStatus[0];
        String taskProgress = taskStatus[1];
        String taskTag = taskStatus[2];
        String taskDescription = taskStatus[3];
        Task taskToAdd;

        if (taskType.equals("T")) {
            taskToAdd = new Todo(taskDescription);
        } else if (taskType.equals("D")) {
            taskToAdd = new Deadline(taskDescription, LocalDate.parse(taskStatus[4]));
        } else {
            taskToAdd = new Event(taskDescription, LocalDate.parse(taskStatus[4]));
        }

        if (taskProgress.equals("1")) {
            taskToAdd.markAsDone();
        }

        if (!taskTag.trim().isEmpty()) {
            taskToAdd.setTag(taskTag);
        }

        assert taskToAdd != null : "The task should be initialised as expected";

        return taskToAdd;
    }

    /**
     * Saves the current tasks to the hard drive.
     *
     * @param taskList The task list.
     * @throws IOException If the data cannot be saved in the file.
     */
    public void save(TaskList taskList) throws IOException {
        // Format the task list into a String
        String textToAdd = "";
        ArrayList<Task> tasksList = taskList.getTaskList();
        for (Task task : tasksList) {
            textToAdd += translateTaskToString(task) + System.lineSeparator();
        }

        // Write to the hard disk
        File dukeFile = new File(this.filePath);
        dukeFile.createNewFile();
        FileWriter fileWriter = new FileWriter(this.filePath);
        fileWriter.write(textToAdd);
        fileWriter.close();
    }

    private String translateTaskToString(Task task) {
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

        // Formatting the tag of the task
        currentLine += task.getTag() + " | ";

        // Formatting the task description
        currentLine += task.getDescription() + " | ";

        // Formatting the date associated with the task
        if (task instanceof Deadline) {
            currentLine += ((Deadline) task).getDate().toString();
        } else if (task instanceof Event) {
            currentLine += ((Event) task).getDate().toString();
        }

        assert !currentLine.trim().isEmpty() : "The current line should be filled up with relevant details";

        return currentLine;
    }
}
