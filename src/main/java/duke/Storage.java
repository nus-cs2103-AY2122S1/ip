package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private File storage;
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
        this.storage = new File(fileName);
    }

    public TaskList load() {
        try {
            this.storage.createNewFile();
        } catch (IOException error) {
            System.out.println("Ensure you have created a folder named 'data' within the main project directory!");
        }
        try {
            Scanner fileScanner = new Scanner(this.storage);
            TaskList tasklist = new TaskList();

            while (fileScanner.hasNext()) {
                String fileData = fileScanner.nextLine();

            }
        } catch (FileNotFoundException e) {
            System.out.println("Ensure you have created a folder named 'data' within the main project directory!");
        }

        //load contents into TaskList
    }

    private void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(this.fileName, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public void save(String history) {
        try {
            appendToFile(history + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
