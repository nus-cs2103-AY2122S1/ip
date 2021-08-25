package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    String filepath = "src/main/data/duke.txt";

    public Storage() {
    }

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

    public void save(List<Task> listOfText2) {
        String temp = "";
        for (int i = 0; i < listOfText2.size(); i++) {
            temp = temp + listOfText2.get(i) + "\n";
        }

        try {
            writeToFile(filepath, temp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
