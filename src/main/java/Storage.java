import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * deals with loading tasks from save file and saving tasks in the file.
 */
public class Storage {

    // File name for saved tasks.
    private static final String SAVE_FILENAME = "dukeSave.txt";

    // | is a special character that has to be escaped.
    private static final String SAVE_SEPARATOR = " ~ ";

    private static String toSaveDateFormat(LocalDate localDate) throws DukeException {
        try {
            return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeException dateTimeException) {
            throw new DukeException("Stored date is corrupt.");
        }
    }

    private static String toSaveFormat(Task task) throws DukeException {
        // Initialize StringBuilder.
        StringBuilder stringBuilder = new StringBuilder();

        // Get task type, done status of task.
        String taskType = task.getTaskType();
        int done = task.isDone ? 1 : 0;

        // Build corresponding save string from task.
        stringBuilder.append(taskType).append(SAVE_SEPARATOR);
        stringBuilder.append(done).append(SAVE_SEPARATOR);
        stringBuilder.append(task.description);

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

        return stringBuilder.toString();
    }

    private static Task parseSaveFormat(String save) throws DukeException {
        // Split save string by the save separator.
        String[] saveSplit = save.split(SAVE_SEPARATOR);

        try {
            // Get the task type, done status, description of task from saveSplit.
            String taskType = saveSplit[0];
            // Save would be corrupt if done status cannot be parsed to int.
            int isDone = Integer.parseInt(saveSplit[1]);
            String description = saveSplit[2];

            // Create corresponding Task object.
            // Save would be corrupt if Deadline and Event tasks do not have time.
            Task task;
            switch (taskType) {
            case "T":
                task = new Todo(description);
                break;
            case "D":
                LocalDate by = Parser.toLocalDate(saveSplit[3]);
                task = new Deadline(description, by);
                break;
            case "E":
                LocalDate at = Parser.toLocalDate(saveSplit[3]);
                task = new Event(description, at);
                break;
            default:
                throw new DukeException("Save files corrupted. Failed to read tasks from save file.");
            }

            if (isDone == 1) {
                task.markAsDone();
            }

            return task;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new DukeException("Save files corrupted. Failed to read tasks from save file.");
        }
    }

    public static TaskList readTasksFromData() throws DukeException {
        // Initialize an ArrayList for Task objects.
        ArrayList<Task> tasks = new ArrayList<>();

        // Get absolute path to save file.
        String cwd = System.getProperty("user.dir");
        Path absolutePathToSaveFile = Paths.get(cwd, "data", SAVE_FILENAME);

        // Check if save file exists.
        boolean isSaveFileExist = Files.exists(absolutePathToSaveFile);

        try {
            // If save file does not exist, create save file;
            if (!isSaveFileExist) {
                saveTasksToData(tasks);
            }

            // Read from save file.
            List<String> rawTasks = Files.readAllLines(absolutePathToSaveFile);

            // Parse each line into a Task object and save to tasks.
            for (int i = 0; i < rawTasks.size(); i++) {
                Task task = parseSaveFormat(rawTasks.get(i));
                tasks.add(task);
            }
        } catch (IOException ioException) {
            // Failure to read from save file.
            throw new DukeException("Failed to read tasks from save file.");
        }

        return new TaskList(tasks);
    }

    public static void saveTasksToData(ArrayList<Task> tasks) throws DukeException {

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
            Path absolutePathToSaveFile = Paths.get(absolutePathToDataDir.toString(), SAVE_FILENAME);

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
            throw new DukeException("Failed to save tasks");
        }
    }
}
