import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Path;

public class TaskManager {
    static private List<Task> taskList = new ArrayList<>();
    static private final String DB_NAME = "db.txt";

    public static void initialize() {
        load(DB_NAME);
    }

    public static Task addTask(String type, Optional<String> args) throws DukeException, IllegalArgumentException {
        Task ret = null;
        // parse raw task string
        switch (type) {
            case "todo":
                ret = ToDo.of(args);
                break;
            case "deadline":
                ret = Deadline.of(args);
                break;
            case "event":
                ret = Event.of(args);
                break;
            default:
                throw new InvalidDukeCommandException();
        }
        taskList.add(ret);
        save(DB_NAME);

        return ret;
    }

    public static Task deleteTask(int taskId) throws IllegalArgumentException {
        if (taskId < taskList.size() && taskId >= 0) {
            Task ret = taskList.remove(taskId);
            save(DB_NAME);
            return ret;
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! Task Index is invalid!!");
        }
    }

    public static int taskCount() {
        return taskList.size();
    }

    public static Task completeTask(int taskId) throws IllegalArgumentException {
        if (taskId < taskList.size() && taskId >= 0) {
            taskList.set(taskId, taskList.get(taskId).done());
            save(DB_NAME);
            return taskList.get(taskId);
        } else {
            throw new IllegalArgumentException("☹ OOPS!!! Task Index is invalid!!");
        }
    }

    public static String listTasks() {
        return Stream.iterate(0, x -> x < taskList.size(), x -> x+1)
                .map(x -> String.format("%d. %s", x+1, taskList.get(x).toString()))
                .collect(Collectors.joining("\n"));
    }

    private static void load(String dbName) {
        Path path = Paths.get(".", dbName);
        boolean fileExists = Files.exists(path);
        if(!fileExists) {
            return;
        }

        try {
            BufferedReader file = Files.newBufferedReader(path);
            file.lines().forEachOrdered(line -> {
                String[] parts = line.split("[|]");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                switch (type) {
                case "T":
                    taskList.add(ToDo.of(isDone, parts[2]));
                    break;
                case "D":
                    taskList.add(Deadline.of(isDone, parts[2], parts[3]));
                    break;
                case "E":
                    taskList.add(Event.of(isDone, parts[2], parts[3]));
                    break;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void save(String dbName) {
        Path path = Paths.get(".", dbName);
        boolean fileExists = Files.exists(path);
        try {
            if (!fileExists) {
                path = Files.createFile(path);
            }
            BufferedWriter file = Files.newBufferedWriter(path);

            for (int i=0; i < taskList.size(); i++) {
                Task t = taskList.get(i);
                file.write(t.toDatabaseString() + "\n");
            }

            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
