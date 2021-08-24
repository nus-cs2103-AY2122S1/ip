import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Storage storage;

    public TaskList(Storage storage) throws FileParseException {
        retrieveTaskListFromLines(storage.getLines());
        this.storage = storage;
    }

    private void retrieveTaskListFromLines(ArrayList<String> lines) throws FileParseException {
        try {
            for (String line : lines) {
                parseLineToTask(line);
            }
        } catch (DukeException | ArrayIndexOutOfBoundsException e) {
            throw new FileParseException("The contents of the file in storage are formatted wrongly.");
        }
    }

    private void parseLineToTask(String line) throws DukeException {
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

    public Task addTask(Task task) throws IOException {
        storage.addLine(task);
        tasks.add(task);
        return task;
    }

    public Task markTask(int taskNumber) throws IOException {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        storage.editLine(taskNumber, task);
        return task;
    }

    public Task deleteTask(int taskNumber) throws IOException {
        storage.removeLine(taskNumber);
        return tasks.remove(taskNumber - 1);
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    @Override
    public String toString() {
        StringBuilder tasksString = new StringBuilder();

        for (int i = 0; i < getSize(); i++) {
            int taskNumber = i + 1;
            tasksString
                    .append(taskNumber)
                    .append(".")
                    .append(getTask(i))
                    .append(i == (getSize() - 1) ? "" : "\n");
        }

        return tasksString.toString();
    }

}
