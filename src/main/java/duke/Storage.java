package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Manages the writing and creation of files duke.txt
 */
public class Storage {

    public Storage() {
    }

    /**
     * Creates duke.txt in data file, any changes to tasklist is written there
     *
     * @throws IOException
     */
    public void createFile() throws IOException {
        Files.createDirectories(Paths.get("src/main/data"));
        File file = new File("src/main/data/duke.txt");
        if (!(file.exists())) {
            file.createNewFile();
        }

    }

    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Takes in arraylist of tasks and write the contents to duke.txt
     *
     * @param listOfText2 the arraylist of current tasks
     */
    public void save(List<Task> listOfText2) {
        String temp = "";
        for (int i = 0; i < listOfText2.size(); i++) {
            temp = temp + listOfText2.get(i).saveWithFormat() + "\n";
        }

        try {
            writeToFile("src/main/data/duke.txt", temp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
