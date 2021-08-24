package duke;

import duke.task.Task;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Storage {
    private Path filePath;
    private ArrayList<String> lines;

    public Storage(String fileLocation) throws IOException {
        Path filePath = Path.of(fileLocation);
        Files.createDirectories(filePath.getParent());
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        this.filePath = filePath;
        this.lines = readLines();
    }

    private ArrayList<String> readLines() throws IOException {
        Stream<String> linesStream = Files.lines(filePath);
        return linesStream.collect(Collectors.toCollection(ArrayList::new));
    }

    private void saveLinesToStorage() throws IOException {
        // store updated lines to the file path
        Files.write(filePath, lines);
    }

    public ArrayList<String> getLines() {
        return lines;
    }

    public void addLine(Task task) throws IOException {
        lines.add(task.toStorageString());
        saveLinesToStorage();
    }

    public void removeLine(int lineNumber) throws IOException {
        lines.remove(lineNumber - 1);
        saveLinesToStorage();
    }

    public void editLine(int lineNumber, Task task) throws IOException {
        lines.set(lineNumber - 1, task.toStorageString());
        saveLinesToStorage();
    }

}
