package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path path;

    public Storage() {
        String DB_DIR = ".";
        String DB_NAME = "db.txt";
        this.path = Paths.get(DB_DIR, DB_NAME);
    }

    public Storage(Path path) {
        this.path = path;
    }

    public void save(List<Task> taskList) throws IOException {
        boolean fileExists = Files.exists(path);

        if (!fileExists) {
            Files.createFile(path);
        }
        BufferedWriter file = Files.newBufferedWriter(path);

        for (Task t : taskList) {
            file.write(t.toDatabaseString() + "\n");
        }

        file.close();
    }

    public List<Task> load() throws IOException, DateTimeException {
        boolean fileExists = Files.exists(path);
        List<Task> taskList = new ArrayList<>();

        if (!fileExists) {
            return new ArrayList<>();
        }

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

        return taskList;
    }
}
