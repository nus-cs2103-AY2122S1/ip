package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private final String filename;
    private final TaskList tasks = new TaskList(new ArrayList<>());

    Storage(String filename) {
        this.filename = filename;
    }

    void write(String line) throws IOException {
        FileWriter writer = new FileWriter(this.filename, true);
        writer.write(line + "\n");
        writer.close();
    }

    /**
     * Loads all the tasks from the given file name and add it to task list.
     * If the position is unset, NaN is returned.
     *
     * @throws IOException If unable to create new file
     */
    public void loadTasks() throws IOException {
        Scanner scanner;
        File file = new File(filename);
        file.createNewFile();
        scanner = new Scanner(file);
        while (scanner.hasNext()) {
            Task task = parseTask(scanner.nextLine());
            if (task != null) {
                tasks.add(task);
            }
        }
    }

    private Task parseTask(String input) {
        String[] splitString = input.split(" \\| ");
        String type = splitString[0];
        String status = splitString[1];
        String desc = splitString[2];
        Task newTask;
        switch (type) {
        case "T":
            newTask = new Todo(desc);
            break;
        case "D":
            String deadline = splitString[3];
            newTask = new Deadline(desc, LocalDateTime.parse(deadline));
            break;
        case "E":
            String duration = splitString[3];
            newTask = new Event(desc, LocalDateTime.parse(duration));
            break;
        default:
            return null;
        }
        if (status.equals("1")) {
            newTask.markAsDone();
        }
        return newTask;
    }

    /**
     * Returns tasks.
     *
     * @return Tasks loaded from file
     */
    public TaskList getTasks() {
        return tasks;
    }

    /**
     * Rewrites the entire file based with tasks.
     *
     * @throws IOException If unable to create write to file
     */
    public void writeEntireFile() throws IOException {
        FileWriter writer = new FileWriter(this.filename);
        for (Task task : tasks) {
            writer.write(task.save() + "\n");
        }
        writer.close();
    }
}
