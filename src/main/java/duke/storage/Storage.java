package duke.storage;

import duke.tasks.Task;
import duke.DukeExceptions;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private static final Path DIRECTORY = Paths.get("data");
    private static final Path FILE_PATH = Paths.get("data", "duke.txt");
    private TaskList tasks = new TaskList();

    private static void directoryCreator() {
        try {
            Files.createDirectories(DIRECTORY);
        } catch (FileAlreadyExistsException e) {

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    private static void fileCreator() {
        try {
            Files.createFile(FILE_PATH);
        } catch (FileAlreadyExistsException e) {

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void open() {
        directoryCreator();
        fileCreator();
        List<String> saveFile = null;
        try {
            saveFile = Files.readAllLines(FILE_PATH);
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
        
        tasks.importFromList(saveFile);

    }
    
    public void save() {
        try {
            Files.write(FILE_PATH, tasks.exportToText().getBytes());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public String getList() {
        return tasks.toString();
    }

    public void addToList(Task task) {
        tasks.add(task);
    }

    public void deleteFromList(int index) {
        tasks.remove(index);
    }

    public void markAsFinished(int index) throws DukeExceptions {
        tasks.markAsFinished(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }
}
