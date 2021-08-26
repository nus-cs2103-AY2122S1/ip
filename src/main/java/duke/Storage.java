package duke;

import duke.exception.EmptyFieldException;
import duke.exception.InvalidCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents how Duke stores information of its user.
 *
 * @author Javier Phon Zhee Kai.
 * @version CS2103T AY21/22 Sem 1.
 */
public class Storage {

    /** A file that Duke stores information of its user in. */
    private final File file;

    /**
     * Constructor of the Storage class.
     *
     * @param filePath A string representing the filepath which leads to Duke's data file.
     * @throws IOException if any input/output error occurred.
     */
    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        //if data directory doesn't exist create one
        if (!this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdir();
        }
        //if file doesn't exist, create one. If it does, ignore.
        this.file.createNewFile();
    }

    /**
     * Saves user's data to the specified file.
     *
     * @param taskList The taskList where all tasks are stored.
     * @throws IOException if any input/output error occurred.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(this.file);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            String str = taskList.get(i).saveToFile() + "\n";
            sb.append(str);
        }
        fileWriter.write(sb.toString());
        fileWriter.close();
    }

    /**
     * Loads user data from the specified file to the display whenever Duke is activated.
     *
     * @return an ArrayList of user's saved tasks.
     * @throws FileNotFoundException if the specified file cannot be found.
     * @throws EmptyFieldException if the tasks in the taskList have any empty fields.
     * @throws InvalidCommandException if the tasks in the taskList have any invalid fields/
     */
    public ArrayList<Task> load() throws FileNotFoundException, EmptyFieldException, InvalidCommandException {
        // create a Scanner using the File as the source
        Scanner s = new Scanner(this.file);
        ArrayList<Task> temp = new ArrayList<>();
        while (s.hasNext()) {
            Task task = textToTask(s.nextLine());
            temp.add(task);
        }
        return temp;
    }

    /**
     * Returns a task after it has been converted from file string format to display string format.
     *
     * @param fileInput A string representation of a line of text in the specified file.
     * @return A task that has been converted from the fileInput.
     * @throws EmptyFieldException if the task created has empty fields.
     * @throws InvalidCommandException if the task created has invalid fields.
     */
    private Task textToTask(String fileInput) throws EmptyFieldException, InvalidCommandException {
        String[] taskString = fileInput.split("\\|");
        Task temp;
        for (int i = 0; i < taskString.length; i++) {
            taskString[i] = taskString[i].strip();
        }

        if (taskString[0].equals("T")) {
            temp = new ToDo(taskString[2]);
        } else if (taskString[0].equals("E")) {
            temp = new Event(taskString[2], taskString[3]);
        } else {
            temp = new Deadline(taskString[2], taskString[3]);
        }

        if (taskString[1].equals("1")) {
            temp.setIsDone(true);
        }
        return temp;
    }
}
