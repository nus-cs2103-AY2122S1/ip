/**
 * 
 * Represents the memory buffer used to store commands after all
 * changes have been made and the session has ended.
 * 
 * @author Rishabh Anand
 * @version CS2103 AY21/22 Semester 1
 * 
 */

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class MemoryBuffer {
    private String filePath;
    private ArrayList<Task> memory;

    public MemoryBuffer(String fp, ArrayList<Task> mem) {
        filePath = fp;
        memory = mem;
    }

    public void writeFile() throws IOException {
        // BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        FileWriter fileWriter = new FileWriter(filePath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i < memory.size(); i++) {
            Task record = memory.get(i);
            String recordRepresentation = record.toTextRepresentation();
            
            printWriter.print(recordRepresentation + "\n");
        }
        printWriter.close();
    }
}
