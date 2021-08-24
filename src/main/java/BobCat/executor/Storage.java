package BobCat.executor;

import BobCat.executor.parser.StorageParser;
import BobCat.model.TaskList;
import BobCat.model.task.Task;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Storage {
    public void initStorage(String storagePath, TaskList taskList) throws IOException {
        StorageParser storeParser = new StorageParser();
        Path path = Paths.get(storagePath);
        List<String> read = Files.readAllLines(path);
        for (String storeCmd : read) {
            Task t = storeParser.parse(storeCmd);
            taskList.push(t);
        }
    }

    // TODO: Find a better way to do this..
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
