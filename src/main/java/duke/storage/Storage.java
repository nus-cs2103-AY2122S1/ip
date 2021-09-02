package duke.storage;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A Parser class that handles the storage of task list.
 *
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
     * @throws DukeException file/folder not found.
     */
    public List<Task> load() throws DukeException {
        String[] folders = filePath.split("/");
        for (String s:folders) {
            File f = new File(s);
            if (!s.contains(".") && !f.isDirectory()) {
                throw new DukeException(String.format("OOPS!!! \"%s\" folder does not exist.", s));
            }
        }

        File file = new File(filePath);
        List<Task> list = new ArrayList<>();

        // Load all tasks from the file
        try {
            Scanner sc = new Scanner(file);
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
                    throw new DukeException("OOPS!!! File appears to be corrupted.");
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            throw new DukeException("OOPS!!! File not found.");
        }
    }

    /**
     * Save task method.
     * Save the task list at the given file path.
     * @param taskList The task list to save.
     * @throws DukeException invalid location to save.
     */
    public void save(TaskList taskList) throws DukeException {

        File file = new File(filePath);
        if (file.isDirectory()) {
            throw new DukeException(String.format("OOPS!!! \"%s\" is not a file but a folder.", filePath));
        }
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t:taskList.getTaskList()) {
                fw.write(t.getMetaData() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException(String.format("OOPS!!! File path \"%s\" is invalid", filePath));
        }
    }
}
