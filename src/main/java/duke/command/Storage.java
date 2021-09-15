package duke.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
/**
 * This class handles the loading and saving of user tasks.
 */
public class Storage {
    // The location of the file to load.
    private final String filepath;

    /**
     * Constructor for a Storage instance.
     *
     * @param filepath The location of the file to load.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Imports the data from the hard disk to the user's list.
     *
     * @return The array of tasks saved in the user's data.
     * @throws FileNotFoundException If there are problems with reading the file.
     */
    public ArrayList<Task> loadListData() throws FileNotFoundException {

        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(filepath);
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            addTaskToList(sc.nextLine(), tasks);
        }

        return tasks;
    }

    /**
     * Adds the data from a line in the .txt file to the task list.
     *
     * @param lineToAdd The line of the file to add.
     * @param tasks The array representing the task list.
     */
    public static void addTaskToList(String lineToAdd, ArrayList<Task> tasks) {
        assert lineToAdd.length() > 0 : "Line to add cannot be empty!";

        String taskDetails = lineToAdd.substring(7);

        if (lineToAdd.charAt(1) == 'T') {
            addTodoToList(lineToAdd, tasks, taskDetails);
        } else if (lineToAdd.charAt(1) == 'D') {
            addDeadlineToList(lineToAdd, tasks, taskDetails);
        } else {
            addEventToList(lineToAdd, tasks, taskDetails);
        }
    }

    /**
     * Parses to do from string format to add into in task list.
     *
     * @param lineToAdd The string representing the line to add.
     * @param tasks The array representing the task list.
     * @param taskDetails The to do details part of the string.
     */
    private static void addTodoToList(String lineToAdd, ArrayList<Task> tasks, String taskDetails) {
        Task currentTask = new ToDo(taskDetails);
        tasks.add(currentTask);
        if (lineToAdd.charAt(4) == 'X') {
            currentTask.markAsDone();
        }
    }

    /**
     * Parses deadline from string format to add into in task list.
     *
     * @param lineToAdd The string representing the line to add.
     * @param tasks The array representing the task list.
     * @param taskDetails The deadline details part of the string.
     */
    private static void addDeadlineToList(String lineToAdd, ArrayList<Task> tasks, String taskDetails) {
        int separator = taskDetails.indexOf(" (by: ");
        String taskName = taskDetails.substring(0, separator);
        String timeFull = taskDetails.substring(separator + 6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        Task currentTask;

        if (timeFull.length() > 12) {
            String time = timeFull.substring(12, timeFull.length() - 1);
            LocalDate date = LocalDate.parse(timeFull.substring(0, 11), formatter);
            currentTask = new Deadline(taskName, date, time);
        } else {
            LocalDate date = LocalDate.parse(timeFull.substring(0, 11), formatter);
            currentTask = new Deadline(taskName, date);
        }
        tasks.add(currentTask);

        if (lineToAdd.charAt(4) == 'X') {
            currentTask.markAsDone();
        }
    }

    /**
     * Parses event from string format to add into in task list.
     *
     * @param lineToAdd The string representing the line to add.
     * @param tasks The array representing the task list.
     * @param taskDetails The event details part of the string.
     */
    private static void addEventToList(String lineToAdd, ArrayList<Task> tasks, String taskDetails) {
        int separator = taskDetails.indexOf(" (at: ");
        String taskName = taskDetails.substring(0, separator);
        String timeFull = taskDetails.substring(separator + 6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        Task currentTask;

        if (timeFull.length() > 12) {
            String time = timeFull.substring(12, timeFull.length() - 1);
            LocalDate date = LocalDate.parse(timeFull.substring(0, 11), formatter);
            currentTask = new Event(taskName, date, time);
        } else {
            LocalDate date = LocalDate.parse(timeFull.substring(0, 11), formatter);
            currentTask = new Event(taskName, date);
        }

        tasks.add(currentTask);

        if (lineToAdd.charAt(4) == 'X') {
            currentTask.markAsDone();
        }
    }

    /**
     * Saves the last element in the user's list to the .txt file.
     *
     * @param tasks The array of tasks to save in user's data.
     * @throws IOException If there are problems with writing into the file.
     */
    public void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        StringBuilder textToAdd = new StringBuilder();
        for (Task task : tasks) {
            String taskName = task.toString();
            textToAdd.append(taskName).append("\n");
        }

        File directory = new File("data");
        directory.mkdir();

        File file = new File(this.filepath);
        FileWriter fw = new FileWriter(this.filepath);

        if (!file.exists()) {
            file.createNewFile();
        }

        fw.write(textToAdd.toString());
        fw.close();
    }
}
