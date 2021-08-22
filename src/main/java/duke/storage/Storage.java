package duke.storage;

import duke.tasklist.Task;
import duke.exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Scanner load() throws DukeException {
        try {
            return new Scanner(new File(filePath));
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static void save(List<Task> lib) throws IOException {
        String location = "data/duke.txt";

        File f = new File(location);
        if (!f.exists()) {
            if (f.getParentFile().mkdirs()) {
                System.out.println("Directory created");
            }
            if (f.createNewFile()) {
                System.out.println("Text file created");
            }
        }

        FileWriter fw = new FileWriter(location);
        for (Task tsk: lib) {
            fw.write(tsk.save() + System.lineSeparator());
        }
        fw.close();
    }
}
