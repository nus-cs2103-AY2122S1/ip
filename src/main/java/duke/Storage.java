package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Encapsulates the storage for a Duke program.
 */
public class Storage {
    private final File cache;

    /**
     * Constructs a Storage object.
     *
     * @param filePath Path where cache is stored.
     */
    public Storage(String filePath) {
        cache = new File(filePath);
    }

    /**
     * Loads the cache and reads any previous tasks, if cache exists.
     *
     * @return TaskList with any previous tasks.
     * @throws DukeException Thrown if cache cannot load properly.
     */
    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        if (cache.exists()) {
            scanCacheIntoTasks(cache, tasks);
        } else {
            createCacheDirectory(cache);
            createCacheFile(cache);
        }
        return tasks;
    }

    /**
     * Scans and parses commands to edit a TaskList.
     *
     * @param scanner Source of commands.
     * @param tasks TaskList to be edited.
     */
    private void scanIntoTasks(Scanner scanner, TaskList tasks) {
        Parser parser = new Parser(tasks);
        while (scanner.hasNextLine()) {
            try {
                String nextInput = scanner.nextLine();
                parser.parseCommand(nextInput);
            } catch (DukeException e) {
                // Skip invalid statements, continue adding tasks.
                // Bad commands from previous caches are not relevant to the user.
            }
        }
    }

    /**
     * Scans and parses commands from cache to edit a TaskList.
     *
     * @param cache File containing previous commands.
     * @param tasks TaskList to be edited.
     * @throws DukeException if scanner fails to load from cache.
     */
    private void scanCacheIntoTasks(File cache, TaskList tasks) throws DukeException {
        try {
            Scanner scanner = new Scanner(cache);
            scanIntoTasks(scanner, tasks);
            scanner.close();
        } catch (FileNotFoundException e) {
            throw new DukeException("Could not load from cache.");
        }
    }

    /**
     * Creates a directory for the cache, if it doesn't yet exist.
     *
     * @param cache Path where cache will be stored.
     * @throws DukeException if directory cannot be created.
     */
    private void createCacheDirectory(File cache) throws DukeException {
        File dir = cache.getParentFile();
        if (!dir.exists() && !dir.mkdirs()) {
            throw new DukeException("Failed to create required directory.");
        }
        assert dir.exists() : "Directory for cache should exist after creation.";
    }

    /**
     * Creates the file for the cache.
     *
     * @param cache Path where cache will be stored.
     * @throws DukeException if cache file cannot be created.
     */
    private void createCacheFile(File cache) throws DukeException {
        try {
            if (!cache.createNewFile()) {
                throw new DukeException("Failed to create required cache.");
            }
        } catch (IOException e) {
            throw new DukeException("Failed to create required cache.");
        }
        assert cache.exists() : "Cache file should exist after creation.";
    }

    /**
     * Writes a command to the cache.
     *
     * @param command String representation of a command.
     * @throws DukeException if FileWriter fails to append to cache.
     */
    public void writeCommand(String command) throws DukeException {
        try {
            FileWriter fw = new FileWriter(cache, true);
            fw.write(command + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Failed to write to cache.");
        }
    }
}
