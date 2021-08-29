package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A representation of a class that handles the reading and writing of the data.
 */
public class Storage {
    private File file;

    /**
     * Constructor for the Storage Class
     *
     * @param file The file containing the data.
     */
    public Storage(File file) {
        this.file = file;
    }

    /**
     * Reads the data from the File.
     * Add the entries to the List according to the order in data.
     *
     * @return A newly created List of Tasks.
     * @throws IOException If the file cannot be read/found.
     */
    public ArrayList<Task> load() throws IOException {
        Scanner myReader = new Scanner(file);
        ArrayList<Task> result = new ArrayList<>();

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            result.add(Task.create(line.split("\\|")));
        }
        myReader.close();
        return result;
    }

    /**
     * Updates the data in the file.
     *
     * @throws IOException If the file cannot be read/found.
     */
    public void writeToFile(List list) throws IOException {
        FileWriter myWriter = new FileWriter("filename.txt");

        for (int i = 0; i < list.getTodos().size(); i++) {
            myWriter.write(list.getTodos().get(i).toDataString());
            myWriter.write(System.lineSeparator());
        }
        myWriter.close();
    }
}
