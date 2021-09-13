package cygnus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import cygnus.task.Deadline;
import cygnus.task.Event;
import cygnus.task.Task;
import cygnus.task.ToDo;

/**
 * Representation of a storage file which can be read from and written to.
 *
 * @author Joshua Yong
 */
public class Storage {

    private static final String dateTimePrintFormat = "EEE dd MMM yyyy hh:mma";
    private static final String datePrintFormat = "EEE dd MMM yyyy";

    private final String filePath;

    /**
     * Class constructor.
     *
     * @param filePath The file path of the file to be used.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses the associated file, returning a collection of Tasks.
     *
     * @return An ArrayList of Tasks corresponding to the data in the file.
     * @throws CygnusException If the file is formatted incorrectly or cannot be found.
     */
    public ArrayList<Task> load() throws CygnusException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                Task newTask;
                try {
                    String taskInfo = line.substring(7);
                    switch (line.charAt(1)) {
                    case 'D':
                        String[] deadlineInfo = taskInfo.split(" \\| by: ");
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(datePrintFormat);
                        LocalDate by = LocalDate.parse(deadlineInfo[1], dateFormatter);
                        newTask = new Deadline(deadlineInfo[0], by);
                        break;
                    case 'E':
                        String[] eventInfo = taskInfo.split(" \\| at: ");
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePrintFormat);
                        LocalDateTime at = LocalDateTime.parse(eventInfo[1], dateTimeFormatter);
                        newTask = new Event(eventInfo[0], at);
                        break;
                    case 'T':
                        newTask = new ToDo(taskInfo);
                        break;
                    default:
                        throw new CygnusException("Error: Data is formatted incorrectly in storage file.");
                    }
                } catch (IndexOutOfBoundsException | DateTimeParseException e) {
                    throw new CygnusException("Error: Data is formatted incorrectly in storage file.");
                }
                if (line.charAt(4) == 'X') {
                    newTask.setDone();
                }
                tasks.add(newTask);
                line = br.readLine();
            }
        } catch (java.io.IOException e) {
            throw new CygnusException("Error: The specified file path for storage cannot be resolved.");
        }
        return tasks;
    }

    /**
     * Writes Task data from a TaskList to the associated file.
     *
     * @param tasks The given TaskList.
     */
    public void save(TaskList tasks) {
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter(filePath);
            int taskCount = tasks.getSize();
            for (int i = 0; i < taskCount; i++) {
                fw.write(tasks.getTask(i).toString() + System.lineSeparator());
            }
            fw.close();
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
