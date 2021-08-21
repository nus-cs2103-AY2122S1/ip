package entity.data;

import entity.message.Message;

import java.io.File;
import java.io.IOException;

public class Data {
    private final static String dataDirectoryPath = "./data";

    public static DukeFile loadListFile() {
        try {
            File directory = new File(dataDirectoryPath);
            directory.mkdirs();

            String listFileName = "duke.txt";
            String filePath = String.format("%s/%s", dataDirectoryPath, listFileName);
            return DukeFile.loadFile(filePath);
        } catch (IOException e) {
            Message message = new Message("There was a problem in getting the list data");
            message.print();
            return null;
        }
    }
}
