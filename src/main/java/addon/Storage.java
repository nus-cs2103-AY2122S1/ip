package addon;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles storing and loading the save file.
 */
public class Storage {

    /**
     * Saves items in list in a local file
     *
     * @param list List to be written over.
     */
    public static void saveToFile(ArrayList<Task> list) {
        File oldFile = new File("./save.txt");
        boolean ignore = oldFile.delete();
        try {
            FileWriter fw = new FileWriter("./save.txt");
            for (Task s : list) {
                if (s instanceof Task.Todo) {
                    fw.write("T" + "\r\n");
                    fw.write(s.description + "\r\n");
                } else if (s instanceof Task.Event) {
                    Task.Event e = (Task.Event) s;
                    fw.write("E" + "\r\n");
                    fw.write(s.description + "\r\n");
                    fw.write(e.date + "\r\n");
                } else if (s instanceof Task.Deadline) {
                    Task.Deadline e = (Task.Deadline) s;
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
                list.add(new Task.Todo(name));
            } else {
                String base = s.nextLine();
                String[] basesplit = base.split("T", 2);
                String[] datesplit = basesplit[0].split("-", 3);
                LocalDate sendThis = LocalDate.of(Integer.parseInt(datesplit[0]),
                        Integer.parseInt(datesplit[1]), Integer.parseInt(datesplit[2]));
                if (type.equals("D")) {
                    list.add(new Task.Deadline(name, sendThis));
                } else if (type.equals("E")) {
                    list.add(new Task.Event(name, sendThis));
                }
            }
            if (s.nextLine().equals("1")) {
                (list.get(list.size() - 1)).markDone();
            }
        }
        return list;
    }
}
