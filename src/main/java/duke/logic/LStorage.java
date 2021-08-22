package duke.logic;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Deals with the logic of writing to the dukedata.txt file each time there is an update to the task list.
 */
public class LStorage {
    private final String filePath;
    private final TaskList taskList;

    public LStorage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        this.taskList = taskList;
    }

    public void updateDukeTextFile() {
        File textFile = new File(filePath);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(textFile));
            writer.write(""); // Overwrites everything
            for (Task task : taskList.getTasks()) {
                writer.append(task.getDataLine()).append("\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.printf("However an error occurred while writing to %s:\n", textFile.getAbsolutePath());
            e.printStackTrace();
        }
    }
}
