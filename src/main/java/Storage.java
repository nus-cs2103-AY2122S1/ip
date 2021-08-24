import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
                tasks.add((line));
            }


        }
    }

    private Task parseTask(String task) {
        String todo = "[T]";
        String deadline = "[D]";
        String event = "[E]";


    }
}
