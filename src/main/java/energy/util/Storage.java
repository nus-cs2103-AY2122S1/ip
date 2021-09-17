package energy.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import energy.result.AliasHandler;
import energy.result.TaskList;
import energy.task.Deadline;
import energy.task.Event;
import energy.task.Task;
import energy.task.Todo;

/**
 * A class that handles all file related functionality, such as loading from
 * and saving to a save file.
 */
public class Storage {

    private final Path saveFilePath;
    private final Path configFilePath;

    /**
     * Creates an instance of the Storage class.
     *
     * @param saveFilePath File path for the save file.
     */
    public Storage(Path saveFilePath, Path configFilePath) {
        this.saveFilePath = saveFilePath;
        this.configFilePath = configFilePath;
    }

    /**
     * Loads tasks from the specified file path.
     *
     * @return The TaskList object containing a list of tasks loaded from the save file.
     * @throws FileNotFoundException If the file does not exist.
     * @throws EnergyException         If the file contains invalid task data.
     */
    public TaskList loadTasksFromFile() throws FileNotFoundException, EnergyException {
        File saveFile = saveFilePath.toFile();
        Scanner scanner = new Scanner(saveFile);
        List<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String taskLine = scanner.nextLine();
            String[] taskData = taskLine.split("\\|");

            String taskType = taskData[0];
            String taskName = taskData[2];
            boolean isDone = Boolean.parseBoolean(taskData[1]);

            switch (taskType) {
            case ("T"):
                tasks.add(new Todo(taskName, isDone));
                break;
            case ("D"):
                LocalDate date = Parser.parseDateFromInput(taskData[3]);
                LocalTime time = Parser.parseTimeFromInput(taskData[4]);
                tasks.add(new Deadline(taskName, isDone, date, time));
                break;
            case ("E"):
                date = Parser.parseDateFromInput(taskData[3]);
                LocalTime startTime = Parser.parseTimeFromInput(taskData[4]);
                LocalTime endTime = Parser.parseTimeFromInput(taskData[5]);
                tasks.add(new Event(taskName, isDone, date, startTime, endTime));
                break;
            default:
                throw new EnergyException("Save file contains invalid task data (Invalid task type).");
            }
        }
        return new TaskList(tasks);
    }

    /**
     * Saves tasks to the file path given at instantiation.
     *
     * @param taskList The list of tasks to be saved.
     * @throws IOException If the saving process fails.
     */
    public void saveTasksToFile(TaskList taskList) throws IOException {
        // Create directories if they do not exist, so that the file can be created without error
        Files.createDirectories(saveFilePath.getParent());
        FileWriter fw = new FileWriter(saveFilePath.toAbsolutePath().toString());
        // Convert all tasks in the task list into save data format
        StringBuilder saveData = new StringBuilder();
        taskList.forEach((task) -> saveData.append(task.toSaveData())
                .append(System.lineSeparator())); // Each task will be on its own line

        fw.write(saveData.toString());
        fw.close();
    }

    /**
     * Loads aliases from the specified file path.
     *
     * @return The AliasHandler object containing a list aliases loaded from the config file.
     * @throws FileNotFoundException If the file does not exist.
     * @throws EnergyException         If the file contains invalid alias data.
     */
    public AliasHandler loadAliasesFromFile() throws FileNotFoundException, EnergyException {
        File configFile = configFilePath.toFile();
        Scanner scanner = new Scanner(configFile);
        HashMap<String, String> aliasHashMap = new HashMap<>();
        while (scanner.hasNextLine()) {
            String aliasLine = scanner.nextLine();
            String[] aliasData = aliasLine.split("\\|");
            String alias = aliasData[0];
            String command = aliasData[1].toLowerCase();
            aliasHashMap.put(alias, command);
        }
        return new AliasHandler(aliasHashMap);
    }

    /**
     * Saves aliases to the file path given at instantiation.
     *
     * @param aliasHandler An object containing the aliases to be saved.
     * @throws IOException If the saving process fails.
     */
    public void saveAliasesToFile(AliasHandler aliasHandler) throws IOException {
        // Create directories if they do not exist, so that the file can be created without error
        Files.createDirectories(configFilePath.getParent());
        FileWriter fw = new FileWriter(configFilePath.toAbsolutePath().toString());
        // Convert all aliases into save data format
        String aliasData = aliasHandler.toConfigData();
        fw.write(aliasData);
        fw.close();
    }
}
