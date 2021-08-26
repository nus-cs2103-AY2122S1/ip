import java.io.File;
import java.io.IOException;

public class Storage {
    static File initialiseDirectory(String pathName) throws DukeException {
        File directory = new File(pathName);
        boolean hasDirectory = directory.exists();

        if (!hasDirectory) {
            hasDirectory = directory.mkdir();
        }

        if (hasDirectory) {
            return directory;
        } else {
            throw new DukeException("\t" + "Unable to initialise directory");
        }
    }

    static File initialiseFile(File directory, String fileName) throws IOException {
        File file = new File(directory + "/" + fileName);
        boolean hasFile = file.exists();

        if (!hasFile) {
            hasFile = file.createNewFile();
        }

        if (hasFile) {
            return file;
        } else {
            throw new IOException("\t" + "Unable to initialise file");
        }
    }
}
