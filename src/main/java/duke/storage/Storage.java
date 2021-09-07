package duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DataFileChangedException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.Todo;

/**
 * Handles loading and saving tasks from the data file.
 */
public class Storage {


    /** Represents the deadline keyword. */
    private final char DEADLINE = 'D';

    /** Represents the event keyword. */
    private final char EVENT = 'E';

    /** Represents the todo keyword. */
    private final char TODO = 'T';

    /** Data file. */
    private final File file;

    /**
     * Constructor for Storage class.
     *
     * @param filePath path to data file.
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Saves and writes the current tasks to the data file.
     * If the file does not exist, it will create a new file.
     *
     * @param tasks contains all current tasks.
     * @throws IOException if the named file exists but is a directory
     * rather than a regular file, does not exist but cannot be created,
     * or cannot be opened for any other reason.
     */

    public void save(TaskList tasks) throws IOException {
        ArrayList<Task> taskList = tasks.getTaskList();
        FileWriter fw = new FileWriter(file.getAbsoluteFile());

        for (Task task : taskList) {
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.flush();
        fw.close();
    }

    /**
     * Loads data from data file.
     * If the data file does not exist, a new data file will be created.
     *
     * @return an arraylist of tasks which contains any task read from the data file.
     * @throws IOException  if the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     * @throws DataFileChangedException if the data file was changed and any entry contains a wrong format.
     */
    public ArrayList<Task> load() throws IOException, DataFileChangedException {
        file.getParentFile().mkdirs();

        ArrayList<Task> taskList = new ArrayList<>();

        // if file not present it creates a file, else it does nothing
        if (file.createNewFile()) {
            // exit method if a new file is created
            return taskList;
        }

        Scanner sc = new Scanner(file);

        try {
            while (sc.hasNext()) {
                String nextCommand = sc.nextLine();
                Task task;

                switch (extractTask(nextCommand)) {
                case DEADLINE:
                    task = extractDeadline(extractMessage(nextCommand));
                    break;
                case EVENT:
                    task = extractEvent(extractMessage(nextCommand));
                    break;
                case TODO:
                    task = new Todo(extractMessage(nextCommand));
                    break;
                default:
                    throw new DataFileChangedException();
                }

                if (isMarkedDone(nextCommand)) {
                    task.markAsDone();
                }
                taskList.add(task);
            }
            sc.close();
            return taskList;
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new DataFileChangedException();
        }
    }

    /**
     * Checks if task from data file was marked done.
     *
     * @param input input from data file.
     * @return boolean that indicates if the task has been marked done.
     */
    private boolean isMarkedDone(String input) {
        return input.charAt(4) == 'X';
    }

    /**
     * Extracts the message of the task.
     *
     * @param input input from data file.
     * @return message of the task.
     */
    private String extractMessage(String input) {
        return input.substring(7);
    }

    /**
     * Extracts the letter that indicates the type of task.
     *
     * @param input input from data file.
     * @return the letter indicating the type of task.
     */
    private char extractTask(String input) {
        return input.charAt(1);
    }

    /**
     * Extracts a deadline from the data file in the proper format for duke to read.
     *
     * @param text the deadline in the data file.
     * @return a new deadline that represents the deadline from the data file.
     */
    private Deadline extractDeadline(String text) {
        int lastOccurrenceOfBy = text.lastIndexOf(" (by: "); // in case other bys appear
        String description = text.substring(0, lastOccurrenceOfBy);

        // disregards "( by: " and trailing ")"
        String by = text.substring(lastOccurrenceOfBy + 6, text.length() - 1);

        LocalDateTime dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));

        return new Deadline(description, dateTime);
    }

    /**
     * Extracts an event from the data file in the proper format for duke to read.
     *
     * @param text the event in the data file.
     * @return a new deadline that represents the deadline from the data file.
     */
    private Event extractEvent(String text) {
        ArrayList<String> parsedInfo = parseEventInfo(text);

        String description = parsedInfo.get(0);
        String date = parsedInfo.get(1);
        String startTime = parsedInfo.get(2);
        String endTime = parsedInfo.get(3);

        LocalDate finalDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
        LocalTime finalStartTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("h:mm a"));
        LocalTime finalEndTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("h:mm a"));

        return new Event(description, finalDate, finalStartTime, finalEndTime);
    }

    /**
     * Method to parse information of the event.
     *
     * @param text data saved in file.
     * @return ArrayList of description, date, and times of event.
     */
    private ArrayList<String> parseEventInfo(String text) {
        int lastOccurrenceOfAt = text.lastIndexOf(" (at: ");
        String description = text.substring(0, lastOccurrenceOfAt);
        ArrayList<String> parsedInfo = new ArrayList<>();

        // disregards "( at: " and trailing ")"
        String at = text.substring(lastOccurrenceOfAt + 6, text.length() - 1);

        // find start and end times
        int indexOfComma = at.indexOf(',');
        String date = at.substring(0, indexOfComma).trim(); // at this point, date contains 10 chars YYYY/MM/DD
        String eventDuration = at.substring(indexOfComma + 1).trim();
        String[] eventTimes = eventDuration.split("-");

        parsedInfo.add(description.trim());
        parsedInfo.add(date.trim());
        parsedInfo.add(eventTimes[0].trim());
        parsedInfo.add(eventTimes[1].trim());
        return parsedInfo;
    }
}
