package duke.core;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public File retrieveTasks() throws DukeException {
        File file = new File(filePath);
        try {
            if (file.exists()) {
                return file;
            }
            throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            createStorageFile(file);
            return null;
        }
    }

    public void createStorageFile(File file) throws DukeException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e2) {
            throw new DukeException("Something unexpected happened to the file: " + e2.getMessage());
        }
    }

    public void saveTasks(String dataText) throws DukeException {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                throw new FileNotFoundException();
            }
        } catch (FileNotFoundException e) {
            createStorageFile(file);
        } finally {
            try {
                FileWriter fw = new FileWriter(filePath);
                fw.write(dataText);
                fw.close();
            } catch (IOException e) {
                throw new DukeException("Something happened to the file when saving: " + e.getMessage());
            }
        }
    }
}
