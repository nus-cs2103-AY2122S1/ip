package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Storage {
    private final String dukeData;

    /**
     * Constructor for Storage
     *
     * @param dukeData the String that contains the file path
     */
    public Storage(String dukeData) {
        this.dukeData = dukeData;
    }

    /**
     * Create the data file if it does not exist
     *
     * @return the list of tasks
     * @throws IOException if the method txtToArrayList() fails
     */
    public ArrayList<Task> load() throws IOException {
        File tempFile = new File("data/dukeData.txt");
        if (tempFile.getParentFile().mkdir()) {
            System.out.println("directory created");
        }
        if (tempFile.exists()) {
            return txtToArrayList();
        } else {
            tempFile.createNewFile();
            return new ArrayList<>();
        }
    }

    /**
     * Converts a txt file of tasks to ArrayList of tasks
     *
     * @return an ArrayList of tasks
     * @throws IOException if it is unable to read the file, file format is wrong
     */
    public ArrayList<Task> txtToArrayList() throws IOException {
        ArrayList<Task> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(dukeData));
        String line = bufferedReader.readLine();
        while (line != null) {
            String status = line.substring(3, 4);
            if (line.startsWith("T")) {
                Task newTask = new Todo(line.substring(5), status.equals("1"));
                list.add(newTask);
            } else if (line.startsWith("D")) {
                Task newTask = new Deadline(getTaskName(line), getDuration(line.substring(5)), status.equals("1"));
                list.add(newTask);
            } else {
                Task newTask = new Event(getTaskName(line), getDuration(line.substring(5)), status.equals("1"));
                assert line.startsWith("E");
                list.add(newTask);
            }
            line = bufferedReader.readLine();
        }
        return list;
    }

    /**
     * Gets the name of the task
     *
     * @param line the input String
     * @return the task name
     */
    private String getTaskName(String line) {
        return line.substring(5, line.substring(5).indexOf("|") + 5);
    }

    /**
     * Gets the Duration of the task
     *
     * @param line the input String
     * @return the LocalDateTime of the task
     */
    private LocalDateTime getDuration(String line) {
        String due = line.substring(line.indexOf("|") + 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h.mma");
        return LocalDateTime.parse(due, formatter);
    }

    /**
     * Updates the txt file of any changes
     *
     * @param list the updated list of tasks
     * @throws IOException if it is unable to write to the file
     */
    public void updateFile(ArrayList<Task> list) throws IOException {
        FileWriter fileWriter = new FileWriter(dukeData);
        for (Task t : list) {
            fileWriter.write(t.displayTask() + "\n");
        }
        fileWriter.close();
    }
}
