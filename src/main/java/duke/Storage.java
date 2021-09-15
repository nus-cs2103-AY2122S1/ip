package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import duke.exception.CannotReadFileException;
import duke.exception.CannotSaveFileException;
import duke.exception.CorruptSaveFileException;
import duke.exception.DukeException;
import duke.exception.InvalidDateTimeFormatException;
import duke.exception.InvalidLocalDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Period;
import duke.task.Task;
import duke.task.Todo;

/**
 * Deals with loading tasks from save file and saving tasks in the file.
 */
public class Storage implements Storable {

    /** Tasks saved are separated by SAVE_SEPARATOR */
    private static final String SAVE_SEPARATOR = " ~ ";

    /** Start date and End date in a time period are separated by PERIOD_SEPARATOR */
    private static final String PERIOD_SEPARATOR = " -> ";

    /** Filename of file to be saved to */
    private final String saveFileName;

    /**
     * Constructor for Storage.
     * Creates a Storage with a specified filename as the target file for save and read operations.
     *
     * @param fileName filename of file to be saved to and read from.
     */
    public Storage(String fileName) {
        this.saveFileName = fileName;
    }

    /**
     * Converts LocalDate into "dd/MM/yyyy" string format for saving.
     *
     * @param localDate LocalDate to be converted.
     * @return Formatted String suitable for saving.
     * @throws InvalidLocalDateException If localDate cannot be parsed into save format.
     */
    private static String toSaveDateFormat(LocalDate localDate) throws InvalidLocalDateException {
        try {
            return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeException dateTimeException) {
            throw new InvalidLocalDateException();
        }
    }

    /**
     * Converts start and end dates of Period into "dd/MM/yyyy" string format separated by PERIOD_SEPARATOR
     * for saving.
     *
     * @param startDate StartDate of Period.
     * @param endDate EndDate of Period.
     * @return Formatted String suitable for saving.
     * @throws InvalidLocalDateException If localDate cannot be parsed into save format.
     */
    private static String toSavePeriodFormat(LocalDate startDate, LocalDate endDate) throws InvalidLocalDateException {
        return toSaveDateFormat(startDate) + PERIOD_SEPARATOR + toSaveDateFormat(endDate);
    }

    /**
     * Converts a Task into a formatted string suitable for saving.
     *
     * @param task Task to be converted.
     * @return Formatted String suitable for saving.
     * @throws InvalidLocalDateException If date cannot be parsed to save format.
     */
    private static String toSaveFormat(Task task) throws InvalidLocalDateException {
        // Initialize StringBuilder.
        StringBuilder stringBuilder = new StringBuilder();

        // Get task type, done status of task.
        String taskType = task.getTaskType();
        int done = task.getIsDone() ? 1 : 0;

        // Build corresponding save string from task.
        stringBuilder.append(taskType).append(SAVE_SEPARATOR);
        stringBuilder.append(done).append(SAVE_SEPARATOR);
        stringBuilder.append(task.getDescription());

        // Deadline tasks have time, so it is obtained and appended via stringBuilder.
        if (taskType.equals("D")) {
            Deadline deadline = (Deadline) task;
            LocalDate localDate = deadline.getTime();
            String time = toSaveDateFormat(localDate);
            stringBuilder.append(SAVE_SEPARATOR).append(time);
        }

        // Event tasks have time, so it is obtained and appended via stringBuilder.
        if (taskType.equals("E")) {
            Event event = (Event) task;
            LocalDate localDate = event.getTime();
            String time = toSaveDateFormat(localDate);
            stringBuilder.append(SAVE_SEPARATOR).append(time);
        }

        // Period tasks have time period, so it is obtained and appended via stringBuilder.
        if (taskType.equals("P")) {
            Period period = (Period) task;
            LocalDate[] startEndDates = period.getPeriod();
            LocalDate startDate = startEndDates[0];
            LocalDate endDate = startEndDates[1];
            String time = toSavePeriodFormat(startDate, endDate);
            stringBuilder.append(SAVE_SEPARATOR).append(time);
        }

        return stringBuilder.toString();
    }

    /**
     * Converts string read from specified file into corresponding tasks based on the string identifier.
     *
     * @param save The read string.
     * @return Task corresponding to the read string.
     * @throws CorruptSaveFileException if string does not have sufficient parts for the task it represents.
     * @throws DukeException if underlying methods fail.
     */
    private static Task parseSaveFormat(String save) throws DukeException {
        // Split save string by the save separator.
        String[] splitSaveString = save.split(SAVE_SEPARATOR);

        try {
            // Get the task type, done status, description of task from saveSplit.
            String taskType = splitSaveString[0];
            // Save would be corrupt if done status cannot be parsed to int.
            int isDone = Integer.parseInt(splitSaveString[1]);
            String description = splitSaveString[2];

            // Create corresponding duke.task.Task object.
            // Save would be corrupt if duke.task.Deadline and duke.task.Event tasks do not have time.
            Task task;
            switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                LocalDate by = Parser.dateToLocalDate(splitSaveString[3]);
                task = new Deadline(description, by);
                break;
            case "E":
                LocalDate at = Parser.dateToLocalDate(splitSaveString[3]);
                task = new Event(description, at);
                break;
            case "P":
                LocalDate[] startEndDates = savedPeriodToLocalDate(splitSaveString[3]);
                LocalDate startDate = startEndDates[0];
                LocalDate endDate = startEndDates[1];
                task = new Period(description, startDate, endDate);
                break;
            default:
                throw new CorruptSaveFileException();
            }

            if (isDone == 1) {
                task.markAsDone();
            }

            return task;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new CorruptSaveFileException();
        }
    }

    /**
     * Converts saved period string into a 2 element LocalDate array consisting of start date and end date.
     *
     * @param savedPeriodString Saved period string.
     * @return LocalDate array containing 2 elements, start and end date of the time period.
     * @throws InvalidDateTimeFormatException If saved date strings cannot be converted to LocalDate.
     * @throws CorruptSaveFileException If saved period String is saved in a wrong format.
     */
    private static LocalDate[] savedPeriodToLocalDate(String savedPeriodString) throws DukeException {
        String[] split = savedPeriodString.split(PERIOD_SEPARATOR);

        // If time period cannot be split into 2 by the PERIOD_SEPARATOR, then save file is corrupted.
        if (split.length != 2) {
            throw new CorruptSaveFileException();
        }

        LocalDate startDate = Parser.dateToLocalDate(split[0]);
        LocalDate endDate = Parser.dateToLocalDate(split[1]);

        return new LocalDate[]{startDate, endDate};
    }

    /**
     * Reads tasks from specified file and returns a TaskList containing the retrieved tasks.
     * Doubles down as an initializer for the save file if the data directory and the save file are missing.
     *
     * @return TaskList containing retrieved tasks.
     * @throws CannotReadFileException If there are errors reading the file.
     * @throws DukeException If underlying methods fail.
     */
    public TaskList readTasksFromData() throws DukeException {
        // Initialize an ArrayList for duke.task.Task objects.
        ArrayList<Task> tasks = new ArrayList<>();

        // Get absolute path to save file.
        String cwd = System.getProperty("user.dir");
        Path absolutePathToSaveFile = Paths.get(cwd, "data", this.saveFileName);

        // Check if save file exists.
        boolean isSaveFileExist = Files.exists(absolutePathToSaveFile);

        try {
            // If save file does not exist, create save file.
            if (!isSaveFileExist) {
                saveTasksToData(new TaskList(tasks));
            }

            // Read from save file using streams.
            List<String> rawTasks = new ArrayList<>();
            Stream<String> lines = Files.lines(absolutePathToSaveFile);
            lines.forEachOrdered(rawTasks::add);

            // Parse each line into a duke.task.Task object and save to tasks.
            for (int i = 0; i < rawTasks.size(); i++) {
                Task task = parseSaveFormat(rawTasks.get(i));
                tasks.add(task);
            }
        } catch (IOException ioException) {
            throw new CannotReadFileException();
        }

        return new TaskList(tasks);
    }

    /**
     * Saves tasks to specified file.
     *
     * @param taskList TaskList containing the tasks to be saved to specified file.
     * @throws CannotSaveFileException If tasks cannot be written to save file.
     * @throws DukeException If underlying methods fail.
     */
    public void saveTasksToData(TaskList taskList) throws DukeException {
        // Extracts ArrayList from duke.TaskList object.
        ArrayList<Task> tasks = taskList.getTasks();

        // Get the absolute path to data subdirectory of project directory.
        String cwd = System.getProperty("user.dir");
        Path absolutePathToDataDir = Paths.get(cwd, "data");

        // Check if data directory exists.
        boolean isDirectoryExist = Files.exists(absolutePathToDataDir);

        try {
            // If data directory does not exist, create one.
            if (!isDirectoryExist) {
                Files.createDirectory(absolutePathToDataDir);
            }

            // Get absolute path to save file.
            Path absolutePathToSaveFile = Paths.get(absolutePathToDataDir.toString(), this.saveFileName);

            // Check if file exists.
            boolean isSaveFileExist = Files.exists(absolutePathToSaveFile);

            // If file does not exist, create it.
            if (!isSaveFileExist) {
                Files.createFile(absolutePathToSaveFile);
            }

            // Stop if there are no tasks to be saved.
            if (tasks.size() == 0) {
                return;
            }

            // Generate string to be saved to save file.
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                String save = toSaveFormat(tasks.get(i));
                stringBuilder.append(save);
                // If last task in tasks, no need to append newline.
                if (i < (tasks.size() - 1)) {
                    stringBuilder.append("\n");
                    ;
                }
            }
            String textToSave = stringBuilder.toString();

            // Write to save file.
            byte[] textToSaveToBytes = textToSave.getBytes();
            Files.write(absolutePathToSaveFile, textToSaveToBytes);
        } catch (IOException ioException) {
            throw new CannotSaveFileException();
        }
    }
}
