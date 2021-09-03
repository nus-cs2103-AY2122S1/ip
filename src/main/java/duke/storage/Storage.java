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

    /** Path to data file. */
    private final String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath path to data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
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
        FileWriter fw = new FileWriter(filePath);

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
        File file = new File(filePath);

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

                switch (nextCommand.charAt(1)) {
                case 'D':
                    task = extractDeadline(nextCommand.substring(7)); // [D][X] something by time
                    break;
                case 'E':
                    task = extractEvent(nextCommand.substring(7)); // [D][X] something at time
                    break;
                case 'T': // todos
                    task = new Todo(nextCommand.substring(7)); // disregards [T][X]
                    break;
                default:
                    throw new DataFileChangedException();
                }

                // check if marked as done
                if (nextCommand.charAt(4) == 'X') {
                    task.markAsDone();
                }
                taskList.add(task);
            }
            sc.close();
            return taskList;
        } catch (IndexOutOfBoundsException e) {
            throw new DataFileChangedException();
        }
    }

    /**
     * Extracts a deadline from the data file in the proper format for duke to read.
     *
     * @param text the deadline in the data file.
     * @return a new deadline that represents the deadline from the data file.
     * @throws DataFileChangedException if the data file was changed and any entry contains a wrong format.
     */
    private Deadline extractDeadline(String text) throws DataFileChangedException {
        int lastOccurrenceOfBy = text.lastIndexOf(" (by: "); // in case other bys appear
        String description = text.substring(0, lastOccurrenceOfBy);

        // disregards "( by: " and trailing ")"
        String by = text.substring(lastOccurrenceOfBy + 6, text.length() - 1);

        LocalDateTime dateTime;

        try {
            dateTime = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a"));
        } catch (DateTimeParseException e) {
            throw new DataFileChangedException();
        }

        return new Deadline(description, dateTime);
    }

    /**
     * Extracts an event from the data file in the proper format for duke to read.
     *
     * @param text the event in the data file.
     * @return a new deadline that represents the deadline from the data file.
     * @throws DataFileChangedException if the data file was changed and any entry contains a wrong format.
     */
    private Event extractEvent(String text) throws DataFileChangedException {
        ArrayList<String> parsedInfo = parseEventInfo(text);
        if (parsedInfo.isEmpty()) {
            throw new DataFileChangedException();
        }

        String description = parsedInfo.get(0);
        String date = parsedInfo.get(1);
        String[] eventTimes = {parsedInfo.get(2), parsedInfo.get(3)};

        String startTime = eventTimes[0].trim();
        String endTime = eventTimes[1].trim();

        LocalDate finalDate;
        LocalTime finalStartTime;
        LocalTime finalEndTime;

        try {
            // checks if the formats of the input date and time are correct
            finalDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MMM d yyyy"));
            finalStartTime = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("h:mm a"));
            finalEndTime = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("h:mm a"));
        } catch (DateTimeParseException e) {
            throw new DataFileChangedException();
        }

        return new Event(description, finalDate, finalStartTime, finalEndTime);
    }

    /**
     * Method to parse information of the event.
     *
     * @param text data saved in file.
     * @return ArrayList of description, date, and times of event.
     * @throws DataFileChangedException if the contents of the event in data file is incorrect.
     */
    private ArrayList<String> parseEventInfo(String text) throws DataFileChangedException {
        int lastOccurrenceOfAt = text.lastIndexOf(" (at: ");
        String description = text.substring(0, lastOccurrenceOfAt);
        ArrayList<String> parsedInfo = new ArrayList<>();

        // disregards "( at: " and trailing ")"
        String at = text.substring(lastOccurrenceOfAt + 6, text.length() - 1);

        // prepare variables
        String atWithoutWhiteSpace = at.replaceAll("\\s", "");
        int lengthOfAtNoSpace = atWithoutWhiteSpace.length();

        // throws error if it doesn't even contain sufficient number of characters for correct format
        if (lengthOfAtNoSpace < 22 || lengthOfAtNoSpace > 25) { // MMM d yyyy, HH:mm - HH:mm
            return parsedInfo;
        }

        // find start and end times
        int indexOfComma = at.indexOf(',');
        String date = at.substring(0, indexOfComma).trim(); // at this point, date contains 10 chars YYYY/MM/DD
        String eventDuration = at.substring(indexOfComma + 1).trim();
        String[] eventTimes = eventDuration.split("-");

        // if no "-" present
        if (eventTimes.length != 2) {
            throw new DataFileChangedException();
        }

        parsedInfo.add(description);
        parsedInfo.add(date);
        parsedInfo.add(eventTimes[0]);
        parsedInfo.add(eventTimes[1]);
        return parsedInfo;
    }
}
