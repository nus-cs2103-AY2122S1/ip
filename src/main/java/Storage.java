import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String next = s.nextLine();
            String[] split = next.split("\\|", 4);
            Task t;
            if (split[0].equals("T")) {
                t = new Todo(split[2]);
            } else if (split[0].equals("D")) {
                t = new Deadline(split[2], split[3]);
            } else {
                t = new Event(split[2], split[3]);
            }
            if (split[1].equals("X")) {
                t.markAsDone();
            }
            taskArrayList.add(t);
        }
        s.close();
        return taskArrayList;
    }

    public void save(ArrayList<Task> taskArrayList) throws IOException{
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i);
            appendToFile(filePath, getStringFormat(task), i);
        }
    }

    private String getStringFormat(Task t) {
        if (t instanceof Todo) {
            Todo temp = (Todo) t;
            return "T|" + temp.getStatusIcon() + "|" + temp.description + "\n";
        } else if (t instanceof Deadline) {
            Deadline temp = (Deadline) t;
            return "D|" + temp.getStatusIcon() + "|" + temp.description + "|" + temp.by + "\n";
        } else {
            Event temp = (Event) t;
            return "E|" + temp.getStatusIcon() + "|" + temp.description + "|" + temp.at + "\n";
        }
    }

    private void appendToFile(String filePath, String textToAppend, int i) throws IOException {
        FileWriter fw = new FileWriter(filePath, i != 0);
        fw.write(textToAppend);
        fw.close();
    }
}
