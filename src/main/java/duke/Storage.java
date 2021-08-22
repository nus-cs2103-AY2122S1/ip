package duke;

import duke.task.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Storage {
    private final String DB_NAME = "db.txt";
    private final String DB_DIR = ".";
    private Path path;

    public Storage() {
        this.path = Paths.get(DB_DIR, DB_NAME);
    }

    public void save(List<Task> taskList) throws IOException{
        boolean fileExists = Files.exists(path);

        if (!fileExists) {
           path = Files.createFile(path);
        }
        BufferedWriter file = Files.newBufferedWriter(path);

        for (int i=0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            file.write(t.toDatabaseString() + "\n");
        }

        file.close();
    }

    public Stream<String> load() throws IOException {
        Path path = Paths.get(DB_DIR, DB_NAME);
        boolean fileExists = Files.exists(path);
        if(!fileExists) {
            return Stream.empty();
        }

        BufferedReader file = Files.newBufferedReader(path);
        return file.lines();
    }
}
