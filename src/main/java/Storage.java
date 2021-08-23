import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Storage {
    private Path filePath;

    public Storage(String fileLocation) throws IOException {
        Path filePath = Path.of(fileLocation);
        Files.createDirectories(filePath.getParent());
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        this.filePath = filePath;
    }

    public ArrayList<String> readLines() throws IOException {
        Stream<String> linesStream = Files.lines(filePath);
        return linesStream.collect(Collectors.toCollection(ArrayList::new));
    }

    public void writeLines(ArrayList<String> lines) throws IOException {
        // store updated lines to the file path
        Files.write(filePath, lines);
    }

}
