import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeDataHandler {

    public TaskList retrieveTaskList() {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File("./data/duke.txt");
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("OOPS!!! An error occurred when initializing Duke.");
                System.exit(1);
            }
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Task t = parse(scanner.nextLine());
                list.add(t);
            }
        } catch (Exception e) {
            System.err.println("OOPS!!! An error occurred when initializing Duke.");
            System.exit(1);
        }
        return new TaskList(list);
    }

    public void storeTaskList(TaskList list) {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            fw.write(list.printList());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            System.err.println("OOPS!!! An error occurred when writing to the data file.");
        }
    }

    private Task parse(String task) {
        task = task.trim();
        int m = task.indexOf('.');
        task = task.substring(m + 1);
        boolean isDone = task.charAt(4) == 'X';
        if (task.startsWith("[T]")) {
            task = task.substring(7);
            Task t = new Todo(task);
            if (isDone) {
                t.markAsDone();
            }
            return t;
        } else if (task.startsWith("[D]")) {
            task = task.substring(7);
            m = task.lastIndexOf(" (by: ");
            String description = task.substring(0, m);
            String by = task.substring(m + 6, task.length() - 1);
            Task t = new Deadline(description, by);
            if (isDone) {
                t.markAsDone();
            }
            return t;
        } else if (task.startsWith("[E]")) {
            task = task.substring(7);
            m = task.lastIndexOf(" (at: ");
            String description = task.substring(0, m);
            String at = task.substring(m + 6, task.length() - 1);
            Task t = new Event(description, at);
            if (isDone) {
                t.markAsDone();
            }
            return t;
        } else {
            throw new DukeException("invalid data file");
        }
    }

}
