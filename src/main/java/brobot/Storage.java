package brobot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import brobot.task.Deadline;
import brobot.task.Event;
import brobot.task.Task;
import brobot.task.TaskList;
import brobot.task.Todo;


/**
 * Represents the storage of the Brobot Program. Provides methods to handle the storage.
 */
public class Storage {
    private final String filePath;
    private final File file;

    /**
     * Constructor for the Storage Class.
     *
     * @param filePath The storage file location in the system.
     */
    public Storage(String filePath) {
        //initialize the filePath and file object with the specified filePath
        this.filePath = filePath;
        this.file = new File(filePath);
        //check whether the save file exists in the specified filePath and if not, create it.
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            UI.printToTerm(UI.getErrorText(e));
        }
    }

    /**
     * Reads the list from the storage file and translates it into a TaskList object.
     *
     * @return A TaskList.
     */
    public TaskList readList() {
        TaskList list = new TaskList();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] singleTask = s.nextLine().split(" \\| ", 4);
                processTaskInStorageFormat(singleTask, list);

            }
        } catch (IOException e) {
            UI.printToTerm(UI.getErrorText(e));
        }
        return list;
    }

    /**
     * Parse a single task in storage format and adds it into the task list.
     * @param singleTask The task to be parsed
     * @param list The list to add the task into
     */
    private void processTaskInStorageFormat(String[] singleTask, TaskList list) {
        String taskType = singleTask[0];
        boolean isTaskDone = singleTask[1].equals("1");
        String taskContent = singleTask[2];
        switch (taskType) {
        case "T":
            Task t = new Todo(taskContent);
            if (isTaskDone) {
                t.markComplete();
            }
            list.addTask(t);
            break;
        case "D":
            String deadlineDate = singleTask[3];
            Task d = new Deadline(taskContent, LocalDateTime.parse(deadlineDate,
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            if (isTaskDone) {
                d.markComplete();
            }
            list.addTask(d);
            break;
        case "E":
            String eventDate = singleTask[3];
            Task e = new Event(taskContent, LocalDateTime.parse(eventDate,
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            if (isTaskDone) {
                e.markComplete();
            }
            list.addTask(e);
            break;
        default:
            break;
        }
    }

    /**
     * Writes a TaskList object into the storage file.
     *
     * @param list The TaskList to be written into the file.
     */
    public void writeList(TaskList list) {
        try {
            assert(new File(filePath).exists());
            assert(new File(filePath).canWrite());
            FileWriter fw = new FileWriter(filePath);
            fw.write(list.toStorageString());
            fw.close();
        } catch (IOException e) {
            UI.printToTerm(UI.getErrorText(e));
        }
    }
}
