package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

/**
 * Stores the task.
 */
public class Storage {

    private final String filePath;

    /**
     * Creates storage with the file path provided.
     * @param filePath The file path where all the tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the text file and creates a text file if file does not exist.
     * @return A array list with all the tasks read from the file.
     */
    public ArrayList<Task> loadFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File file = new File(this.filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } else {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while (line != null) {
                    char type = line.charAt(0);
                    String[] splitInput = line.split(" \\| ");
                    if (type == 'T') {
                        taskList.add(new Todo(splitInput[2], splitInput[1].equals("1")));
                    } else if (type == 'D') {
                        LocalDate date = LocalDate.parse(splitInput[3], DateTimeFormatter.ofPattern("d/M/yyyy"));
                        LocalTime time = splitInput.length == 5 ? LocalTime.parse(splitInput[4]) : null;
                        taskList.add(new Deadline(splitInput[2], splitInput[1].equals("1"), date, time));
                    } else if (type == 'E') {
                        LocalDate date = LocalDate.parse(splitInput[3], DateTimeFormatter.ofPattern("d/M/yyyy"));
                        LocalTime time = splitInput.length == 5 ? LocalTime.parse(splitInput[4]) : null;
                        taskList.add(new Event(splitInput[2], splitInput[1].equals("1"), date, time));
                    }
                    line = br.readLine();
                }
            }
            return taskList;
        } catch (IOException e) {
            e.printStackTrace();
            return taskList;
        }
    }

    /**
     * Updates the tasks given to the file.
     * @param taskList The tasks that will be updated to the file.
     */
    public void updateTasks(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getAllTasks();
        try {
            FileWriter writer = new FileWriter(this.filePath);
            ArrayList<String> displayTasks = new ArrayList<>();
            for (Task task : tasks) {
                displayTasks.add(task.fileFormat());
            }
            writer.write(String.join(String.format("%n"), displayTasks));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
