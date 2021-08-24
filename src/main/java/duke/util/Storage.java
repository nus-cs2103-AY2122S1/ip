package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(String encoded) throws IOException {
        File file = linkFileOrCreateFile(filePath);
        FileWriter fw = new FileWriter(file);
        fw.write(encoded);
        fw.close();
    }

    public String[] load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        List<String> lines = new ArrayList<>();
        while(sc.hasNextLine()) {
          lines.add(sc.nextLine());
        }

        return lines.toArray(new String[0]);
    }

    /**
     * Return a File object at given path.
     * If the file and its parent directories do not exist,
     * The methods will create them and then return the File object.
     * @param path
     * @return File object at the given path
     * @throws IOException
     */
    private File linkFileOrCreateFile(String path) throws IOException {
        File file = new File(path);
        if(!file.exists()) {
            checkAndFixParentDirectory(file.getParentFile());
            file.createNewFile();
        }
        return file;
    }

    /** Recursively check if current directory and anscestor directory exsits
     *  if not, create directories.
     * @param currDir current directory to check and fix
     */
    private void checkAndFixParentDirectory(File currDir) {
        if(currDir == null) {
            currDir.mkdir();
        } else if (!currDir.exists()) {
            checkAndFixParentDirectory(currDir.getParentFile());
            currDir.mkdir();
        }
    }
}
