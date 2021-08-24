import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private final Path filePath;
    private final Path directoryPath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        this.directoryPath = Paths.get(new File(filePath).getParent());
    }

    public List<String> load() throws DukeException {
        List<String> taskList = new ArrayList<>();

        try {
            if (!Files.exists(directoryPath))
                Files.createDirectory(directoryPath);

            if (!Files.exists(filePath))
                Files.createFile(filePath);

            taskList = Files.readAllLines(filePath);
        } catch (IOException | UnsupportedOperationException | SecurityException e) {
            new Ui().showError(e.getMessage());
        }

        if (taskList.isEmpty())
            throw new DukeException("Empty list");

        return taskList;
    }

    public void save(TaskList taskLists) {
        try {
            FileWriter dataFileWriter = new FileWriter(this.filePath.toString());
            for (Task task : taskLists.getTasks()) {
                dataFileWriter.write(task.toDataString() + System.lineSeparator());
            }
            dataFileWriter.close();
        } catch (IOException e) {
            Printer.prettyPrint("File writing failed: " + e);
        }
    }
}
