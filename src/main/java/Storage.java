import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// to divide into
// encoder
// decoder

class Storage {
    private static final String FILE_NAME = "./duke.txt";
    private static final Path FILE_PATH = Paths.get(FILE_NAME);

    public static List<Task> load() throws IOException {
        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
        }
        List<String> lines = Files.readAllLines(FILE_PATH);
        List<Task> tasks = new ArrayList<>(lines.size());
        for (String s : lines) {
            String[] tokens = s.split(" \\| ");
            Task t = null;
            boolean isDone = tokens[1].equals("1");
            switch (tokens[0]) {
            case "T":
                t = new ToDo(tokens[2], isDone);
                break;
            case "D":
                LocalDateTime by = LocalDateTime.parse(tokens[3]);
                t = new Deadline(tokens[2], isDone, by);
                break;
            case "E":
                LocalDateTime at = LocalDateTime.parse(tokens[3]);
                LocalDateTime end = tokens[4].equals("null")
                        ? null
                        : LocalDateTime.parse(tokens[4]);
                t = new Event(tokens[2], isDone, at, end);
                break;
            }
            tasks.add(t);
        }
        return tasks;
    }

    public static void save(List<String> data) throws IOException {
        Files.write(FILE_PATH, data, StandardOpenOption.WRITE);
    }
}
