import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Storage {
    public String FileName;

    public Storage() {
        this.FileName = "duke.txt";
    }

    public void Save(ArrayList<Task> arr) {
        try {
            PrintWriter printWriter = new PrintWriter(this.FileName);
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
                sb.append('\n');
            }
            printWriter.write(sb.toString());
            printWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public void Load() {

    }
}
