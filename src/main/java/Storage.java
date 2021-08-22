import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws IOException {

            List<Task> items = new ArrayList<>();
            File f = new File(filePath);
            if (f.exists()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String t = sc.nextLine();
                    String[] task = t.split("\\|");
                    switch (task[0]) {
                    case "T":
//                        items.addTodo(task);
                        items.add(new Todo(task[2], task[1].equals("1")));
                        break;

                    case "E":
//                        items.addEvent(task);
                        items.add(new Event(task[2], task[1].equals("1"), LocalDateTime.parse(task[3], outputFormatter)));
                        break;

                    case "D":
//                        items.addDeadline(task);
                        items.add(new Deadline(task[2], task[1].equals("1"), LocalDateTime.parse(task[3], outputFormatter)));
                        break;

                    default:
                        break;
                    }
                }
            }

            return items;

    }
}
