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

public class Storage {
    private final static String SAVE_FILE = "data/tasks.txt";
    private final static String SAVE_DIR = "data/";

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
