import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class TextFile {

    public static void writeToTextFile(List<Task> taskList) throws FileNotFoundException {
        PrintWriter out = new PrintWriter("./data/task_list.txt");
        for (Task task : taskList) {
            if (task instanceof Todo) {
                out.printf("T | %s | %s%n", task.getStatusIcon() == "X" ? 1 : 0, task.getDescription());
            } else if (task instanceof Deadline) {
                out.printf("D | %s | %s | %s%n", task.getStatusIcon() == "X" ? 1 : 0, task.getDescription(),
                        ((Deadline) task).getDeadline());
            } else {
                out.printf("E | %s | %s | %s%n", task.getStatusIcon() == "X" ? 1 : 0, task.getDescription(),
                        ((Event) task).getTime());
            }
        }
        out.close();
    }
}
