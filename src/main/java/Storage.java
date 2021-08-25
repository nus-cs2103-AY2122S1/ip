import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.stream.Stream;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class Storage {
    private String home;
    private String filePath;
    private Path dataPath;

    public Storage(String filePath) {
        this.filePath = filePath;
        home = System.getProperty("user.home");
        dataPath = Paths.get(home, filePath);
    }

    public Stream<String> load() throws DukeException {
        Stream<String> lines = Stream.empty();
        if (!Files.exists(dataPath)) {
            try {
                Files.createFile(dataPath);
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        } else {
            try {
                lines = Files.lines(dataPath);
            } catch (IOException e) {
                throw new DukeException(e.getMessage());
            }
        }
        return lines;
    }

    public void rewriteData(String dataStr) throws DukeException {
        try {
            Files.writeString(dataPath, dataStr, StandardCharsets.ISO_8859_1);
        }
        catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
