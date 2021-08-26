import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;


public class Storage {

    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    // Loads tasks from the given file path. Called when Duke starts.
    public List<Task> loadTasksFromFile() throws FileNotFoundException, DukeException {
        File saveFile = this.filePath.toFile();
        Scanner scanner = new Scanner(saveFile);
        List<Task> tasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String taskLine = scanner.nextLine();
            String[] taskData = taskLine.split("\\|");
            switch (taskData[0]) {
            case ("T"):
                tasks.add(new Todo(taskData[2], Boolean.parseBoolean(taskData[1])));
                break;
            case ("D"):
                tasks.add(new Deadline(taskData[2], Boolean.parseBoolean(taskData[1]),
                        Parser.parseDateFromInput(taskData[3]), Parser.parseTimeFromInput(taskData[4])));
                break;
            case ("E"):
                tasks.add(new Event(taskData[2], Boolean.parseBoolean(taskData[1]),
                        Parser.parseDateFromInput(taskData[3]), Parser.parseTimeFromInput(taskData[4]),
                        Parser.parseTimeFromInput(taskData[5])));
                break;
            default:
                throw new DukeException("Save file contains invalid task data (Invalid task type)");
            }
        }
        return tasks;
    }

    // Saves tasks to the given file path. Called when list is modified.
    public void saveTasksToFile(TaskList tasks) throws IOException {
        Files.createDirectories(this.filePath.getParent()); // Create directories if they do not exist
        FileWriter fw = new FileWriter(this.filePath.toAbsolutePath().toString());
        StringBuilder saveData = new StringBuilder();
        tasks.forEach((task) -> saveData.append(task.toSaveData()).append(System.lineSeparator()));
        fw.write(saveData.toString());
        fw.close();
    }
}
