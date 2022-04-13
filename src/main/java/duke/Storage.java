package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Scanner;

import duke.items.Deadline;
import duke.items.Event;
import duke.items.Item;
import duke.items.ToDo;

/**
 * Handles loading and saving to-do lists to and from the system.
 */
public class Storage {
    static final String SEPARATOR = "###";
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
     * @param items List to save.
     * @throws DukeException If an <code>IOException</code> occurs.
     */
    public void saveState(ItemList items) throws DukeException {
        StringBuilder s = new StringBuilder();

        try {
            ListIterator<Item> iterator = items.listIterator();
            if (!iterator.hasNext()) {
                s.append("");
            } else {
                while (iterator.hasNext()) {
                    Item currItem = iterator.next();
                    if (currItem instanceof ToDo) {
                        s.append("T" + SEPARATOR);
                    } else if (currItem instanceof Event) {
                        s.append("E" + SEPARATOR);
                    } else if (currItem instanceof Deadline) {
                        s.append("D" + SEPARATOR);
                    } else {
                        throw new DukeException("storage error");
                    }
                    
                    s.append(currItem.getDone() + SEPARATOR);
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
        ItemList items = new ItemList();
        try {
            Scanner fileReader = new Scanner(this.file);
            while (fileReader.hasNextLine()) {
                String currLine = fileReader.nextLine();
                String[] currArgs = currLine.split(SEPARATOR);
                Item toAdd = null;

                String name = null;
                String time = null;
                String tag = null;

                for (String s : currArgs) {
                    String[] flagArgs = s.split("&&&");
                    switch (flagArgs[0]) {
                    case "name":
                        name = flagArgs[1];
                        break;
                    case "time":
                        time = flagArgs[1];
                        break;
                    case "tag":
                        tag = flagArgs[1];
                        break;
                    default:
                        break;
                    }
                }

                switch (currArgs[0]) {
                case "T":
                    toAdd = new ToDo(name);
                    break;
                case "D":
                    toAdd = new Deadline(name, time);
                    break;
                case "E":
                    toAdd = new Event(name, time);
                    break;
                default:
                    throw new DukeException("Storage error");
                }

                if (currArgs[1].equals("X")) {
                    toAdd.markAsDone();
                }

                if (!Objects.isNull(tag)) {
                    toAdd.addTag(tag);
                }

                items.add(toAdd);
            }
            fileReader.close();
        } catch (FileNotFoundException | DukeException e) {
            e.printStackTrace();
        }
        return items;
    }
}
