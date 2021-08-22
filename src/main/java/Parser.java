import java.io.IOException;
import java.util.ArrayList;

public class Parser {
    private ArrayList<String> lines;
    private Storage storage;

    public Parser(Storage storage) throws IOException {
        this.storage = storage;
        this.lines = storage.readLines();
    }

    public ArrayList<Task> retrieveTaskListFromLines() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (String line : lines) {
            parseLineToTask(line, tasks);
        }
        return tasks;
    }

    private void parseLineToTask(String line, ArrayList<Task> tasks) {
        String[] params = line.split(" \\| ");
        String taskType = params[0];
        boolean isTaskDone = params[1].equals("1");
        String description = params[2];

        switch (taskType) {
        case "T":
            tasks.add(new Todo(description, isTaskDone));
            break;
        case "D":
            tasks.add(new Deadline(description, params[3], isTaskDone));
            break;
        case "E":
            tasks.add(new Event(description, params[3], isTaskDone));
            break;
        }
    }

    public void addLine(Task task) throws IOException {
        lines.add(task.toStorageString());
        saveLinesToStorage();
    }

    public void removeLine(Task task) throws IOException {
        lines.remove(task.toStorageString());
        saveLinesToStorage();
    }

    public void editLine(int lineNumber, Task task) throws IOException {
        lines.set(lineNumber - 1, task.toStorageString());
        saveLinesToStorage();
    }

    private void saveLinesToStorage() throws IOException {
        storage.writeLines(lines);
    }

}
