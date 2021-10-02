package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** class for handling file I/O */
public class FileController {
    /** file path of file */
    private String filePath;
    /** file name of file */
    private String fileName;

    public FileController(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
    }

    /**
     * Writes given text to file specified by this object.
     *
     * @param text String to write.
     * @return True if file writes successfully, false otherwise.
     */
    public boolean writeText(String text) {
        try {
            FileWriter fw;
            try {
                fw = new FileWriter(filePath.concat(fileName));
            } catch (FileNotFoundException e) {
                File f = new File(filePath);
                f.mkdirs();
                fw = new FileWriter(filePath.concat(fileName));
            }
            fw.write(text);
            fw.close();
            return true;
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            return false;
        }
    }

    /**
     * Returns the whole contents of the file in a single String.
     *
     * @return contents of file specified in this object.
     */
    public String readContentsAsString() {
        try {
            File f = new File(filePath.concat(fileName));
            Scanner s = new Scanner(f);
            StringBuilder sb = new StringBuilder();
            while (s.hasNext()) {
                sb.append(s.nextLine()).append("\n");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            return "";
        }
    }
}
