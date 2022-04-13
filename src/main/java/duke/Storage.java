package duke;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Storage is concerned with the persisting of the program state in a specified location of the computer's storage
 */
public class Storage {
    File file;

    public Storage(String filePath) throws java.io.IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }
        this.file = f;
    }

    /**
     * reads file line by line
     * @return ArrayList of lines read from file
     * @throws java.io.IOException if there is error accessing file
     */
    public ArrayList<String> readFile() throws java.io.IOException {
        ArrayList<String> arr = new ArrayList<>();
        Scanner s = new Scanner(this.file);
        while (s.hasNextLine()) {
            arr.add(s.nextLine());
        }
        return arr;
    }

    /**
     * writes to file line by line
     * @param lines ArrayList of lines to be written to file
     * @throws java.io.IOException if there is error accessing file
     */
    public void writeFile(ArrayList<String> lines) throws java.io.IOException {
        FileWriter fw = new FileWriter(this.file, false);
        String data = "";
        for (String line: lines) {
            data += (line + "\n");
        }
        fw.write(data);
        fw.close();
    }

    /**
     * appends line to file
     * @param line to be appended to file
     * @throws java.io.IOException
     */
    public void appendFile(String line) throws java.io.IOException {
        FileWriter fw = new FileWriter(this.file, true);
        fw.write(line); // write each task
        fw.write("\n");
        fw.close();
    }
}
