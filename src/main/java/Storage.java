import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;



public class Storage {
    private final String fileName;
    private final TaskList tasks = new TaskList(new ArrayList<Task>());

    Storage(String fileName) {
        this.fileName = fileName;
    }

    void write(String line) throws IOException {
        FileWriter writer = new FileWriter(this.fileName, true);
        writer.write(line + "\n");
        writer.close();
    }

    public void loadTasks() throws IOException, DukeException{
        Scanner scanner;
        File file = new File(fileName);
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

    TaskList getTasks() {
        return tasks;
    }

    public void writeEntireFile() throws IOException {
        FileWriter writer = new FileWriter(this.fileName);
        for (Task task : tasks) {
            writer.write(task.save() + "\n");
        }
        writer.close();
    }
}
