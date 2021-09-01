package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Storage {
    // About save file
    private static final String[] SAVE_DIRECTORY = new String[] {"data"};
    private static final String SAVE_FILENAME = "duke.txt";
    private static Path savePath;

    private boolean initialized;

    public Storage() {
        this.initialized = false;
    }

    protected void initialize() throws DukeException {
        try {
            Path path = Arrays.stream(SAVE_DIRECTORY)
                    .map(Paths::get)
                    .reduce(Paths.get(""), Path::resolve)
                    .resolve(SAVE_FILENAME);

            if (!Files.exists(path)) {
                Path p = Paths.get("");
                for (String s : SAVE_DIRECTORY) {
                    p = p.resolve(s);
                    if (!Files.exists(p))
                        Files.createDirectory(p);
                }

                if (!Files.exists(path)) {
                    Files.createFile(path);
                }
            }

            savePath = path;
            this.initialized = true;
        } catch (IOException e) {
            throw new DukeException(ExceptionType.FAIL_TO_READ, e.getMessage());
        }
    }

    protected void initialize(String pathStr) throws DukeException{
        try {
            Path path = Paths.get(pathStr);

            if (!Files.exists(path)) {
                Path p = Paths.get("");
                Iterator<Path> iterator = p.iterator();
                while (iterator.hasNext()) {
                    p = p.resolve(iterator.next());
                    if (!p.equals(path) && !Files.exists(p))
                        Files.createDirectory(p);
                }

                if (!Files.exists(path)) {
                    Files.createFile(path);
                }
            }

            savePath = path;
            this.initialized = true;
        } catch (IOException e) {
            throw new DukeException(ExceptionType.FAIL_TO_READ, e.getMessage());
        }
    }

    protected void writeLine(String textToWrite) throws DukeException {
        if (!this.initialized) return;
        try {
            FileWriter fileWriter = new FileWriter(savePath.toFile(), true);
            fileWriter.write(textToWrite);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(ExceptionType.FAIL_TO_WRITE, e.getMessage());
        }
    }

    protected void removeLine(int lineIndex) throws DukeException {
        if (!this.initialized) return;
        try {
            List<String> originalContent = Files.readAllLines(savePath);
            originalContent.remove(lineIndex);
            Files.write(savePath, originalContent);
        } catch (IOException e) {
            throw new DukeException(ExceptionType.FAIL_TO_WRITE, e.getMessage());
        }
    }

    protected void setLine(int lineIndex, String textToWrite) throws DukeException {
        if (!this.initialized) return;
        try {
            List<String> originalContent = Files.readAllLines(savePath);
            originalContent.set(lineIndex, textToWrite);
            Files.write(savePath, originalContent);
        } catch (IOException e) {
            throw new DukeException(ExceptionType.FAIL_TO_WRITE, e.getMessage());
        }
    }

    protected boolean isEmpty() throws DukeException {
        if (!this.initialized) return true;
        try {
            return Files.readString(savePath).isEmpty();
        } catch (IOException e) {
            throw new DukeException(ExceptionType.FAIL_TO_READ, e.getMessage());
        }
    }

    protected List<String> getFileContents() throws DukeException {
        if (!this.initialized) return new ArrayList<String>();
        try {
            return Files.readAllLines(savePath);
        } catch (IOException e) {
            throw new DukeException(ExceptionType.FAIL_TO_READ, e.getMessage());
        }
    }
}
