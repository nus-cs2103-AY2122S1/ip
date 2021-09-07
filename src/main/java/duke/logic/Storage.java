package duke.logic;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Deals with the logic of loading data from a file and writing to the
 * file each time there is an update to the task list.
 */
public class Storage {
    private final TaskList taskList;
    private final File textFile;

    /**
     * Creates a new instance of a storage object that deals with saving the task list in Duke.
     *
     * @param filePath The string representing the path of the file that the data will be saved into.
     * @param taskList The list of task that will be saved into the file.
     */
    public Storage(String filePath, TaskList taskList) throws IOException {
        this.taskList = taskList;
        textFile = new File(filePath);
        if (!textFile.exists()) {
            textFile.createNewFile();
        }
        if (!textFile.canWrite() || !textFile.canRead()) {
            throw new IOException("This file cannot be written or read by duke!");
        }
        // Attempts to load file (even an empty one!)
        loadFileIntoTaskList(taskList, textFile);
    }

    /**
     * Loads the data file into the task list of duke.
     *
     * @param list the task list in duke
     */
    private static void loadFileIntoTaskList(TaskList list, File textFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(textFile));
            for (String lineOfData = reader.readLine(); lineOfData != null; lineOfData = reader.readLine()) {
                list.addTask(parseStringIntoTask(lineOfData));
            }
        } catch (IOException e) {
            System.out.printf("However an error occurred while writing to %s:\n", textFile.getAbsolutePath());
            e.printStackTrace();
        }
    }

    private static Task parseStringIntoTask(String line) {
        String corruptFileMsg = "Storage file is corrupted. Please fix it or delete the file.";
        char taskType = line.charAt(0); //'T' or 'D' or 'E'
        boolean isDone = Integer.parseInt(line.substring(4, 5)) == 1; // 1 if isDone, 0 otherwise
        String[] descriptionAndDatetime = line.substring(8).split(" \\| ", 3);
        Task task;
        if (taskType == 'T') {
            task = new Todo(descriptionAndDatetime[0]);
        } else if (taskType == 'D') {
            if (descriptionAndDatetime.length != 2) {
                throw new DukeException(corruptFileMsg);
            }
            task = new Deadline(descriptionAndDatetime[0],
                DateTimeParser.getDateTimeFromDataString(descriptionAndDatetime[1]));
        } else if (taskType == 'E') {
            if (descriptionAndDatetime.length != 3) { // includes start and end
                throw new DukeException(corruptFileMsg);
            }
            task = new Event(descriptionAndDatetime[0],
                DateTimeParser.getDateTimeFromDataString(descriptionAndDatetime[1]),
                DateTimeParser.getDateTimeFromDataString(descriptionAndDatetime[2]));
        } else {
            throw new DukeException(corruptFileMsg);
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Updates the text file with the latest task list.
     *
     * <p>Currently the text file is overwritten each time it updates.
     * A more efficient method should be implemented.</p>
     *
     * @throws IOException when the duke text file cannot be updated.
     */
    public void updateDukeTextFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));
        writer.write(""); // Overwrites everything
        for (Task task : taskList.getTasks()) {
            writer.append(task.getDataLine()).append("\n");
        }
        writer.close();
    }
}
