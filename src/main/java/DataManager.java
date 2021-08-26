import java.io.*;
import java.util.ArrayList;

public class DataManager {

    private final File dukeData;

    public DataManager(File dukeData) {
        this.dukeData = dukeData;
    }

    public ArrayList<Task> txtToArrayList() throws IOException {
        ArrayList<Task> list = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(dukeData));
        String line = bufferedReader.readLine();
        while (line != null) {
            if (line.startsWith("T")) {
                String status = line.substring(1, 2);
                String task = line.substring(5);
                Task newTask = new Todo(task, status.equals("1"));
                list.add(newTask);
            } else if (line.startsWith("D")) {
                String status = line.substring(1, 2);
                String temp = line.substring(5);
                String task = line.substring(5, temp.indexOf("|") + 5);
                String due = temp.substring(temp.indexOf("|") + 2);
                Task newTask = new Deadline(task, due, status.equals("1"));
                list.add(newTask);
            } else {
                String status = line.substring(1, 2);
                String temp = line.substring(5);
                String task = line.substring(5, line.substring(5).indexOf("|") + 5);
                String due = temp.substring(temp.indexOf("|") + 2);
                Task newTask = new Event(task, due, status.equals("1"));
                list.add(newTask);
            }
            line = bufferedReader.readLine();
        }
        return list;
    }

    public void updateFile(ArrayList<Task> list) throws IOException {
        FileWriter fileWriter = new FileWriter(dukeData);
        for (Task t : list ) {
            fileWriter.write(t.displayTask() + "\n");
        }
        fileWriter.close();
    }
}
