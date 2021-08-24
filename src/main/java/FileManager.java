import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    private static String DIRECTORY_PATH = "data";

    private static final String FILE_PATH = DIRECTORY_PATH + "/gnosis.csv";
    private static final File file = new File(FILE_PATH);
    private static final String  DELIMITER = ",";

    public static boolean isGnosisDataSetup() {
        return Files.isDirectory(Paths.get(DIRECTORY_PATH)) && file.exists();
    }

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
                    return new Event(taskName,isTaskDone, schedule);
                } else if (taskType == TaskType.DEADLINE) {
                    String deadline = tokens[3];
                    return new Deadline(taskName,isTaskDone, deadline);
                }
                return new Task(taskName,taskType,"",isTaskDone);
            }).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public static void writeFile(ArrayList<Task> tasks) {
        //TODO: to remove
//        ArrayList<Task> tasks = new ArrayList<>();
//        tasks.add(new Todo("Study for CS2103"));
//        tasks.add(new Event("cs2103 lecture", "2-4pm"));
//        tasks.get(1).setDone(true);
//        tasks.add(new Deadline("cs2103 iP due", "2359 Thursday"));

        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(FILE_PATH));
            writer.write("Task Type,is task completed?,Task name,DateTime");
            writer.newLine();

            for (Task record: tasks) {
                int taskDone = record.isTaskDone() == 'X' ? 1 : 0;

                String oneLine = record.getTaskType() + DELIMITER +
                        taskDone + DELIMITER +
                        record.getTaskName() + DELIMITER +
                        record.getDatetime();
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
    public static boolean CreateDataFolder() throws IOException {
        // create folder and file only if file doesn't exist
        if (!file.exists() && file.getParentFile().mkdir()) {
            System.out.println("File and data folder created");
            return file.createNewFile();
        }
        return true;
    }
    //TODO: to remove
//
    public static void main(String[] args) {
        System.out.println(isGnosisDataSetup());
    }

}
