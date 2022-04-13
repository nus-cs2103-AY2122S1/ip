package duke.storage;

import duke.exception.DukeException;
import duke.exception.FileNotFoundDukeException;
import duke.exception.BadFileCreationDukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A Storage class that handles the storage of task list.
 * @author KelvinSoo
 * @version A-MoreOOP
 */
public class Storage {

    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load task method.
     * Load the task list at the given file path.
     * @return The task list from the file.
     */
    public List<Task> load() throws DukeException {
        File file = getFile();
        return getTasksFromFile(file);
    }

    /**
     * Save task method.
     * Save the task list at the given file path.
     * @param taskList The task list to save.
     * @throws DukeException invalid location to save.
     */
    public void save(TaskList taskList) throws DukeException {
        getFile();
        writeTasksToFile(taskList);
    }

    private boolean isValidFilepath(String filePath){
        File file = new File(filePath);
        return file.isFile() && !file.isDirectory();
    }

    private void createFile(String filePath) throws DukeException {
        Path pathToFile = Paths.get(filePath);
        try {
            Files.createDirectories(pathToFile.getParent());
            Files.createFile(pathToFile);
        } catch (IOException e) {
            throw new BadFileCreationDukeException();
        }
    }

    private File getFile() throws DukeException {
        if (!isValidFilepath(filePath)) {
            createFile(filePath);
        }
        return new File(filePath);
    }

    private List<Task> getTasksFromFile(File file) throws DukeException {
        Scanner sc;
        List<Task> list = new ArrayList<>();
        try {
             sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new BadFileCreationDukeException();
        }
        while (sc.hasNext()) {
            String taskMeta = sc.nextLine();
            String[] taskParameter = taskMeta.split("\\|");
            switch (taskParameter[0]) {
            case "T":
                list.add(new ToDo(taskParameter[2], taskParameter[1].equals("X")));
                break;
            case "D":
                list.add(new Deadline(taskParameter[2], taskParameter[3], taskParameter[1].equals("X")));
                break;
            case "E":
                list.add(new Event(taskParameter[2], taskParameter[3], taskParameter[1].equals("X")));
                break;
            default:
                //File is corrupted
                list = new ArrayList<>();
                break;
            }
        }
        return list;
    }

    private void writeTasksToFile(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t:taskList.getTaskList()) {
                fw.write(t.getMetaData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new FileNotFoundDukeException();
        }
    }
}
