package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Encapsulates the HP storage class.
 */
public class HpStorage {
    private static final String DIR_PATH = "data/";
    private final String saveFilePath = "data/hp.txt";

    /**
     * Loads hp count.
     *
     * @return The hp count.
     * @throws IOException If there is error reading from the file.
     */
    public int loadHpFromFile() throws IOException {
        try {
            FileReader fileReader = new FileReader(saveFilePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            return Integer.parseInt(bufferedReader.readLine());
        } catch (FileNotFoundException e) {
            return 0;
        }
    }

    /**
     * Writes new data to file.
     *
     * @param hp Hp count to be saved.
     */
    public void writeHpToFile(int hp) throws IOException {
        File dir = new File(DIR_PATH);
        if (!Files.exists(Paths.get(DIR_PATH))) {
            dir.mkdir();
        }
        File file = new File(saveFilePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(saveFilePath);
        fileWriter.write(String.valueOf(hp));
        fileWriter.close();
    }
}
