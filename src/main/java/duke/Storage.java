package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static final Path DEFAULT_DIR = Paths.get("data");

    private File tasks;

    public Storage(String fileName) {
        try {
            if (!Files.isDirectory(DEFAULT_DIR)) {
                Files.createDirectories(DEFAULT_DIR);
            }
            Path taskPath = Paths.get(DEFAULT_DIR.toString(), fileName);
            if (Files.notExists(taskPath)) {
                this.tasks = Files.createFile(taskPath).toFile();
            } else {
                this.tasks = taskPath.toFile();
            }
        } catch (IOException e) {
            Ui.reportError("☹ OOPS!!! Unable to find or create a file!");
        }
    }

    public static Task lineToTask(String line) {
        String[] data = line.split(" \\| ");
        return Parser.arrayCommandToTask(data);
    }

    public TaskList parseToTaskList() throws DukeException {
        ArrayList<Task> result = new ArrayList<>();
        try {
            List<String> data = Files.readAllLines(tasks.toPath());
            data.forEach(x -> result.add(lineToTask(x)));
        } catch (IOException e) {
            Ui.reportError("☹ OOPS!!! Something wrong happened when reading the file.");
        } catch (DukeException e) {
            Ui.reportError(e);
        }
        return new TaskList(result);
    }

    public void addTask(Task task) {
        try {
            FileWriter fileWriter = new FileWriter(this.tasks, true);
            String line = task.taskToLine();
            if (this.tasks.length() == 0) {
                fileWriter.write(line);
            } else {
                fileWriter.write("\n" + line);
            }
            fileWriter.close();
        } catch (IOException e) {
            Ui.reportError("☹ OOPS!!! Something wrong happened when modifying the file.");
        }
    }

    public void refreshTask(TaskList taskList) {
        try {
            int numOfTask = taskList.size();
            if (numOfTask == 0) {
                return;
            }
            FileWriter fileWriter = new FileWriter(this.tasks);
            for (int i = 0; i < numOfTask - 1; i++) {
                fileWriter.write(taskList.getTask(i).taskToLine() + "\n");
            }
            fileWriter.write(taskList.getTask(numOfTask - 1).taskToLine());
            fileWriter.close();
        } catch (IOException e) {
            Ui.reportError("☹ OOPS!!! Something wrong happened when modifying the file.");
        }
    }
}
