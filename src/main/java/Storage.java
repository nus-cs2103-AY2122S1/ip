import exception.DataFileChangedException;

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

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task task : taskList) {
            fw.write(task.toString() + "\n");
        }

        fw.flush();
        fw.close();
    }

    public ArrayList<Task> load() throws IOException {
        File file = new File(filePath);

        ArrayList<Task> taskList = new ArrayList<>();

        // creates file if not present, else it does nothing
        if (file.createNewFile()) {
            // exit method if a new file is created
            return taskList;
        }

        Scanner sc = new Scanner(file);

        while(sc.hasNext()) {
            String nextCommand = sc.nextLine();
            Task task;

            switch (nextCommand.charAt(1)) {
            case 'D':
                try {
                    task = extractDeadline(nextCommand.substring(7));   // [D][X] something by time
                    break;
                } catch (DataFileChangedException e) {
                    System.out.println(e.getMessage());
                }
            case 'E':
                try {
                    task = extractEvent(nextCommand.substring(7));      // [D][X] something at time
                    break;
                } catch (DataFileChangedException e) {
                    System.out.println(e.getMessage());
                }
            default:                                                // todos
                task = new Todo(nextCommand.substring(7));          // disregards [T][X]
            }

            if (nextCommand.charAt(4) == 'X') {
                task.markAsDone();
            }

            taskList.add(task);
        }

        sc.close();
        return taskList;
    }

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

    private Event extractEvent(String text) throws DataFileChangedException {
        int lastOccurrenceOfAt = text.lastIndexOf(" (at: ");
        String description = text.substring(0, lastOccurrenceOfAt);

        // disregards "( at: " and trailing ")"
        String at = text.substring(lastOccurrenceOfAt + 6, text.length() - 1);

        // throws error if it doesn't even contain sufficient number of characters for correct format
        if (at.replaceAll("\\s", "").length() < 22 || at.replaceAll("\\s", "").length() > 25) { // MMM d yyyy, HH:mm - HH:mm
            throw new DataFileChangedException();
        }

        int indexOfComma = at.indexOf(',');
        String date = at.substring(0, indexOfComma).trim(); // at this point, date contains 10 chars YYYY/MM/DD
        String eventDuration = at.substring(indexOfComma + 1).trim();
        String[] eventTimes = eventDuration.split("-");

        // if no "-" present
        if (eventTimes.length != 2) {
            throw new DataFileChangedException();
        }

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
}
