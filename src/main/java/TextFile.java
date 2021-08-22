import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class TextFile {

    public static void updateTextFile(List<Task> taskList) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("./data/task_list.txt");
        for (Task task : taskList) {
            out.println(task);
        }
        out.close();
    }
}
