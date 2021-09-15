package storage;

import exception.InvalidDateFormat;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage class allows access to the local storage file that contains the data saved.
 * Filepath to the local file is required.
 * If file is not found, tasklist will be initialised to be empty.
 */
public class Storage {

    private String filePath;

    /**
     * Initialises the filepath for access.
     *
     * @param filePath path of file from current location.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Processes the information stored in the local file and convert it into an ArrayList of Task.
     * When file is empty/not found, ArrayList returned will be empty.
     *
     * @return the ArrayList of Task stored in local storage.
     * @throws InvalidDateFormat If the file contains invalid date format and is corrupted.
     */
    public ArrayList<Task> load() throws InvalidDateFormat {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] output = line.split("\\s\\|\\s");
                switch (output[0]) {
                case "T":
                    loadTodo(taskList, output);
                    break;
                case "D":
                    loadDeadline(taskList, output);
                    break;
                case "E":
                    loadEvent(taskList, output);
                    break;
                default:
                    System.out.println("Detected invalid task type. Please check...");
                    break;
                }
            }
        } catch (IOException e1) {
            System.out.println("Something went wrong: " + e1.getMessage());
        }
        return taskList;
    }

    private void loadTodo(ArrayList<Task> taskList, String[] output) {
        Task newTodo;
        if (output.length < 4) {
            newTodo = new Todo(output[2], "", output[1].equals("1"));

        } else {
            newTodo = new Todo(output[2], output[4], output[1].equals("1"));
        }
        taskList.add(newTodo);
    }

    private void loadDeadline(ArrayList<Task> taskList, String[] output) throws InvalidDateFormat {
        Task newDeadline;
        if (output.length < 5) {
            newDeadline = new Deadline(output[2], output[3], "", output[1].equals("1"));

        } else {
            newDeadline = new Deadline(output[2], output[3], output[4], output[1].equals("1"));
        }
        taskList.add(newDeadline);
    }

    private void loadEvent(ArrayList<Task> taskList, String[] output) throws InvalidDateFormat {
        Task newEvent;
        if (output.length < 5) {
            newEvent = new Event(output[2], output[3], "", output[1].equals("1"));

        } else {
            newEvent = new Event(output[2], output[3], output[4], output[1].equals("1"));
        }
        taskList.add(newEvent);
    }

    /**
     * Writes data into the file specified.
     *
     * @param filePath path to the local file used for storage.
     * @param textToAdd the string containing task details
     * @throws IOException If file is unable to be read.
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Abstracts the data of tasks into a string used for writeToFile.
     *
     * @param taskList the ArrayList of tasks.
     */
    public void write(ArrayList<Task> taskList) {

        String output = "";
        String separator = " | ";

        for (Task t : taskList) {
            output = output + t.getType() + separator + ((t.checkDone()) ? 1 : 0) + separator +
                    t.getDescription() + separator + t.getDeadline() + separator + t.getNotes() + "\n";
        }
        try {
            writeToFile(this.filePath, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}