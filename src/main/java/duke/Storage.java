package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Class to encapsulate a Storage
 */
public class Storage {
    protected Path filePath;
    protected Ui ui;

    /**
     * Class constructor for Storage Class specifying the filepath
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
        ui = new Ui();
    }

    /**
     * Returns an ArrayList of tasks loaded from data source
     *
     * @return           an arraylist of tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        boolean directoryExists = Files.exists(filePath);

        try {
            if (directoryExists) {
                List<String> data = Files.readAllLines(filePath);
                for (String str : data) {
                    Task task = convertDataToTask(str);
                    tasks.add(task);
                }
            } else {
                throw new DukeException("File not found");
            }
        } catch (DukeException | IOException e) {
            ui.showError(e.getMessage());
        }

        return tasks;
    }

    /**
     * Saves the given task to the data source
     *
     * @param task the given task to save
     */
    public void save(Task task) {
        boolean directoryExists = Files.exists(filePath);
        if (!directoryExists) {
            try {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            } catch (IOException e) {
                ui.showError(e.getMessage());
            }
        }
        try {
            String newData = task.toData() + '\n';
            Files.write(filePath, newData.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Update the given task in the data source
     *
     * @param taskNumber the task number of the task
     * @param task       the task given
     * @param action     actions to perform to the task, eg: d-delete, m-modify
     */
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
        } catch (IOException | DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Convert data retrieved from data source to Task
     *
     * @param data the data retrieved from data source
     * @return     the converted Task
     */
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
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
        return null;
    }
}
