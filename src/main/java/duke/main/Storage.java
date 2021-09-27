package duke.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TaskTag;
import duke.task.Todo;

/**
 * Represents a file to store data, operations to write on the file.
 *
 * @author Gordon
 * @version CS2103T, Semester 2
 */
public class Storage {
    private static final String DIVIDER = " | ";
    private String filePath;
    private String fileName;
    private File file;
    private FileWriter fileWriter;

    /**
     * Class constructor.
     *
     * @param filePath the path from the project directory to the storage file.
     * @param fileName the name of the file.
     */
    public Storage(String filePath, String fileName) {
        this.filePath = filePath;
        this.fileName = fileName;
        assert filePath != null && fileName != null : "filepath and filename cannot be null";
    }

    /**
     * Opens the storage file, if it exists, else creates a new file.
     *
     * @return the storage file.
     * @throws DukeException thrown if there is an exception from creating a new file.
     */
    public File loadStorageFile() throws DukeException {
        try {
            File fileDirectory = new File(filePath);
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
            }
            file = new File(filePath + "/" + fileName);
            System.out.println(file.exists());
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            file = new File(filePath + "/" + fileName);
            throw new DukeException(DukeException.Exceptions.IOException);
        }
        return file;
    }

    /**
     * Loads all the tasks stored in the storage file into an arraylist.
     *
     * @return an arraylists of tasks stored.
     * @throws DukeException thrown if there is an error creating the new tasks.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        File storageFile = loadStorageFile();
        ArrayList<Task> tasks = new ArrayList<>();
        Task task;
        try {
            if (storageFile.length() < 1) {
                return tasks;
            }
            Scanner sc = new Scanner(storageFile);
            String storedTask;
            while (sc.hasNext()) {
                storedTask = sc.nextLine();
                task = createNewTask(storedTask);
                tasks.add(task);
            }
            sc.close();
            return tasks;
        } catch (IOException e) {
            throw new DukeException(DukeException.Exceptions.IOException);
        }
    }
    private Task createNewTask(String taskString) throws DukeException {
        Task task;
        String[] taskComponents = splitIntoTaskComponents(taskString);
        String taskMarker = taskComponents[0];
        switch (taskMarker) {
        case Deadline.DEADLINE_MARKER:
            task = new Deadline(taskComponents[2], taskComponents[3]);
            break;
        case Event.EVENT_MARKER:
            task = new Event(taskComponents[2], taskComponents[3]);
            break;
        case Todo.TODO_MARKER:
            task = new Todo(taskComponents[2]);
            break;
        default:
            throw new DukeException(DukeException.Exceptions.EXCEPTIONS);
        }
        task = checkAndMarkTask(task, taskComponents[1]);
        task = checkAndAddTag(task, taskString, taskComponents);
        return task;
    }

    // returns an string array containing taskMarker, done status, description, date and tag.
    private String[] splitIntoTaskComponents(String taskString) {
        return taskString.split("\\s\\|\\s");
    }
    // checks if the taskString contains a tag and adds the tags the task if so.
    private Task checkAndMarkTask(Task task, String taskDoneStatus) {
        String markedDone = "0";
        if (taskDoneStatus.equals(markedDone)) {
            task.markAsDone();
        }
        return task;
    }
    private Task checkAndAddTag(Task task, String taskString, String[] taskComponents) throws DukeException {
        if (taskString.contains(TaskTag.getTagSymbol())) {
            int lastElementIndex = taskComponents.length - 1;
            task.addTag(taskComponents[lastElementIndex]);
        } else {
            String empty_tag = "";
            task.addTag(empty_tag);
        }
        return task;
    }
    /**
     * Stores all the tasks from the tasks array into the storage file.
     *
     * @param tasks an arraylist of tasks.
     * @throws IOException exception caused in creating new file.
     */
    public void store(TaskList tasks) throws DukeException {
        try {
            fileWriter = new FileWriter(loadStorageFile(), false);
            String data = "";
            for (int i = 0; i < tasks.getNumTasks(); i++) {
                data = data.concat(tasks.getTask(i).formatToStore() + "\n");
            }
            fileWriter.write(data);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(DukeException.Exceptions.IOException);
        }
    }

}
