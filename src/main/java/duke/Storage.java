package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
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
            if (line.startsWith("T")) {
                String status = line.substring(1, 2);
                String task = line.substring(5);
                Task newTask = new Todo(task, status.equals("1"));
                list.add(newTask);
            } else if (line.startsWith("D")) {
                String status = line.substring(1, 2);
                String temp = line.substring(5);
                String task = line.substring(5, temp.indexOf("|") + 5);
                String due = temp.substring(temp.indexOf("|") + 2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h.mma");
                LocalDateTime parsedDate = LocalDateTime.parse(due, formatter);
                Task newTask = new Deadline(task, parsedDate, status.equals("1"));
                list.add(newTask);
            } else {
                String status = line.substring(1, 2);
                String temp = line.substring(5);
                String task = line.substring(5, line.substring(5).indexOf("|") + 5);
                String due = temp.substring(temp.indexOf("|") + 2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy h.mma");
                LocalDateTime parsedDate = LocalDateTime.parse(due, formatter);
                Task newTask = new Event(task, parsedDate, status.equals("1"));
                list.add(newTask);
            }
            line = bufferedReader.readLine();
        }
        return list;
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
