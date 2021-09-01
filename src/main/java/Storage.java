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

    public Storage() {
        initializeSaveData();
    }

    public Storage(String pathStr) {
        initializeSaveData(pathStr);
    }

    protected void writeLine(String textToWrite) {
        try {
            FileWriter fileWriter = new FileWriter(savePath.toFile(), true);
            fileWriter.write(textToWrite);
            fileWriter.close();
        } catch (IOException e) {
            Ui.printWithIndent("Failed to write to SaveData: " + e);
        }
    }

    protected void removeLine(int lineIndex) {
        try {
            List<String> originalContent = Files.readAllLines(savePath);
            originalContent.remove(lineIndex);
            Files.write(savePath, originalContent);
        } catch (IOException e) {
            Ui.printWithIndent("Failed to remove task in SaveData: " + e);
        }
    }

    protected void setLine(int lineIndex, String textToWrite) {
        try {
            List<String> originalContent = Files.readAllLines(savePath);
            originalContent.set(lineIndex, textToWrite);
            Files.write(savePath, originalContent);
        } catch (IOException e) {
            Ui.printWithIndent("Failed to write to SaveData: " + e);
        }
    }

    private void initializeSaveData() {
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
        } catch (IOException e) {
            Ui.printWithIndent("Initialize Error: Failed to create saving file.");
        }
    }

    private void initializeSaveData(String pathStr) {
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
        } catch (IOException e) {
            Ui.printWithIndent("Initialize Error: Failed to create saving file.");
        }
    }

    protected boolean isEmpty() {
        try {
            return Files.readString(savePath).isEmpty();
        } catch (IOException e) {
            Ui.printWithIndent("Failed to read from SaveData: " + e);
            return true;
        }
    }

    protected List<String> getFileContents() {
        try {
            return Files.readAllLines(savePath);
        } catch (IOException e) {
            Ui.printWithIndent("Failed to read from SaveData: " + e);
            return new ArrayList<String>();
        }
    }
}
