package duke;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String filePath;

    /**
     * Constructor for a Storage instance.
     * @param filePath file path for file to save/load save data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves TaskList to file.
     * @param taskList TaskList to be saved.
     */
    public void save(TaskList taskList) throws IOException {
        StringBuilder saveData = new StringBuilder();
        FileWriter fw;
        for (int i = 1; i < taskList.getSize() + 1; i++) {
            saveData.append(taskList.get(i).formatForFile());
        }

        File file = new File(filePath);
        file.createNewFile();
        fw = new FileWriter(file);
        fw.write(saveData.toString());
        fw.close();

    }

    /**
     * Creates a new TaskList by reading save data from a Storage object's file path.
     * @return new TaskList with Tasks from save data.
     * @throws FileNotFoundException if no save data file exists/is found.
     */
    public TaskList load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        TaskList taskList = new TaskList();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split("\\" + Task.SAVE_DATA_MARKER);
            String taskType = line[0];
            boolean isDone = Integer.parseInt(line[1]) != 0;
            String taskName = line[2];
            switch (taskType) {
                case "T":
                    taskList.add(new TodoTask(taskName, isDone));
                    break;
                case "E":
                    taskList.add(new EventTask(taskName, isDone, line[3], line[4]));
                    break;
                case "D":
                    taskList.add(new DeadlineTask(taskName, isDone, line[3]));
                    break;
                default:
                    break;
            }
        }
        return taskList;
    }
}
