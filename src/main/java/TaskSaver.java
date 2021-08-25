import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskSaver {
    private final String filePath;
    private final File file;

    public TaskSaver(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            System.err.println("IOException: Unable to create file.");
        }
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        try {
            Scanner sc = new Scanner(file);
            ArrayList<Task> list = new ArrayList<>();
            while (sc.hasNext()) {
                Task task = taskFormatter(sc.nextLine());
                list.add(task);
            }
            return list;
        } catch (IOException e) {
            System.err.println("Error loading file.");
            return new ArrayList<Task>();
        }
    }

    public void updateFile(String tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(tasks);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unexpected error.");
        }
    }

    public Task taskFormatter(String task) throws DukeException {
        String type = task.substring(1, 2);
        String doneStatus = task.substring(4, 5);
        Task t;
        switch (type) {
            case "D":
                String d = task.substring(7, task.length() - 1);
                String[] descD = d.split("by: ");
                String firstD = descD[0].substring(0, descD[0].length() - 2);
                t = new Deadline(firstD, descD[1]);
                break;
            case "E":
                String e = task.substring(7, task.length() - 1);
                String[] descE = e.split("at: ");
                String firstE = descE[0].substring(0, descE[0].length() - 2);
                t = new Event(firstE, descE[1]);
                break;
            case "T":
                String descT = task.substring(7);
                t = new ToDo(descT);
                break;
            default:
                throw new DukeException("Unknown task");
        }
        if (doneStatus.equals("X")) {
            t.markAsDone();
        }
        return t;
    }

}
