import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private  File f;
    private Scanner s;
    private ArrayList<String> taskListString;

    public Storage (String filePath) {
        this.f = new File(filePath);
        this.taskListString = new ArrayList<>();
    }

    public ArrayList<String[]> load() throws FileNotFoundException{
        ArrayList<String[]> taskList = new ArrayList<>();
        s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            taskListString.add(line);
            taskList.add(line.split("[|]"));
        }
        return taskList;
    }

    public void save() throws IOException {
        FileWriter fw = new FileWriter(f);
        for (int i = 0; i < this.taskListString.size() - 1; i++) {
            fw.write(this.taskListString.get(i) + "\n");
        }
        fw.write(this.taskListString.get(taskListString.size() - 1));
        fw.close();
    }

    public void saveTask(String type, String status, String description) {
        taskListString.add(type + "|" + status + "|" + description);
    }

    public void saveTask(String type, String status, String description, LocalDateTime timeframe) {
        taskListString.add(type + "|" + status + "|" + description + "|"
                + timeframe.format(DateTimeFormatter.ISO_DATE_TIME));
    }

    public void saveTaskDone(int index) {
        String data = taskListString.get(index - 1);
        taskListString.set(index - 1, data.substring(0, 2) + 'X' + data.substring(3));
    }

    public void removeTask(int index) {
        taskListString.remove(index);
    }
}
