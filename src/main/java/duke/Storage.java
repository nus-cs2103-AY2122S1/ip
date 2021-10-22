package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;


/**
 * Storage saves tasks added by user and save it in a file as indicated by filepath.
 */
public class Storage {
    private static final int START_OF_TASK_DES = 8;
    private static final int TASK_TYPE_INDEX = 0;
    private static final int TASK_STATUS_INDEX = 4;
    private final String filePath;

    /**
     * Constructs an instance of Storage which saves data for Duke.
     *
     * @param filePath directory of saved memory for Duke.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Creates new file at filePath if the file does not exist.
     *
     * @return File to write
     */
    private File initStorageFile() {
        String dirName = filePath.split("/Duke.txt")[0];
        File directory = new File(dirName);
        directory.mkdir();
        File storageFile = new File(filePath);
        try {
            storageFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error has occurred.");
        }
        return storageFile;
    }

    private Event decodeEvent(String encodedEvent) {
        int taskDescIdentifier = encodedEvent.indexOf('|', START_OF_TASK_DES);
        char taskStatus = encodedEvent.charAt(TASK_STATUS_INDEX);
        String date = encodedEvent.substring(taskDescIdentifier + 2);
        String taskDesc = encodedEvent.substring(START_OF_TASK_DES, taskDescIdentifier - 1);
        Event event = new Event(taskDesc, date);
        if (taskStatus == '1') {
            event.completeTask();
        }
        return event;
    }

    private Deadline decodeDeadline(String encodedDeadline) {
        int taskDescIdentifier = encodedDeadline.indexOf('|', START_OF_TASK_DES);
        char taskStatus = encodedDeadline.charAt(TASK_STATUS_INDEX);
        String date = encodedDeadline.substring(taskDescIdentifier + 2);
        String taskDesc = encodedDeadline.substring(START_OF_TASK_DES, taskDescIdentifier - 1);
        Deadline deadline = new Deadline(taskDesc, date);
        if (taskStatus == '1') {
            deadline.completeTask();
        }
        return deadline;
    }

    private ToDo decodeToDo(String encodeToDo) {
        int taskDescIdentifier = encodeToDo.indexOf('|', START_OF_TASK_DES);
        char taskStatus = encodeToDo.charAt(TASK_STATUS_INDEX);
        String taskDesc = encodeToDo.substring(START_OF_TASK_DES, taskDescIdentifier - 1);
        ToDo toDo = new ToDo(taskDesc);
        if (taskStatus == '1') {
            toDo.completeTask();
        }
        return toDo;
    }

    /**
     * Reads from storage file all the previously saved tasks info and add all the tasks to the current tracking list.
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        try {
            Scanner s = new Scanner(initStorageFile());
            while (s.hasNext()) {
                String line = s.nextLine();
                char taskType = line.charAt(TASK_TYPE_INDEX);
                if (taskType == 'E') {
                    Event event = decodeEvent(line);
                    tasks.addTask(event);
                } else if (taskType == 'D') {
                    Deadline deadline = decodeDeadline(line);
                    tasks.addTask(deadline);
                } else if (taskType == 'T') {
                    ToDo toDo = decodeToDo(line);
                    tasks.addTask(toDo);
                }
            }
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    /**
     * Overwrites and saves new task list in data/duke.txt file when there is changes such as deleting and adding task.
     */
    public void saveData(List<String> encodedTasks) {
        try {
            FileWriter myWriter = new FileWriter(filePath);
            for (String encodedTask : encodedTasks) {
                myWriter.write(encodedTask + '\n');
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
