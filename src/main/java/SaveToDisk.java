import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class SaveToDisk {
    TaskList ls;
    String fileName = "duke.txt";
    PrintWriter writer;

    public SaveToDisk(TaskList ls) {
        this.ls = ls;
    }

    public void rewriteFile(TaskList ls) {
        try {
            FileWriter fw = new FileWriter(fileName, false);
            writer = new PrintWriter(fw);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ls.getSize(); i++) {
            Task task = ls.getTask(i);
            String type = task.getType();
            String desc = task.getDesc();
            String addOns = task.addOns();
            if (type == "todo") {
                writer.println("T" + (task.isDone ? " | 1 | " : " | 0 | ") + desc);
            } else if (type == "deadline") {
                writer.println("D" + (ls.getTask(i).isDone ? " | 1 | " : " | 0 | ")
                        + desc + " | " + addOns);
            } else if (type == "event") {
                writer.println("E" + (task.isDone ? " | 1 | " : " | 0 | ")
                        + desc + " | " + addOns);
            }
        }
        writer.close();
    }
}
