package duke;

import duke.task.DukeList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class managing storing and loading data into duke.Duke's list.
 */
public class Storage {

    /** PATH to save file. */
    private final String PATH;

    /** List where data is loaded to and saved from. */
    private final DukeList LIST;

    /**
     * Constructs a duke.Storage object.
     *
     * @param path PATH to the storage file.
     * @param list List where data is loaded to and saved from.
     */
    public Storage(String path, DukeList list) {
        this.PATH = path;
        this.LIST = list;
    }

    /**
     * Saves data from list provided into file in PATH.
     */
    public void save() {
        try {
            FileWriter fileWriter = new FileWriter(this.PATH + "\\data\\data.txt");
            fileWriter.write(LIST.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Loads data from file in PATH to list provided.
     */
    public void load() {
        try {
            Path folderPath = Paths.get(this.PATH + "\\data");
            Path filePath = Paths.get(this.PATH + "\\data\\data.txt");
            boolean hasDirectory = Files.exists(folderPath);
            boolean hasSaveFile = Files.exists(filePath);

            if (!hasDirectory) {
                File folder = new File(this.PATH + "\\data");
                File saveFile = new File(this.PATH + "\\data\\data.txt");

                folder.mkdir();
                saveFile.createNewFile();
            } else if (!hasSaveFile) {
                File saveFile = new File(this.PATH + "\\data\\data.txt");
                saveFile.createNewFile();
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.PATH
                    + "\\data\\data.txt"));
            String data = bufferedReader.readLine();
            String type;
            String state;
            String body;

            String[] segregate;

            while (data != null) {
                segregate = data.split(" ", 3);

                type = segregate[0];
                state = segregate[1];
                body = segregate[2];

                this.LIST.loadData(type, state, body);

                data = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
