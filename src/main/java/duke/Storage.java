package duke;

import duke.task.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private File tasksFile;

    /**
     * Creates a Storage object to store data at the provided filepath.
     * @param filePath The filepath to store data at.
     * @throws An IO Exception if a file cannot be created at the provided filepath.
     */
    public Storage(String filePath) throws IOException {
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdir();
        }
        File tasksFile = new File("data/" + filePath);
        if (!tasksFile.exists()) {
            tasksFile.createNewFile();
        }
        this.tasksFile = tasksFile;
    }

    /**
     * Loads tasks from the storage file and populates a {@link TaskList}.
     * @throws Error if there is a problem with the file format of the stored file.
     */
    public List<Task> loadTasks() throws Exception {
        List<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(this.tasksFile);
        try {
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] components = line.split(" \\| ");
                if (components.length < 3) {
                    throw new Exception("Invalid format");
                }
                boolean isCompleted = components[1].equals("1") ? true : false;
                String description = unescapeString(components[2]);
                switch (components[0]) {
                    case "T": {
                        tasks.add(new Todo(description, isCompleted));
                        break;
                    }
                    case "E": {
                        if (components.length != 4) {
                            throw new Exception("Invalid format");
                        }
                        tasks.add(new Event(description, DateTime.parse(components[3]), isCompleted));
                        break;
                    }
                    case "D": {
                        if (components.length != 4) {
                            throw new Exception("Invalid format");
                        }
                        tasks.add(new Deadline(description, DateTime.parse(components[3]), isCompleted));
                        break;
                    }
                    default: {
                        throw new Exception("Invalid format");
                    }
                }
            }
        } finally {
            s.close();
        }

        return tasks;
    }

    /**
     * Saves tasks from the given tasklist to the storage file.
     * @param taskList The tasklist to save.
     * @throws IO Exception if file cannot be written to.
     */
    public void saveTasks(TaskList taskList) throws IOException {
        FileWriter writer = new FileWriter(this.tasksFile);

        for (Task task : taskList.items()) {
            this.writeTask(writer, task);
        }

        writer.close();
    }

    private void writeTask(FileWriter writer, Task task) throws IOException {
        if (task instanceof Todo) {
            writer.write("T | ");
            writer.write(task.getIsCompleted() ? "1" : "0");
            writer.write(" | ");
            writer.write(escapeString(task.getDescription()));
            writer.write(System.lineSeparator());
        } else if (task instanceof Event) {
            writer.write("E | ");
            writer.write(task.getIsCompleted() ? "1" : "0");
            writer.write(" | ");
            writer.write(escapeString(task.getDescription()));
            writer.write(" | ");
            writer.write(DateTime.stringify(((Event) task).getTime()));
            writer.write(System.lineSeparator());
        } else if (task instanceof Deadline) {
            writer.write("D | ");
            writer.write(task.getIsCompleted() ? "1" : "0");
            writer.write(" | ");
            writer.write(escapeString(task.getDescription()));
            writer.write(" | ");
            writer.write(DateTime.stringify(((Deadline) task).getTime()));
            writer.write(System.lineSeparator());
        }
    }

    private String escapeString(String str) {
        return str.replace("|", "||");
    }

    private String unescapeString(String str) {
        return str.replace("||", "|");
    }
}
