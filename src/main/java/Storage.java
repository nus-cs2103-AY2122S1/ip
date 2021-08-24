import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Storage {
    private static final String FileName = "duke.txt";

    public void Save(ArrayList<Task> arr) {
        try {
            PrintWriter printWriter = new PrintWriter(FileName);
            StringBuilder sb = new StringBuilder();
            for (Task task : arr) {
                sb.append(task.logo);
                sb.append(",");
                sb.append(task.isDone ? "1" : "0");
                sb.append(",");
                sb.append(task.description);
                if (task instanceof Event) {
                    sb.append(",");
                    sb.append(((Event) task).at);
                }
                if (task instanceof Deadline) {
                    sb.append(",");
                    sb.append(((Deadline) task).by);
                }
                sb.append(System.lineSeparator());
            }
            printWriter.write(sb.toString());
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public static ArrayList<Task> Load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FileName);
        try {
            Scanner sc = new Scanner(file);
            System.out.println("Past tasks found. Use command \"list\" to list previous tasks.");
            sc.useDelimiter(",");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                List<String> splitLine = Arrays.stream(line.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList());

                String taskType = splitLine.get(0);
                boolean isDone = splitLine.get(1).equals("1");
                String description = splitLine.get(2);

                Task toAdd;
                switch (taskType) {
                    case "T":
                        toAdd = new Todo(description);
                        break;
                    case "D":
                        LocalDateTime by = LocalDateTime.parse(splitLine.get(3));
                        toAdd = new Deadline(description, by);
                        break;
                    case "E":
                        LocalDateTime at = LocalDateTime.parse(splitLine.get(3));
                        toAdd = new Event(description, at);
                        break;
                    default:
                        toAdd = new Task(description);
                        break;
                }

                if (isDone) {
                    toAdd.markDone();
                }
                tasks.add(toAdd);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No past tasks found. Starting with a new list.");
        }
        return tasks;
    }
}
