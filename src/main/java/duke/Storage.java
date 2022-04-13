package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents of a class that handles the reading and writing of the data.
 */
public class Storage {
    public static final String FILENAME = "filename.txt";
    public static final String WRITE_TO_FILE_ERROR_MESSAGE = "ERROR!";
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
     * Adds the entries to the List according to the order in data.
     *
     * @return A newly created List of Tasks.
     * @throws IOException If the file cannot be read/found.
     */
    public ArrayList<Task> load() throws IOException {
        Scanner myReader = new Scanner(file);
        ArrayList<Task> result = new ArrayList<>();

        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            result.add(Task.create(line.split("\\" + Task.STORAGE_SEPARATOR)));
        }
        myReader.close();
        return result;
    }

    /**
     * Updates the data in the file.
     *
     * @throws IOException If the file cannot be read/found.
     */
    public void writeToFile(List list) {
        try {
            FileWriter myWriter = new FileWriter(Storage.FILENAME);

            for (int i = 0; i < list.getTodos().size(); i++) {
                myWriter.write(list.getTodos().get(i).toDataString());
                myWriter.write(System.lineSeparator());
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println(WRITE_TO_FILE_ERROR_MESSAGE);
        }
    }
}
