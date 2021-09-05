package gnosis.task;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gnosis.model.Deadline;
import gnosis.model.Event;
import gnosis.model.Task;
import gnosis.model.TaskType;
import gnosis.model.Todo;

public class TaskStorageManager {

    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = DIRECTORY_PATH + "/tasks.csv";
    private static final File FILE = new File(FILE_PATH);
    private static final String DELIMITER = ",";

    /**
     * Loads up Gnosis file to display.
     *
     * @return tasks Returns a list of tasks from file.
     */
    public static List<Task> loadGnosisTasks() {
        //check if user has folder:
        // if no folder -> means no data found -> create one from scratch
        // if folder found -> load to arraylist tasks
        if (TaskStorageManager.isDataFileAvail()) {
            return TaskStorageManager.getTasksFromFile();
        } else {
            TaskStorageManager.createDataFolder();
            return new ArrayList<>();
        }
    }

    /**
     * Writes Tasks into file from input.
     *
     * @param tasks List of tasks to write to file.
     */
    public static void writeTasksToFile(List<Task> tasks) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH));
            writer.write("Task Type,is task completed?,Task name,DateTime");
            writer.newLine();

            for (Task record: tasks) {
                int taskDone = record.isTaskDone() == 'X' ? 1 : 0;

                String oneLine = record.getTaskType() + DELIMITER
                        + taskDone + DELIMITER
                        + record.getTaskName() + DELIMITER
                        + record.getDatetime().toString();

                writer.write(oneLine);
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private static List<Task> getTasksFromFile() {

        List<Task> tasks = new ArrayList<>();
        try {
            tasks = Files.newBufferedReader(Paths.get(FILE_PATH))
                    .lines()
                    .skip(1)
                    .map(TaskStorageManager::parseTask)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    private static Task parseTask(String s) {
        String[] tokens = s.split(DELIMITER);

        TaskType taskType = TaskType.getTaskType(tokens[0].charAt(0));
        boolean isTaskDone = tokens[1].equalsIgnoreCase("1");
        String taskName = tokens[2];

        if (taskType == TaskType.TODO) {
            return new Todo(taskName, isTaskDone);
        } else if (taskType == TaskType.EVENT) {
            String schedule = tokens[3];
            LocalDateTime dt = LocalDateTime.parse(schedule);

            return new Event(taskName, isTaskDone, dt);
        } else if (taskType == TaskType.DEADLINE) {
            String deadline = tokens[3];
            LocalDateTime dt = LocalDateTime.parse(deadline);

            return new Deadline(taskName, isTaskDone, dt);
        }
        return new Task(taskName, taskType, null, isTaskDone);
    }

    // returns value whether data folder and file was created successfully
    private static boolean createDataFolder() {
        // create folder
        return !FILE.exists() && FILE.getParentFile().mkdir();
    }

    public static boolean isDataFileAvail() {
        return Files.isDirectory(Paths.get(DIRECTORY_PATH)) && FILE.exists();
    }


    /**
     * copy saved tasked file to indicated path to export to.
     *
     * @param pathToExport path to export file to
     */
    public static void copyTaskToPath(File pathToExport) {

        try {

            Files.copy(FILE.toPath(), pathToExport.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
