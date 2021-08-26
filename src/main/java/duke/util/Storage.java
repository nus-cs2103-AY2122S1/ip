package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Writer {

    private File savedOutput;

    public Writer() {
        try {
            File dir = new File("tmp/test");
            dir.mkdirs();
            File savedOutput = new File("tmp/savedOutput.txt");
            if (!savedOutput.exists()) {
                savedOutput.createNewFile();
            } else {
                savedOutput.delete();
                savedOutput.createNewFile();
            }
            this.savedOutput = savedOutput;
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    public void saveMsg(String msg) {
        try {
            FileWriter fileWriter = new FileWriter(savedOutput, true);
            fileWriter.write(msg);
            fileWriter.write(System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("file not found");
        }

    }

}
