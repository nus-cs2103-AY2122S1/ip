package back;

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
            String saveString = Parser.listToString(list);
            fw.write(saveString);
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

            String fulldate;
            LocalDate parsedDate;
            switch (type) {
            case "T":
                returnList.add(new Todo(name));
                break;
            case "D":
                fulldate = s.nextLine();
                parsedDate = Parser.parseDateString(fulldate);
                returnList.add(new Deadline(name, parsedDate));
                break;
            case "E": fulldate = s.nextLine();
                parsedDate = Parser.parseDateString(fulldate);
                returnList.add(new Event(name, parsedDate));
                break;
            //not handling other cases since type has to be T/E/D from line 48
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
