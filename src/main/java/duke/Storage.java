package duke;


import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> readFile() throws java.io.IOException {
        ArrayList<String> arr = new ArrayList<>();
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            } else { // read line by line
                Scanner s = new Scanner(f);
                while (s.hasNextLine()) {
                    arr.add(s.nextLine());
                }
            }
        return arr;
    }

    public void writeFile(ArrayList<String> lines) throws java.io.IOException {
            FileWriter fw = new FileWriter(filePath, false);
            String data = "";
            for (String line: lines) {
                data += (line + "\n");
            }
            fw.write(data);
            fw.close();
    }

    public void appendFile(String line) throws java.io.IOException {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(line); // write each task
            fw.write("\n");
            fw.close();
    }
}
