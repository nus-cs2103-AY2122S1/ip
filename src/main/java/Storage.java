import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
/**
 * Encapsulates the storage for a Duke program.
 */
public class Storage {
    private final File cache;

    /**
     * Constructor for a Storage.
     * 
     * @param filePath Path where cache is stored.
     */
    public Storage(String filePath) {
        cache = new File(filePath);
    }

    /**
     * Load the cache and read any previous tasks, if cache exists.
     * 
     * @return TaskList with any previous tasks.
     * @throws DukeException Thrown if cache cannot load properly.
     */
    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();

        // Check if cache already exists.
        if (cache.exists()) {
            // If cache exists, scan for any previously cached tasks.
            try {
                Scanner sc = new Scanner(cache);
                boolean exit = false;
                Parser parser = new Parser(tasks); 
        
                while(!exit) {
                    try {
                        if (sc.hasNextLine()) {
                            String nextInput = sc.nextLine();
                            String[] resultMsg = parser.parseCommand(nextInput);
                            if (Ui.isByeMsg(resultMsg)) {
                                exit = true;
                            }
                        } else {
                            exit = true;
                        }
                    } catch(DukeException e) {
                        // Ignore previous invalid statements, continue adding tasks.
                    }
                }
                sc.close();
            } catch (FileNotFoundException e) {
                throw new DukeException("Could not load from cache.");
            }
        } else {
            // Otherwise, create the directory for the cache file if necessary.
            File dir = cache.getParentFile();
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new DukeException("Failed to crete required directory.");
                }
            }

            // Create the cache file.
            try {
                if (!cache.createNewFile()) {
                    throw new DukeException("Failed to crete required cache.");
                }
            } catch (IOException e) {
                throw new DukeException("Failed to crete required cache.");
            }
        }
        return tasks;
    }

    /**
     * Add a command to the cache.
     * 
     * @param command String representation of a command.
     * @throws DukeException Thrown if FileWriter fails to append to cache.
     */
    public void cache(String command) throws DukeException {
        try {
            FileWriter fw = new FileWriter(cache, true);
            fw.write(command + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write to cache.");
        }
    }
}
