import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class TextFile {
    PrintWriter out;

    public TextFile() throws FileNotFoundException {
        out = new PrintWriter("./data/task_list.txt");
    }

    public void updateTextFile(List<Task> taskList) {

    }
}
