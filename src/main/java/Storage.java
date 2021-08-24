import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    public static void save(ArrayList<Task> tasks) {
        FileWriter w, fw;
        try {
            w = new FileWriter("data/list.txt");
            w.write("");
            w.close();

            fw = new FileWriter("data/list.txt", true);
            for(int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                String taskDesc = t.getType() + (t.isDone() ? " | 1 | " : " | 0 | ") + t.getDetail();
                if (t.getTime() != null) {
                    taskDesc += (" | " + t.getTime());
                }
                fw.write(taskDesc + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file : "+ e.getLocalizedMessage());
        }
    }

    public static ArrayList<Task> load() {
        File f = new File("data/list.txt");
        ArrayList<Task> list = new ArrayList<>();

        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdir();
        } if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Error loading file : " + e.getLocalizedMessage());
            }
        }
        try {
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()) {
                Task newTask;
                String[] t = sc.nextLine().split("\\|", 4);
                if (t[0].contains("T")) {
                    newTask = new ToDo(t[2].trim());
                } else if (t[0].contains("D")) {
                    newTask = new Deadline(t[2].trim(), t[3].trim());
                } else {
                    newTask = new Event(t[2].trim(), t[3].trim());
                }

                if (t[1].contains("1")) {
                    newTask.markDone();
                }
                list.add(newTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found : "+ e.getLocalizedMessage());
        }
        return list;
    }
}
