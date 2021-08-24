import java.io.File;
import java.io.FileWriter;
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
                    items.add(new Todo(task[2], task[1].equals("1")));
                    break;

                case "E":
                    items.add(new Event(task[2], task[1].equals("1"), LocalDateTime.parse(task[3], outputFormatter)));
                    break;

                case "D":
                    items.add(new Deadline(task[2], task[1].equals("1"), LocalDateTime.parse(task[3], outputFormatter)));
                    break;

                default:
                    break;
                }
            }
        } else {
            f.getParentFile().mkdir();
            f.createNewFile();
        }

        return items;
    }

    public void save(List<Task> tasks) throws IOException {
        // Solution adapted from:
        // https://stackoverflow.com/questions/9658297/java-how-to-create-a-file-in-a-directory-using-relative-path
        File dukeFile = new File(filePath);

        if (!dukeFile.exists()) {
            dukeFile.getParentFile().mkdir();
            dukeFile.createNewFile();
        }

        FileWriter fw = new FileWriter(dukeFile.getAbsoluteFile());

        for (Task t : tasks) {
            fw.write(t.toSaveString() + System.lineSeparator());
        }

        fw.close();
    }


}
