import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextFile {

    public static List<Task> readFromTextFile() throws FileNotFoundException {
        File file = new File("./data/task_list.txt");
        Scanner reader = new Scanner(file);
        while (reader.hasNextLine()) {
            String task = reader.nextLine();
            String[] splitDescription = task.split(" | ");
            System.out.println(splitDescription[0]);
        }
        return new ArrayList<>();
    }

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
