package duke.util;

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

import duke.exception.DataIntegrityException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath file path to local storage
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves data stored locally.
     * If file does not exist, then a new file will be created.
     *
     * @param tasks the list of tasks to be saved into local storage
     * @throws IOException if the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason
     */
    public void save(List<Task> tasks) throws IOException {
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
     * @return an array list of tasks containing any tasks that can be read from local storage
     * @throws DataIntegrityException if the data in the save file is not in the correct format
     * @throws IOException if the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason
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
            Task newTask;

            // Obtain relevant info based on type of task
            switch (input.charAt(1)) {
            case 'T': // todo, [T][ ] join sports club
                newTask = new Todo(input.substring(7));
                break;
            case 'D': // deadline, [D][ ] return book (by: Jun 6 2021, 5:12 PM)
                newTask = newDeadline(input);
                break;
            case 'E': // event, [E][ ] project meeting (at: Aug 6 2021, 2:00 PM - 6:00 PM)
                newTask = newEvent(input);
                break;
            default: // gg someone messed with the save file
                throw new DataIntegrityException();
            }

            // Check if task is alr done
            if (input.charAt(4) == 'X') {
                newTask.markAsDone();
            }

            // Add task to list but do not show a confirmation msg
            tasks.add(newTask);
        }

        // Close reader
        br.close();

        return tasks;
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
