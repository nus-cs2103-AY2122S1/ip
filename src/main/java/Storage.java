import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String deliminator = "::";
    private String status;
    private String filePath;
    private final File file;
    private final List<Task> tasks = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    public List<Task> load() throws FileNotFoundException, FileFormatException {
        parseFile(file);
        return tasks;
    }

    private void parseFile(File file) throws FileNotFoundException, FileFormatException {
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            Task task = parseLine(sc.nextLine());
            tasks.add(task);
        }
    }

    private Task parseLine(String string) throws FileFormatException {
        Task task;
        String[] strings = string.split(deliminator);
        try {
            String type = strings[0];
            boolean isDone = strings[1].equals("1");
            String taskDescription = strings[2];
            String time;
            switch (type) {
            case "T":
                task = new Todo(taskDescription);
                break;
            case "D":
                time = strings[3];
                task = new Deadline(taskDescription + " /by " + time);
                break;
            case "E":
                time = strings[3];
                task = new Event(taskDescription + " /at " + time);
                break;
            default:
                throw new FileFormatException();
            }

            if (isDone) {
                task.setDone();
            }

            return task;
        } catch (IndexOutOfBoundsException | EmptyDescriptionException | WrongFormatException e) {
            throw new FileFormatException();
        }
    }

    public void write(List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        StringBuilder text = new StringBuilder();
        for (Task task : tasks) {
            String taskString = String.join(deliminator, task.saveStrings()) + "\n";
            text.append(taskString);
        }
        fw.write(text.toString());
        fw.close();
    }
}
