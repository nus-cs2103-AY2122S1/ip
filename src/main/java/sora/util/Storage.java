package sora.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import sora.exception.DataIntegrityException;
import sora.task.Deadline;
import sora.task.Event;
import sora.task.Task;
import sora.task.Todo;

/**
 * Manages the load and save from and to the data file.
 *
 * @author Zhang Shi Chen
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath File path to local storage
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves data stored locally.
     * If file does not exist, then a new file will be created.
     *
     * @param tasks The list of tasks to be saved into local storage
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     *         does not exist but cannot be created, or cannot be opened for any other reason
     */
    public void save(List<Task> tasks) throws IOException {
        assert tasks != null : "Tasks is not initialized";

        // Reformat the list of tasks for storage
        StringBuilder output = new StringBuilder();
        tasks.forEach(task -> output.append(task.toString()).append("\n"));

        // Overwrite the current save file
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(output.toString());
        fw.flush();
        fw.close();
    }

    /**
     * Loads data stored locally.
     * If file does not exist, then a new file will be created.
     *
     * @return An array list of tasks containing any tasks that can be read from local storage
     * @throws DataIntegrityException If the data in the save file is not in the correct format
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     *         does not exist but cannot be created, or cannot be opened for any other reason
     */
    public ArrayList<Task> load() throws DataIntegrityException, IOException {
        // List of tasks to be returned
        ArrayList<Task> tasks = new ArrayList<>();

        // Initialize save file and create parent directory
        File file = new File(filePath);

        // Attempt to create the parent directory and the save file
        if (file.createNewFile()) {
            // File is created, exit function as nothing to read
            return tasks;
        }

        // Attempt to load data
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String input;

        // Read the rest of data and add to list of tasks
        while ((input = br.readLine()) != null) {
            // Add task to list but do not show a confirmation msg
            tasks.add(newTask(input));
        }

        // Close reader
        br.close();

        return tasks;
    }

    private Task newTask(String command) throws DataIntegrityException {
        Task newTask;

        // Obtain relevant info based on type of task
        switch (command.charAt(1)) {
        case 'T': // todo, [T][ ] join sports club
            newTask = new Todo(command.substring(7));
            break;
        case 'D': // deadline, [D][ ] return book (by: Jun 6 2021, 5:12 PM)
            newTask = newDeadline(command);
            break;
        case 'E': // event, [E][ ] project meeting (at: Aug 6 2021, 2:00 PM - 6:00 PM)
            newTask = newEvent(command);
            break;
        default: // gg someone messed with the save file
            throw new DataIntegrityException();
        }

        // Check if task is alr done
        if (command.charAt(4) == 'X') {
            newTask.markAsDone();
        }

        return newTask;
    }

    private Task newEvent(String command) throws DataIntegrityException {
        // [E][ ] project meeting (at: Aug 6 2021, 2:00 PM - 6:00 PM)

        int index = command.lastIndexOf(" (at: ");
        String description = command.substring(7, index);
        String time = command.substring(index + 6, command.length() - 1);

        String[] info = time.split("[,-]");
        info = Arrays.stream(info)
                .map(String::trim)
                .toArray(String[]::new);

        LocalDate date;
        LocalTime startTime, endTime;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm a");

        // Throw exception if command does not follow format
        try {
            date = LocalDate.parse(info[0], DateTimeFormatter.ofPattern("MMM d yyyy"));
            startTime = LocalTime.parse(info[1], timeFormatter);
            endTime = LocalTime.parse(info[2], timeFormatter);
        } catch (DateTimeParseException e) {
            throw new DataIntegrityException();
        }

        return new Event(description, date, startTime, endTime);
    }

    private Task newDeadline(String command) throws DataIntegrityException {
        // [D][ ] return book (by: Jun 6 2021, 5:12 PM)

        int index = command.lastIndexOf(" (by: ");
        String description = command.substring(7, index);
        String time = command.substring(index + 6, command.length() - 1);

        LocalDateTime dateTime;

        // Throw exception if command does not follow format
        try {
            dateTime = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
        } catch (DateTimeParseException e) {
            throw new DataIntegrityException();
        }

        return new Deadline(description, dateTime);
    }
}
