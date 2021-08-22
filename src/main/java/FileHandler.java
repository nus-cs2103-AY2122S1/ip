import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;



public class FileHandler {
    private final String fileName;
    private final ArrayList<Task> tasks = new ArrayList<>();

    FileHandler(String fileName) throws IOException {
        this.fileName = fileName;
    }

    void write(String line) throws IOException {
        FileWriter writer = new FileWriter(this.fileName, true);
        writer.write(line + "\n");
        writer.close();
    }

    public void loadTasks() throws DukeException{
        Scanner scanner;
        try {
            File file = new File(fileName);
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found!");
        }

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
            newTask = new Deadline(desc, deadline);
            break;
        case "E":
            String duration = splitString[3];
            newTask = new Event(desc, duration);
            break;
        default:
            return null;
        }
        if (status.equals("1")) {
            newTask.markAsDone();
        }
        return newTask;
    }

    ArrayList<Task> getTasks() {
        return tasks;
    }
}
