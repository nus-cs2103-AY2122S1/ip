package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Storage class is responsible for dealing with loading tasks from file and saving tasks in the file.
 */
public class Storage {
    private final File file;

    /**
     * Creates a new Storage instance at the specified path.
     * @param pathname A pathname string
     */
    public Storage(String pathname) {
        this.file = new File(pathname);

        boolean isExistingFile = this.file.exists();
        if (!isExistingFile) {
            this.file.getParentFile().mkdirs();
            try {
                this.file.createNewFile();
            } catch (IOException exception) {
                System.out.printf("An I/O exception occurred when trying to create file %s\n", pathname);
            }
        }
    }

    /**
     * Reads and returns the contents of the file.
     * @return The String contents of the file.
     */
    public String read() {
        try {
            Scanner scanner = new Scanner(this.file);
            StringBuilder contents = new StringBuilder();
            if (!scanner.hasNextLine()) {
                return null;
            }

            contents.append(scanner.nextLine());
            while (scanner.hasNextLine()) {
                contents.append(System.lineSeparator()).append(scanner.nextLine());
            }

            scanner.close();
            return contents.toString();
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("File not found when trying to read file from Storage class.");
        }
    }

    /**
     * Writes the specified contents into the Storage file.
     * @param contents The String contents to write to the Storage file.
     */
    public void write(String contents) {
        try {
            FileWriter fileWriter = new FileWriter(this.file);
            fileWriter.write(contents);
            fileWriter.close();
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("File not found when trying to write to file from Storage class.");
        } catch (IOException exception) {
            throw new RuntimeException(
                String.format("An I/O exception occurred when trying to write to file %s\n", this.file.getPath())
            );
        }
    }
}
