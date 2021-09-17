package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Reads and write memory to local storage.
 */
public class Storage {
    private String filePath = "./data/duke.txt";

    /**
     * Initiates a storage instance.
     */
    public Storage() {

    }

    /**
     * Retrieves file from local storage. If file does not exist create file in local storage.
     *
     * @return a File from local storage.
     * @throws DukeException if error encountered when opening or creating file on local storage.
     */
    public File loadDataFile() throws DukeException {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                File dir = new File("./data");
                if (dir.mkdir() && f.createNewFile()) {
                    return f;
                }
            }
            return f;
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("Unable to load data file");
        }
    }

    /**
     * Writes duke data to local storage.
     *
     * @param data Duke data in string form to be stored on local device.
     * @throws DukeException if error encountered when writing to data file on local storage.
     */
    public void writeToDisk(String data) throws DukeException {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("Unable to write to disk :( \n pls give me another chance !");
        }
    }
}
