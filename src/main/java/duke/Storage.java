package duke;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.ArrayList;

import java.util.List;

/**
 * Class that deals with loading and saving of file.
 */
public class Storage {
    private Path folderPath;
    private Path filePath;

    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
        this.folderPath = this.filePath.getParent();
    }

    /**
     * Gets the file path of the file if it already exists and create the file if not.
     *
     * @return Path of the file.
     * @throw IOException.
     */
    public Path getFilePath() {

        try {
            if (!Files.exists(folderPath)) {
                folderPath = Files.createDirectory(folderPath);
                filePath = Files.createFile(filePath);
                return filePath;
            } else {
                if (!Files.exists(filePath)) {
                    filePath = Files.createFile(filePath);
                }
                return filePath;
            }
        } catch (IOException e) {
            return filePath;
        }
    }

    /**
     * Loads the file and produce it as list.
     *
     * @return List of task.
     * @throw IOException.
     */
    public List<Task> load() {
        String currContent;
        String[] parts;
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            List<String> fileContents = Files.readAllLines(this.filePath);
            for (int i = 0; i < fileContents.size(); i++) {
                currContent = fileContents.get(i);
                parts = currContent.split("\\|");
                if (parts[0].equals("T")) {
                    Todo todo = new Todo(parts[2]);
                    if (parts[1].equals("1")) {
                        todo.markAsDone();
                    }
                    taskList.add(todo);
                } else if (parts[0].equals("D")) {
                    Deadline deadline = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        deadline.markAsDone();
                    }
                    taskList.add(deadline);
                } else if (parts[0].equals("E")) {
                    Event event = new Event(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        event.markAsDone();
                    }
                    taskList.add(event);
                }
            }
            return taskList;
        } catch (IOException e) {
            return taskList;
        }
    }

    /**
     * Loads the file and produce it as list.
     *
     * @param tasks List of tasks.
     * @param path Path of the file.
     * @throw IOException.
     */
    public void write(List<Task> tasks, Path path) {
        String stringToInsert;
        Task t;
        try {
            Files.write(path, "".getBytes());
            for (int i = 0; i < tasks.size(); i++) {
                t = tasks.get(i);
                if (t.getTaskType() == "Todo") {
                    if (t.getIsDone()) {
                        stringToInsert = "T|1|" + t.getDescription() + "\n";
                    } else {
                        stringToInsert = "T|0|" + t.getDescription() + "\n";
                    }
                } else if (t.getTaskType() == "Deadline") {
                    if (t.getIsDone()) {
                        stringToInsert = "D|1|" + t.getDescription() + "|" + t.getBy() + "\n";
                    } else {
                        stringToInsert = "D|0|" + t.getDescription() + "|" + t.getBy() + "\n";
                    }
                } else {
                    if (t.getIsDone()) {
                        stringToInsert = "E|1|" + t.getDescription() + "|" + t.getBy() + "\n";
                    } else {
                        stringToInsert = "E|0|" + t.getDescription() + "|" + t.getBy() + "\n";
                    }
                }
                Files.write(path, stringToInsert.getBytes(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
              //There won't be any instance where no file is found because it will just create a new file
        }
    }
}
