import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Represents the class that deals with loading tasks from file and saving tasks to file.
 * 
 * @author Sherman Ng Wei Sheng
 */
public class Storage {
    private final String fileDir;
    private final String filePath;
    
    public Storage(String filePath) {
        this.filePath = filePath;
        this.fileDir = this.filePath.substring(0,this.filePath.lastIndexOf("/") + 1);
    }
    
    public String load() throws StorageLoadingException {
        try {
            File storageDir = new File(this.fileDir);
            File storageFile = new File(this.filePath);
            boolean isDirExistent = storageDir.exists();
            boolean isFileExistent = storageFile.exists();
            if (!isDirExistent) {
                storageDir.mkdirs();
            }
            if (!isFileExistent) {
                storageFile.createNewFile();
            }
            Scanner scanner = new Scanner(storageFile);
            StringBuilder data = new StringBuilder();
            while (scanner.hasNext()) {
                data.append(scanner.nextLine()).append("\n");
            }
            return data.toString();
        } catch (IOException e) {
            throw new StorageLoadingException();
        }
    }
    
    public void save(String data) throws StorageSavingException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw new StorageSavingException();
        }
    }
}
