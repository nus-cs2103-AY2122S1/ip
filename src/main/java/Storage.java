import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String path;
    private ArrayList<Task> tasks;

    Storage(String path, ArrayList<Task> tasks) {
        this.path = path;
        this.tasks = tasks;
    }

    public void load() throws IOException {
        File fileDir = new File(path).getParentFile();
        File file = new File(path);

        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        if (file.createNewFile()) {
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {
                tasks.add(parseTask(line));
            }


        }
    }

    public void save() throws IOException {
        FileWriter writer = new FileWriter(path);
        for (Task task : tasks) {
            writer.write(task.printFormat());
            writer.write("\n");
        }
        writer.close();
    }

    private Task parseTask(String task) {
        String[] tokens = task.split(" | ");
        Task t = null;
        // T | 0 | description | addInfo
        boolean isDone = tokens[1] == "1";
        switch (tokens[0]) {
            case "T":
                t = new Todo(tokens[2], isDone);
                break;
            case "D":
                t = new Deadline(tokens[2], isDone, tokens[3]);
                break;
            case "E":
                t = new Event(tokens[2], isDone, tokens[3]);
                break;
        }

        return t;
    }
}
