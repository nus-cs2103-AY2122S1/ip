package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import duke.errors.ArchiveException;

/**
 * Handles operations related to Archives.
 */
public class Archive {

    /**
     * Constructor.
     *
     * Creates a new archive directory if it has not been created before.
     */
    public Archive() {
        File file = new File("src/archive");
        if (!file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * Creates a new archive file and saves everything on the current hard disk to the archive file.
     *
     * @param fileName of the Archive file the user creates.
     * @throws ArchiveException thrown when the user already created an archive file of the same name.
     */
    public void newArchive(String fileName) throws ArchiveException {
        String filePath = "src/archive/" + fileName + ".txt";
        File archiveFile = new File(filePath);
        if (archiveFile.exists()) {
            throw new ArchiveException(" archive file of this name already exists.");
        }

        try {
            File dukeFile = new File("src/data/duke.txt");
            Files.copy(dukeFile.toPath(), archiveFile.toPath());
        } catch (IOException ioException) {
            throw new ArchiveException(" to create a new " + fileName + " Archive.");
        }
    }


    /**
     * Deletes an archive file.
     *
     * @param fileName of archive file to delete.
     * @throws ArchiveException if unable to delete the file.
     */
    public void deleteArchive(String fileName) throws ArchiveException {
        String filePath = "src/archive/" + fileName + ".txt";
        File f = new File(filePath);
        if (!f.delete()) {
            throw new ArchiveException(" to delete " + fileName + " Archive.");
        }
    }
}
