package entity.data;

import java.io.File;
import java.io.IOException;

public class DukeFile {
    private String filePath;
    private File file;

    private DukeFile(String filePath, File file) {
        this.filePath = filePath;
        this.file = file;
    }

    public static DukeFile loadFile(String filePath) throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        return new DukeFile(filePath, file);
    }
}
