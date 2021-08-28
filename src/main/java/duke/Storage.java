package duke;

import duke.items.Event;
import duke.items.Deadline;
import duke.items.Item;
import duke.items.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.ListIterator;
import java.util.Scanner;

/**
 * Handles loading and saving to-do lists to and from the system.
 */
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

    /**
     * Saves a to-do list to system storage.
     * 
     * @param itemList List to save.
     * @throws DukeException If an <code>IOException</code> occurs.
     */
    public void saveState(ItemList itemList) throws DukeException {
        StringBuilder s = new StringBuilder();

        try {
            ListIterator<Item> iterator = itemList.listIterator();
            if (!iterator.hasNext()) {
                s.append("");
            } else {
                while (iterator.hasNext()) {
                    Item currItem = iterator.next();
                    if (currItem instanceof ToDo) {
                        s.append("T###");
                    } else if (currItem instanceof Event) {
                        s.append("E###");
                    } else if (currItem instanceof Deadline) {
                        s.append("D###");
                    } else {
                        throw new DukeException("storage error");
                    }
                    
                    s.append(currItem.getDone() + "###");
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

    /**
     * Loads a saved to-do list from file.
     * 
     * @return <code>ItemList</code> object containing the saved data.
     */
    public ItemList loadState() {
        ItemList itemList = new ItemList();
        try {
            Scanner fileReader = new Scanner(this.file);
            while (fileReader.hasNextLine()) {
                String currLine = fileReader.nextLine();
                String[] currArgs = currLine.split("###");
                Item toAdd = null;

                switch (currArgs[0]) {
                case "T":
                    toAdd = new ToDo(currArgs[2]);
                    break;
                case "D":
                    toAdd = new Deadline(currArgs[2], currArgs[3]);
                    break;
                case "E":
                    toAdd = new Event(currArgs[2], currArgs[3]);
                    break;
                }

                if (currArgs[1].equals("X")) {
                    toAdd.markAsDone();
                }

                itemList.add(toAdd);
            }
            fileReader.close();
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
