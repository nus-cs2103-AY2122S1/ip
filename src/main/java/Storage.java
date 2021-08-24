import java.io.*;
import java.util.Scanner;

// todo
// 1. Check existence of file and create it
// 2. Read from file
// 3. Save taskList to file

public class Storage {
    String filePath;

    public Storage(String filePath) {
        // Check if Kermit data exists, else create it
        this.filePath = filePath;
    }
}