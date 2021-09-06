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
        boolean ignore = oldFile.delete(); //clear any old saves
        try {
            FileWriter fw = new FileWriter("./save.txt");
            for (Task s : list) {
                String newLine = "\r\n";
                if (s instanceof Task.Todo) {
                    fw.write("T" + newLine);
                    fw.write(s.taskName + newLine);
                } else if (s instanceof Task.Event) {
                    Task.Event e = (Task.Event) s;
                    fw.write("E" + newLine);
                    fw.write(s.taskName + newLine);
                    fw.write(e.date + newLine);
                } else if (s instanceof Task.Deadline) {
                    Task.Deadline e = (Task.Deadline) s;
                    fw.write("D" + newLine);
                    fw.write(s.taskName + newLine);
                    fw.write(e.date + newLine);
                }
                fw.write((s.isDone ? "1" : "0") + newLine);
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
        ArrayList<Task> returnList = new ArrayList<>();

        while (s.hasNext()) {
            String type = s.nextLine();
            assert type.matches("[TED]") : "Save file (TYPE) corrupted.";
            String name = s.nextLine();
            assert !name.isEmpty() : "Save file (NAME) corrupted.";
            if (type.equals("T")) {
                returnList.add(new Task.Todo(name));
            } else {
                String fullDate = s.nextLine();
                assert(fullDate.matches("^[0-9]{4}([-])(((0[13578]|(10|12))\\1(0[1-9]|[1-2][0-9]|3[0-1]))|(02\\1(0[1-9]"
                        + "|[1-2][0-9]))|((0[469]|11)\\1(0[1-9]|[1-2][0-9]|30)))$")) : "Save file (DATE) corrupted.";
                String[] fullDateSplit = fullDate.split("-");
                LocalDate parsedDate = LocalDate.of(Integer.parseInt(fullDateSplit[0]),
                        Integer.parseInt(fullDateSplit[1]), Integer.parseInt(fullDateSplit[2]));
                if (type.equals("D")) {
                    returnList.add(new Task.Deadline(name, parsedDate));
                } else if (type.equals("E")) {
                    returnList.add(new Task.Event(name, parsedDate));
                }
            }
            String taskDoneInt = s.nextLine();
            assert taskDoneInt.matches("[01]") : "Save file (DONEVALUE) corrupted.";

            if (taskDoneInt.equals("1")) {
                Task lastItemInList = returnList.get(returnList.size() - 1);
                lastItemInList.markDone();
            }
        }
        return returnList;
    }
}
