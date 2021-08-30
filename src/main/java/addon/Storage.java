package addon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import addon.Tasklist.Deadline;
import addon.Tasklist.Event;
import addon.Tasklist.Task;
import addon.Tasklist.Todo;

/**
 * Handles storing and loading the save file.
 */
public class Storage {

    /**
     * Returns a new list of the current tasks.
     *
     * @param list List to be written over.
     */
    public static void rewriteFile(ArrayList<Task> list) {
        File oldFile = new File("./save.txt");
        oldFile.delete();
        try {
            FileWriter fw = new FileWriter("./save.txt");
            for (Task s : list) {
                if (s instanceof Todo) {
                    fw.write("T" + "\r\n");
                    fw.write(s.description + "\r\n");
                } else if (s instanceof Event) {
                    Event e = (Event) s;
                    fw.write("E" + "\r\n");
                    fw.write(s.description + "\r\n");
                    fw.write(e.date + "\r\n");
                } else if (s instanceof Deadline) {
                    Deadline e = (Deadline) s;
                    fw.write("D" + "\r\n");
                    fw.write(s.description + "\r\n");
                    fw.write(e.date + "\r\n");
                }
                fw.write((s.isDone ? "1" : "0") + "\r\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Appends the provided task to the file.
     *
     * @param s Task to be appended.
     */
    public static void appendFile(Task s) {
        try {
            FileWriter fw = new FileWriter("./save.txt", true);
            if (s instanceof Todo) {
                fw.write("T\r\n");
                fw.write(s.description + "\r\n");
            } else if (s instanceof Event) {
                Event e = (Event) s;
                fw.write("E\r\n");
                fw.write(s.description + "\r\n");
                fw.write(e.date.toString() + "\r\n");
            } else if (s instanceof Deadline) {
                Deadline e = (Deadline) s;
                fw.write("D\r\n");
                fw.write(s.description + "\r\n");
                fw.write(e.date.toString() + "\r\n");
            }

            fw.write((s.isDone ? "1" : "0") + "\r\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Loads the provided save file into the current Arraylist.
     *
     * @param saveFile Savefile to be loaded.
     */
    public static ArrayList<Task> loadFile(File saveFile) throws FileNotFoundException {

        Scanner s = new Scanner(saveFile);
        ArrayList<Task> list = new ArrayList<>();

        while (s.hasNext()) {
            String type = s.nextLine();
            String name = s.nextLine();
            if (type.equals("T")) {
                list.add(new Todo(name));
            } else {
                String base = s.nextLine();
                String[] basesplit = base.split("T", 2);
                String[] datesplit = basesplit[0].split("-", 3);
                String[] timesplit = basesplit[1].split(":", 2);
                LocalDateTime sendThis = LocalDateTime.of(Integer.parseInt(datesplit[0]),
                        Integer.parseInt(datesplit[1]), Integer.parseInt(datesplit[2]), Integer.parseInt(timesplit[0]),
                        Integer.parseInt(timesplit[1]));
                if (type.equals("D")) {
                    list.add(new Deadline(name, sendThis));
                } else if (type.equals("E")) {
                    list.add(new Event(name, sendThis));
                }
            }
            if (s.nextLine().equals("1")) {
                (list.get(list.size() - 1)).markDone();
            }
        }
        return list;
    }
}
