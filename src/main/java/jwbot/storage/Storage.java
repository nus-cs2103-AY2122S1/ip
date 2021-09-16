package jwbot.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jwbot.data.TaskList;
import jwbot.data.exception.JwBotException;
import jwbot.data.task.Deadline;
import jwbot.data.task.Event;
import jwbot.data.task.Task;
import jwbot.data.task.Todo;


/**
 * The storage class that reads and writes the txt file.
 *
 * @author  Yim Jaewon
 */
public class Storage {

    private final String filePath;

    /**
     * The constructor of the Storage class.
     *
     * @param filePath the path of the txt file that the tasks will be recorded on
     */
    public Storage(String filePath) {
        assert filePath != null : "filePath is null";
        this.filePath = filePath;
    }

    /**
     * Write the current status of list to the txt file.
     *
     * @param items the list of the tasks
     */
    public void write(TaskList items) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        List<Task> tasks = items.getItems();
        for (Task task : tasks) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                String original = deadline.toString();
                String[] separated = original.split("by: ");
                separated[1] = deadline.getBy() + ")";
                fw.write(String.join("by: ", separated) + "\n");
            } else {
                fw.write(task.toString() + "\n");
            }
        }
        fw.close();
    }

    /**
     * Load the task list from the txt file.
     *
     * @return the loaded list of tasks from the txt file
     */
    public List<Task> load() throws JwBotException {
        List<Task> items = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new JwBotException("Load error, bro!");
            }
        }
        try {
            FileReader filereader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(filereader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                if (line.charAt(1) == 'T') {
                    Todo todo = new Todo(line.substring(7));
                    if (line.charAt(4) == 'X') {
                        todo.markAsDone();
                    }
                    items.add(todo);
                } else if (line.charAt(1) == 'E') {
                    String desAndAt = line.substring(7);
                    String[] separated = desAndAt.split(" \\(at: ");
                    Event event = new Event(separated[0], separated[1].substring(0, separated[1].length() - 1));
                    if (line.charAt(4) == 'X') {
                        event.markAsDone();
                    }
                    items.add(event);
                } else if (line.charAt(1) == 'D') {
                    String desAndBy = line.substring(7);
                    String[] separated = desAndBy.split(" \\(by: ");
                    Deadline deadline =
                            new Deadline(separated[0], separated[1].substring(0, separated[1].length() - 1));
                    if (line.charAt(4) == 'X') {
                        deadline.markAsDone();
                    }
                    items.add(deadline);
                }
            }
            bufReader.close();
        } catch (IOException e) {
            throw new JwBotException("There was a file error, bro!");
        }
        return items;
    }
}
