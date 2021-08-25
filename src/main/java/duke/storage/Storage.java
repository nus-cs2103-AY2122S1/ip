package duke.storage;

import duke.commands.Deadline;
import duke.commands.Task;
import duke.commands.Todo;
import duke.commands.Event;
import duke.data.TaskList;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Storage {
    private String path;
    private TaskList tasks;

    public Storage(String path, TaskList tasks) {
        this.path = path;
        this.tasks = tasks;
    }

    public void load() {
        try {
            File fileDir = new File(path).getParentFile();
            File file = new File(path);

            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            if (!file.createNewFile()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {
                    tasks.add(parseTask(line));
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with loading");
        }
    }

    public void save() {
        try {
            FileWriter writer = new FileWriter(path);
            ArrayList<Task> lst = tasks.getList();
            for (Task task: lst) {
                writer.write(task.printFormat());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong with saving");
        }
    }

    private Task parseTask(String task) {
        String[] tokens = task.split(" \\| ");
        Task t = null;
        // T | 0 | description | addInfo
        boolean isDone = tokens[1].equals("1");
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        LocalDateTime timestamp = null;
        switch (tokens[0]) {
            case "T":
                t = new Todo(tokens[2], isDone);
                break;
            case "D":
                try {
                    timestamp = LocalDateTime.parse(tokens[3], format);
                    t = new Deadline(tokens[2], isDone, timestamp);
                } catch (DateTimeParseException e) {
                    System.out.println("Error parsing task from saved file");
                }
                break;
            case "E":
                try {
                    timestamp = LocalDateTime.parse(tokens[3], format);
                    t = new Event(tokens[2], isDone, timestamp);
                } catch (DateTimeParseException e) {
                    System.out.println("Error parsing task from saved file");
                }
                break;
        }
        return t;
    }
}
