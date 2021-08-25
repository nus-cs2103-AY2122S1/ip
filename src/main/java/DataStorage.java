import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class DataStorage {

    final private String path;
    private ArrayList<Task> list;

    public DataStorage(String path, ArrayList<Task> list) {
        this.path = path;
        this.list = list;
    }

    // load data: check if file exists, check if directory exists
    public ArrayList<Task> loadData() throws IOException{
        File file = new File(path);
        list = new ArrayList<>();
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }

        if (!file.exists()) {
            file.createNewFile();
        } else {
//            FileWriter fw = new FileWriter(file);
//            fw.flush();
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String s = sc.nextLine();
                System.out.println(s);
                String[] arr = s.split("/");
                boolean done = (arr[1].equals("1"));
                String name = arr[2];
                if (s.contains("T")) {
                    ToDo todo = new ToDo(name, done);
                    list.add(todo);
                }
                if (s.contains("D")) {
                    String by = arr[3];
                    Deadline deadline = new Deadline(name, by, done);
                    list.add(deadline);
                }
                if (s.contains("E")) {
                    String at = arr[3];
                    Event event = new Event(name, at, done);
                    list.add(event);
                }
            }
            sc.close();
        }
        return list;
    }

    public void updateData(ArrayList<Task> list) throws IOException {
        this.list = list;
        File file = new File(path);
        FileWriter fw = new FileWriter(file);
        for (Task task : list) {
            fw.write(task.toStringInStorage() + "\n");
        }
        fw.close();
    }
}
