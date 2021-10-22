package duke.util;

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

import duke.exceptions.CorruptedFileException;
import duke.exceptions.EmptyListException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * Encapsulates Storage of Duke bot.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath user filepath to store text file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads data from the text file if present in the user's file path.
     *
     * @return arraylist of tasks
     * @throws CorruptedFileException if invalid data format in loaded text file
     * @throws IOException if given file path is a directory instead of a text file
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
            String input = s.nextLine();

            switch (input.charAt(1)) {
            case 'T':
                task = loadToDo(input);
                break;
            case 'E':
                task = loadEvent(input);
                break;
            case 'D':
                task = loadDeadline(input);
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
     * Loads todo data into a todo task.
     *
     * @param command todo task data string
     * @return todo task
     */
    private Task loadToDo(String command) {
        Task task;
        String description;

        description = command.substring(7);
        task = new ToDo(description);
        return task;
    }

    /**
     * Loads event data into an event task.
     *
     * @param command event task data string
     * @return event task
     * @throws CorruptedFileException if invalid event data format in loaded text file
     */
    private Task loadEvent(String command) throws CorruptedFileException {
        Task task;
        String[] taskInfo;
        String description;
        String dateString;
        String startTimeString;
        String endTimeString;
        LocalDate date;
        LocalTime startTime;
        LocalTime endTime;

        taskInfo = command.substring(7).split(" \\(at: ");
        description = taskInfo[0];
        taskInfo = taskInfo[1].split("[ -]");
        dateString = taskInfo[0];
        startTimeString = taskInfo[1];
        endTimeString = taskInfo[2];
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
        return task;
    }

    /**
     * Loads deadline data into a deadline task.
     *
     * @param command deadline task data string
     * @return deadline task
     * @throws CorruptedFileException if invalid deadline data format in loaded text file
     */
    private Task loadDeadline(String command) throws CorruptedFileException {
        Task task;
        String[] taskInfo;
        String description;
        String dateTimeString;
        LocalDateTime dateTime;

        taskInfo = command.substring(7).split(" \\(by: ");
        description = taskInfo[0];
        dateTimeString = taskInfo[1].substring(0, taskInfo[1].indexOf(")"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy h:mma");

        try {
            dateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            throw new CorruptedFileException();
        }

        task = new Deadline(description, dateTime);
        return task;
    }

    /**
     * Creates/Replaces text file with tasks' data in user's current directory.
     *
     * @param taskList tasks with data to be loaded into text file
     * @throws IOException if given file path is a directory instead of a text file
     * @throws EmptyListException if TaskList is empty
     */
    public void saveFile(TaskList taskList) throws IOException, EmptyListException {
        String path = System.getProperty("user.dir") + filePath;
        FileWriter myWriter = new FileWriter(path);
        myWriter.write(taskList.toString());
        myWriter.close();
    }
}
