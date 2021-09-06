/**
 * 
 * Represents the memory buffer used to store commands after all
 * changes have been made and the session has ended.
 * 
 * @author Rishabh Anand
 * @version CS2103 AY21/22 Semester 1
 * 
 */

package duke.membuffer;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner;

import duke.datastore.DataStore;
import duke.tasks.Task;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class MemoryBuffer {
    private final String filePath;

    public MemoryBuffer(String fp) {
        filePath = fp;
    }

    /**
     *
     * Reads the flat file and its contents into an accessible format.
     *
     * @return an ArrayList of task records in string format.
     * @throws FileNotFoundException thrown when the file cannot be found in the file system.
     */
    public ArrayList<String> readFile() throws FileNotFoundException {
        ArrayList<String> store = new ArrayList<>();

        File myObj = new File(filePath);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();
            store.add(data); // store line in collection
        }
        myReader.close();

        return store;
    }

    /**
     *
     * Writes the most recent task record logs into memory on a flat file.
     *
     * @param dataStore the data store containing the updated list of tasks.
     * @throws IOException thrown when there is an error reading the file.
     */
    public void writeFile(DataStore dataStore) throws IOException {
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
