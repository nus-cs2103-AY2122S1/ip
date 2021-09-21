package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The class that implements storage functions of the project.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor of Storage class.
     * It instantiates a Storage object that is associated with
     * the file specified by the input path.
     *
     * @param filePath The path of the file the Storage will be associated with.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * The method saves the given String (encoded content) to the associated file.
     *
     * @param encodedStr String containing encoded/formatted content.
     * @throws IOException When there is problem in I/O system.
     */
    public void save(String encodedStr) throws IOException {
        File file = linkFileOrCreateFile(filePath);
        FileWriter fw = new FileWriter(file);
        fw.write(encodedStr);
        fw.close();
    }


    /**
     * The method loads Strings from the associated file,
     * and return an array containing rows of lines of the file.
     *
     * @return A String array containing lines of the file.
     * @throws FileNotFoundException When the file does not exist.
     */
    public String[] load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }

        return lines.toArray(new String[0]);
    }


    /**
     * Return a File object at given path.
     * If the file and its parent directories do not exist,
     * The methods will create them and then return the File object.
     *
     * @param path Path of the file to be linked or created.
     * @return File object at the given path
     * @throws IOException When I/O system goes wrong.
     */
    private File linkFileOrCreateFile(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            checkAndFixParentDirectory(file.getParentFile());
            file.createNewFile();
        }
        return file;
    }


    /**
     * Recursively check if current directory and ancestor directory exsits
     * if not, create directories.
     *
     * @param currDir current directory to check and fix
     */
    private void checkAndFixParentDirectory(File currDir) {
        if (currDir == null) {
            currDir.mkdir();
        } else if (!currDir.exists()) {
            checkAndFixParentDirectory(currDir.getParentFile());
            currDir.mkdir();
        }
    }
}
