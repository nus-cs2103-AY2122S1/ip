package gnosis.database;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


/**
 * Database manager to manage read/write to file.
 *
 * @author Pawandeep Singh
 * */
public class DatabaseManager {

    protected static final String DELIMITER = ",";
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_TYPE = "csv";


    private String filePath;
    private File file;

    /**
     * Constructor initialise database.
     * @param fileName name to file to initialise.
     * */
    public DatabaseManager(String fileName) {
        this.filePath = DIRECTORY_PATH
                + "/"
                + fileName
                + "." + FILE_TYPE;
        this.file = new File(filePath);
    }

    public String getFilePath() {
        return this.filePath;
    }


    protected boolean createDataFolder() {
        // create folder
        return !this.file.exists() && file.getParentFile().mkdir();
    }

    public boolean isDataFileAvail() {
        return Files.isDirectory(Paths.get(DIRECTORY_PATH)) && this.file.exists();
    }

    /**
     * copy saved tasked file to indicated path to export to.
     *
     * @param pathToExport path to export file to
     */
    public boolean copyFileToNewPath(File pathToExport) {
        boolean isCopySuccessful;
        try {
            Files.copy(this.file.toPath(), pathToExport.toPath());
            isCopySuccessful = true;
        } catch (IOException e) {
            isCopySuccessful = false;
            e.printStackTrace();
        }
        return isCopySuccessful;
    }
}
