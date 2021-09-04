package storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * File Storage that stores a list of objects.
 *
 * @param <T> Type of the object to be stored.
 */
public class FileListStorage<T> {
    private final Logger logger = Logger.getLogger(FileListStorage.class.getName());
    
    /** Path leading to the file */
    private final String filePath;
    
    /**
     * Main.Main constructor of FileListStorage.
     *
     * @param filePath String representing the path to the file, separated by / char.
     */
    public FileListStorage(String filePath) {
        this.filePath = filePath;
        
        try {
            // a local file is used to persistently store all the tasks,
            // and it should only have 1 object, which is the list of task
            File file = new File(filePath);
            boolean isDirCreated = file.getParentFile().mkdirs();
            boolean isFileCreated = file.createNewFile();
            
            if (isDirCreated && isFileCreated) {
                writeArrayListToFile(new ArrayList<>());
                logger.info("FileListStorage is successfully created at " + file.getAbsolutePath());
            } else {
                logger.info("FileListStorage already exists at " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            logger.severe("Task file cannot be created " + e.getMessage());
        }
    }
    
    /**
     * Writes the serialized ArrayList of Task into a file.
     * It will log IOException error accordingly.
     *
     * @param items ArrayList of items to be written.
     */
    public void writeArrayListToFile(ArrayList<T> items) {
        try (FileOutputStream file = new FileOutputStream(filePath);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(file)
        ) {
            objectOutputStream.writeObject(items);
            logger.fine("Writing Task list to file : " + filePath);
        } catch (IOException e) {
            logger.severe("Writing tasks to file " + filePath + " failed : " + e.getMessage());
        }
    }
    
    /**
     * Reads the serialized ArrayList of Task from a file and return it.
     * It will log IOException and ClassNotFound error accordingly.
     */
    public ArrayList<T> readArrayListFromFile() {
        try (FileInputStream file = new FileInputStream(filePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(file)
        ) {
            @SuppressWarnings("unchecked")
            // Casting in mandatory here since we are reading objects
            ArrayList<T> tasks = (ArrayList<T>) objectInputStream.readObject();
            logger.fine("Reading Task List from file : " + filePath);
            
            return tasks;
        } catch (IOException e) {
            logger.severe("Reading Task List Failed : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.severe("Object cannot be casted to array of task : " + e.getMessage());
        }
        
        // Since it cannot be read, we rewrite the file with a new file.
        return new ArrayList<>();
    }
}
