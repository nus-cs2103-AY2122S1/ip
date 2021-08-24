import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    protected Path filePath;
    private Ui ui;

    public Storage(Path filePath) {
        this.filePath = filePath;
        ui = new Ui();
    }

    public ArrayList<Task> load() {
        ArrayList<Task> taskList = new ArrayList<>();
        boolean directoryExists = Files.exists(filePath);

        try {
            if (directoryExists) {
                // for everything in the data file, add to the list
                List<String> data = Files.readAllLines(filePath);
                for (String str : data) {
                    Task task = convertDataToTask(str);
                    taskList.add(task);
                }
            }
        }
        catch (IOException e) {
            ui.showError(e.getMessage());
        }

        return taskList;
    }

    public void save(Task task) {
        boolean directoryExists = Files.exists(filePath);
        // access the data file and make changes to it
        try {
            if (directoryExists) {
                String newData = task.toData() + '\n';
                Files.write(filePath, newData.getBytes(), StandardOpenOption.APPEND);
            } else {
                throw new DukeException("Data file doesn't exist");
            }
        }
        catch (IOException | DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    public void update(int taskNumber, Task task, String action) {
        boolean directoryExists = Files.exists(filePath);
        try {
            if (directoryExists) {
                List<String> lines = Files.readAllLines(filePath);
                if (action.equals("m")) {
                    lines.set(taskNumber - 1, task.toData());
                } else {
                    lines.remove(taskNumber - 1);
                }
                Files.write(filePath, lines);
            } else {
                throw new DukeException("Data file doesn't exist");
            }
        }
        catch (IOException | DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    private Task convertDataToTask(String data) {
        try {
            String[] dataValues = data.split(" \\| ");
            String type = dataValues[0];
            boolean isCompleted = dataValues[1].equals("1");
            String description = dataValues[2];
            String date;
            if (dataValues.length > 3) {
                date = dataValues[3];
                switch (type) {
                    case "D":
                        return new Deadline(description, isCompleted, date);
                    case "E":
                        return new Event(description, isCompleted, date);
                    default :
                        throw new DukeException("something went wrong");
                }
            }
            return new Todo(description, isCompleted);
        }
        catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return null;
    }
}