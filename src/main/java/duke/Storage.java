package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) throws DukeException {
        this.file = new File(filePath);
        (new File("duke/data")).mkdirs();

        try {
            if (this.file.createNewFile()) {
                System.out.println("new file created");
            }
        } catch (IOException e) {
            throw new DukeException("IOException");
        }
    }

    public void saveState(LinkedList<Item> itemList) throws DukeException {
        StringBuilder s = new StringBuilder();

        try {
            ListIterator<Item> iterator = itemList.listIterator();
            if (!iterator.hasNext()) {
                return;
            } else {
                while (iterator.hasNext()) {
                    Item currItem = iterator.next();
                    if (currItem instanceof ToDo) {
                        s.append("T|");
                    } else if (currItem instanceof Event) {
                        s.append("E|");
                    } else if (currItem instanceof Deadline) {
                        s.append("D|");
                    } else {
                        throw new DukeException("storage error");
                    }
                    
                    s.append(currItem.getDone() + "|");
                    s.append(currItem.getPickle() + "\n");
                }
            }
            FileWriter out = new FileWriter(this.file);
            out.write(s.toString());
            out.close();
        } catch (IOException e) {
            throw new DukeException("Storage IOException");
        }
    }

    public void loadState() {

    }
}
