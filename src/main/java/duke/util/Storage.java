package duke.util;

import duke.exceptions.CorruptedFileException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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

/**
 * Encapsulates Storage of Duke bot.
 *
 * @author Dickson
 */
public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load TaskList from user's current directory if present.
     */
    public ArrayList<Task> loadFile() throws CorruptedFileException, IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        String pathString = System.getProperty("user.dir") + filePath;
        File f = new File(pathString);
        if (f.createNewFile()) {
            return tasks;
        }
        Scanner s = new Scanner(f);

        while (s.hasNextLine()) {
            Task task;
            String description, dateString, dateTimeString, startTimeString, endTimeString, input = s.nextLine();
            String[] taskInfo;

            switch (input.charAt(1)) {
                case 'T':
                    description = input.substring(7);
                    task = new ToDo(description);
                    break;
                case 'D':
                    taskInfo = input.substring(7).split(" \\(by: ");
                    description = taskInfo[0];
                    dateTimeString = taskInfo[1].substring(0, taskInfo[1].indexOf(")"));

                    LocalDateTime dateTime;
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");

                    try {
                        dateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
                    } catch (DateTimeParseException e) {
                        throw new CorruptedFileException();
                    }

                    task = new Deadline(description, dateTime);
                    break;
                case 'E':
                    taskInfo = input.substring(7).split(" \\(at: ");
                    description = taskInfo[0];
                    taskInfo = taskInfo[1].split("[ -]");
                    dateString = taskInfo[0];
                    startTimeString = taskInfo[1];
                    endTimeString = taskInfo[2];

                    LocalDate date;
                    LocalTime startTime, endTime;
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");

                    try {
                        date = LocalDate.parse(dateString, dateFormatter);
                        startTime = LocalTime.parse(startTimeString, timeFormatter);
                        endTime = LocalTime.parse(endTimeString, timeFormatter);
                    } catch (DateTimeParseException e) {
                        throw new CorruptedFileException();
                    }

                    task = new Event(description, date, startTime, endTime);
                    break;
                default:
                    throw new CorruptedFileException();
            }
            if (input.charAt(4) == 'X') {
                task.markDone();
            }
            tasks.add(task);
        }
        s.close();

        return tasks;
    }

    /**
     * Saves the TaskList as a text file in user's current directory.
     */
    public void saveFile(TaskList taskList) throws IOException {
        String path = System.getProperty("user.dir") + filePath;
        FileWriter myWriter = new FileWriter(path);
        myWriter.write(taskList.toString());
        myWriter.close();
    }
}
