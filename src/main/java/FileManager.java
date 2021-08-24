import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    private static final String DIRECTORY_PATH = "data";

    private static final String FILE_PATH = DIRECTORY_PATH + "/gnosis.csv";
    private static final File file = new File(FILE_PATH);
    private static final String  DELIMITER = ",";

    public static List<Task> loadTask() {
        List<Task> tasks = new ArrayList<>();
        try {
            tasks = Files.newBufferedReader(Paths.get(FILE_PATH))
                    .lines()
                    .skip(1)
                    .map(s -> {
                String[] tokens = s.split(DELIMITER);

                TaskType taskType = TaskType.getTaskType(tokens[0].charAt(0));
                boolean isTaskDone = tokens[1].equalsIgnoreCase("1");
                String taskName = tokens[2];

                if (taskType == TaskType.TODO) {
                    return new Todo(taskName,isTaskDone);
                } else if (taskType == TaskType.EVENT) {
                    String schedule = tokens[3];
                    LocalDateTime dt = LocalDateTime.parse(schedule);
                    return new Event(taskName,isTaskDone, dt);
                } else if (taskType == TaskType.DEADLINE) {
                    String deadline = tokens[3];
                    LocalDateTime dt = LocalDateTime.parse(deadline);
                    return new Deadline(taskName,isTaskDone, dt);
                }
                return new Task(taskName,taskType,null,isTaskDone);
            }).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static void writeFile(List<Task> tasks) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH));
            writer.write("Task Type,is task completed?,Task name,DateTime");
            writer.newLine();

            for (Task record: tasks) {
                int taskDone = record.isTaskDone() == 'X' ? 1 : 0;

                String oneLine = record.getTaskType() + DELIMITER +
                        taskDone + DELIMITER +
                        record.getTaskName() + DELIMITER +
                        record.getDatetime().toString();
                writer.write(oneLine);
                writer.newLine();
            }

            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // returns value whether data folder and file was created successfully
    public static boolean createDataFolder() {
        // create folder
        return !file.exists() && file.getParentFile().mkdir();
    }

    public static boolean isDataFileAvail() {
        System.out.println(Files.isDirectory(Paths.get(DIRECTORY_PATH)) && file.exists());
        return Files.isDirectory(Paths.get(DIRECTORY_PATH)) && file.exists();
    }
}
