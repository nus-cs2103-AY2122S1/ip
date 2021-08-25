package duke;

import duke.exception.CorruptedFileException;
import duke.exception.DukeException;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Class to loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    /** Save file path. */
    private final static String SAVE_FILE = "data/tasks.txt";
    /** Save file location. */
    private final static String SAVE_DIR = "data/";

    /**
     * Returns the Success/Error message after loading save file.
     *
     * @param list DukeList to be populated.
     * @return Success/Error message.
     */
    public static String load(DukeList list) {
        File file = new File(SAVE_FILE);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Parser.parseFileLine(line);
                list.add(task);
            }
            scanner.close();
            return "Save file successfully loaded";
        } catch (DukeException e) {
            return "Error loading save file, list might be messed up";
        } catch (FileNotFoundException e) {
            return "No save file found, making a new one";
        }
    }

    /**
     * Saves DukeList into save file.
     *
     * @param list DukeList to be saved.
     * @throws CorruptedFileException If error occurs while writing into save file.
     */
    public static void saveFile(DukeList list) throws CorruptedFileException {
        File dir = new File(SAVE_DIR);
        if (!Files.exists(Paths.get(SAVE_DIR))) {
            dir.mkdir();
        }
        File file = new File(SAVE_FILE);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(SAVE_FILE);
            for (Task t : list) {
                fw.write(t.formatSave() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new CorruptedFileException();
        }
    }
}
