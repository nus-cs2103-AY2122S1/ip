package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Storage class handles the interaction with the DukeMan's memory.
 * @author Dominic Siew Zhen Yu
 */

public class Storage {

    private File memory;

    /**
     * The initialization of the storage class involves inputting the
     * file path of the memory.txt file.
     * @param filePath file path of the memory.txt file
     */
    public Storage(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.getParentFile().mkdir();
                file.createNewFile();
            }
            this.memory = file;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public File load() {
        return this.memory;
    }

    /**
     * adds the String event input into the memory.txt file.
     * @param event
     */
    public void addTaskToMemory(String event) {
        try {
            FileWriter fw = null;
            fw = new FileWriter(this.memory, true);
            fw.write(event + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
