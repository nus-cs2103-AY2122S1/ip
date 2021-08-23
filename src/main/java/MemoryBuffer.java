/**
 * 
 * Represents the memory buffer used to store commands after all
 * changes have been made and the session has ended.
 * 
 * @author Rishabh Anand
 * @version CS2103 AY21/22 Semester 1
 * 
 */
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class MemoryBuffer {
    private String filePath;

    public MemoryBuffer(String fp) {
        filePath = fp;
    }

    public ArrayList<String> readFile() throws FileNotFoundException {
        ArrayList<String> store = new ArrayList<String>();

        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            store.add(data); // store line in collection
        }
        myReader.close();

        return store;
    }

    public void writeFile(DataStore dataStore) throws IOException {
        // BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
        FileWriter fileWriter = new FileWriter(filePath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i < dataStore.length(); i++) {
            Task record = dataStore.get(i);
            String recordRepresentation = record.toTextRepresentation();
            
            printWriter.print(recordRepresentation + "\n");
        }
        printWriter.close();
    }
}
