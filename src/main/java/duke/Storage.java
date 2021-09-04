package duke;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

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

    public ArrayList<String> readFile() throws java.io.IOException {
        ArrayList<String> arr = new ArrayList<>();
        Scanner s = new Scanner(this.file);
        while (s.hasNextLine()) {
            arr.add(s.nextLine());
        }
        return arr;
    }

    public void writeFile(ArrayList<String> lines) throws java.io.IOException {
        FileWriter fw = new FileWriter(this.file, false);
        String data = "";
        for (String line: lines) {
            data += (line + "\n");
        }
        fw.write(data);
        fw.close();
    }

    public void appendFile(String line) throws java.io.IOException {
        FileWriter fw = new FileWriter(this.file, true);
        fw.write(line); // write each task
        fw.write("\n");
        fw.close();
    }
}
