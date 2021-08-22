import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class DukeParser {
    private final String dataPath;

    public DukeParser(String dataPath) {
        this.dataPath = dataPath;
    }

    private Task parseTaskFromLine(String line) throws InvalidDukeCommandException {
        String[] lineArgs = line.split(" \\| ");
        String taskType = lineArgs[0];
        boolean isDone = lineArgs[1].equals("1");
        String taskDescription = lineArgs[2];
        String date = lineArgs.length > 3 ? lineArgs[3] : "";
        Task task;
        switch (taskType) {
        case "T":
            task = new Todo(taskDescription);
            break;
        case "D":
            task = new Deadline(taskDescription, date);
            break;
        case "E":
            task = new Event(taskDescription, date);
            break;
        default:
            throw new InvalidDukeCommandException("Invalid data read from file while loading tasks");
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Returns a list of task based on the data given by specified file in the constructor.
     *
     * @return List of Tasks from file
     * @throws FileNotFoundException If source is not found
     * @throws InvalidDukeCommandException If data from the file does not follow specifications
     */
    public List<Task> loadTasksFromData() throws FileNotFoundException, InvalidDukeCommandException {
        File dataFile = new File(dataPath);
        Scanner scanner = new Scanner(dataFile);
        List<Task> taskList = new ArrayList<>();
        while (scanner.hasNext()) {
            try {
                Task task = parseTaskFromLine(scanner.nextLine());
                taskList.add(task);
            } catch (InvalidDukeCommandException e) {
                throw e;
            }
        }
        return taskList;
    }

    public void writeTasksToFile(List<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(dataPath);
        StringBuilder outputBuilder = new StringBuilder();
        taskList.forEach(task -> {
            outputBuilder.append(task.toDukeStoreFormat());
            outputBuilder.append("\n");
        });
        fileWriter.write(outputBuilder.toString());
        fileWriter.close();
        return;
    }
}
