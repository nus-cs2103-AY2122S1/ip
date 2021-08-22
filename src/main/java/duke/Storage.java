package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.StringBuffer;
import java.util.LinkedList;
import java.util.Scanner;

public class Storage {
    private File file;
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            this.file = new File(filePath);
            Scanner fileReader = new Scanner(this.file);
            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found");
        }
        
    }
    public void saveState(LinkedList<Item> itemList) {
        StringBuffer buffer = new StringBuffer();
    }

    public LinkedList<Item> loadState(String filePath) {
        LinkedList<Item> state = new LinkedList<>();
        
        return state;
    }
}
