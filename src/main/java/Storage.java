import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private File tasksFile;

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
                boolean isCompleted = components[1] == "1" ? true : false;
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

    public void saveTasks(List<Task> tasks) throws IOException {
        FileWriter writer = new FileWriter(this.tasksFile);

        for (Task task : tasks) {
            this.writeTask(writer, task);
        }

        writer.close();
    }

    private void writeTask(FileWriter writer, Task task) throws IOException {
        if (task instanceof Todo) {
            writer.write("T | ");
            writer.write(task.isCompleted ? "1" : "0");
            writer.write(" | ");
            writer.write(escapeString(task.description));
            writer.write(System.lineSeparator());
        } else if (task instanceof Event) {
            writer.write("E | ");
            writer.write(task.isCompleted ? "1" : "0");
            writer.write(" | ");
            writer.write(escapeString(task.description));
            writer.write(" | ");
            writer.write(DateTime.stringify(((Event) task).time));
            writer.write(System.lineSeparator());
        } else if (task instanceof Deadline) {
            writer.write("D | ");
            writer.write(task.isCompleted ? "1" : "0");
            writer.write(" | ");
            writer.write(escapeString(task.description));
            writer.write(" | ");
            writer.write(DateTime.stringify(((Deadline) task).time));
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
