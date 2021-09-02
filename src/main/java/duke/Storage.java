package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates the storage class.
 */
public class Storage {
    private final String filePath;
    private final File file;

    public Storage(String path) {
        this.filePath = path;
        this.file = new File(path);
    }

    private Task toTask(String fileRecord) {
        Task t = null;
        String[] params = fileRecord.split(" \\| ");
        String type = params[0];
        boolean isDone = params[1].equals("1");
        String desc = params[2];
        switch (type) {
        case ("T"):
            t = new ToDo(desc, isDone);
            break;
        case ("E"):
            t = new Event(desc, isDone, params[3]);
            break;
        case ("D"):
            t = new Deadline(desc, isDone, params[3]);
            break;
        }
        return t;
    }

    /**
     * Loads user's saved data from file.
     *
     * @return A TaskList object representing user's tasks.
     * @throws IOException If there is error reading from the file.
     */
    public TaskList loadTasksFromFile() throws IOException {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            ArrayList<Task> tasks = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                tasks.add(toTask(line));
            }
            fileReader.close();
            return new TaskList(tasks);
    }

    /**
     * Writes new data to file on exit.
     *
     * @param tasks List of tasks to be saved.
     */
    public void writeTasksToFile(List<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            StringBuilder fileRecords = new StringBuilder();
            for (Task task : tasks) {
                fileRecords.append(task.toFileRecord());
                fileRecords.append("\n");
            }
            fileWriter.write(fileRecords.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(Ui.format(e.toString()));
        }
    }
}
