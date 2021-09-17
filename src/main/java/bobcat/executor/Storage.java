package bobcat.executor;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

import bobcat.executor.parser.StorageParser;
import bobcat.model.TaskList;
import bobcat.model.task.Task;


/**
 * Represents the "memory" part of the application. A <code>Storage</code> object dictates <i>how</i>
 * <code>TaskList</code> should be modified according to storage file, and also stores data into storage file.
 * This class enables seamless interface between chat application and storage method, should the need arise to modify
 * storage method e.g. file extensions, etc
 */
public class Storage {
    /**
     * Initialises given <code>TaskList</code> according to the content of the file in storagePath. Uses a
     * <code>StorageParser</code> object to parse the content of the file. If any single line in the file cannot be
     * understood by <code>StorageParser</code>, the whole operation stops.
     * @param storagePath Relative path to storage file
     * @param taskList TaskList to be appended onto
     * @throws IOException May throw IOException if storagePath is not appropriate.
     */
    public void initStorage(String storagePath, TaskList taskList) throws IOException {
        assert !Objects.equals(storagePath, null) && !Objects.equals(storagePath, "");

        StorageParser storeParser = new StorageParser();
        Path path = Paths.get(storagePath);
        List<String> read = Files.readAllLines(path);
        for (String storeCmd : read) {
            Task t = storeParser.parse(storeCmd);
            taskList.push(t);
        }
    }

    /**
     * Saves given <code>TaskList</code> into a file according to storagePath. Will overwrite existing file, or
     * create it if given filename does not exist.
     * @param storagePath Relative path to storage file
     * @param taskList TaskList to be saved
     * @throws IOException May throw IOException if storagePath is not appropriate.
     */
    public void saveStorage(String storagePath, TaskList taskList) throws IOException {
        Path path = Paths.get(storagePath);
        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
        Files.createFile(path);

        Task[] all = taskList.getAllTasks();
        BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.APPEND);

        for (Task t : all) {
            bw.write(t.toString() + "\n");
        }
        bw.close();
    }
}
