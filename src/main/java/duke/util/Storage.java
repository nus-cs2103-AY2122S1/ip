package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    String filePath = "./data/duke.txt";

    public Storage() {

    }

    public File loadDataFile() {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                File dir = new File("./data");
                if(dir.mkdir() && f.createNewFile()) {
                    return f;
                }
            }
            return f;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void writeToDisk(String data) throws DukeException {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("Unable to write to disk :(");
        }
    }
}
