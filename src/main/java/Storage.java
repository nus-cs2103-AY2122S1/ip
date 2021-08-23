import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private Tasks tasks;

    public static final Path PATH = Path.of(System.getProperty("user.dir")
            + File.separator + "tasks.txt");

    public Storage() {
        boolean directoryExists = java.nio.file.Files.exists(PATH);
        if (directoryExists) {
            retrieveTasksFromStorage();
            System.out.println("\tNice to see you again.");
            System.out.println(tasks.taskSummary());
            if (!tasks.isEmpty()) {
                System.out.println(tasks);
            }
        } else {
            resetTasks();
        }
    }

    private void retrieveTasksFromStorage() {
        try {
            List<Task> taskList = Files.lines(PATH).map((line) -> {
                String[] fragments = line.split(" \\| ");
                String type = fragments[0];
                boolean done = Boolean.parseBoolean(fragments[1]);
                String description = fragments[2];
                String time = fragments.length == 4 ? fragments[3] : null;
                Task foundTask;
                switch (type) {
                    case "T":
                        foundTask = new ToDo(description, done);
                        break;
                    case "D":
                        foundTask = new Deadline(description, time, done);
                        break;
                    case "E":
                        foundTask = new Event(description, time, done);
                        break;
                    default:
                        throw new DukeException("\t☹ OOPS!!! I can't find your tasks.\n");
                }
                return foundTask;
            }).collect(Collectors.toList());
            tasks = new Tasks(taskList);
        } catch (IOException e) {
            throw new DukeException("\t☹ OOPS!!! I can't find your tasks.\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("\t☹ OOPS!!! Your tasks might be corrupted.");
            resetTasks();
        }
    }

    public void updateStorage() {
        try {
            Files.write(Storage.PATH, tasks.formatStorage(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new DukeException("\t☹ OOPS!!! I can't store any changes you make. \n");
        }
    }

    public void resetTasks() {
        try {
            System.out.println("\tClearing tasks...\n");
            Files.newBufferedWriter(PATH);
            tasks.clearTasks();
            System.out.println("\tYou can now start anew...\n");
        } catch (IOException e) {
            throw new DukeException("\t☹ OOPS!!! Continuing without saving.\n");
        }
    }

    public Tasks getTasks() {
        return tasks;
    }

}
