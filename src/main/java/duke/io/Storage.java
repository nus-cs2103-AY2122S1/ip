package duke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;
import duke.Duke;
import duke.exception.DukeException;
import duke.task.TaskList;

public class Storage {
    private final File saveFile;

    public Storage(String filepath) {
        this.saveFile = new File(filepath);
    }

    // returns false if there is no file, i.e. new user
    public boolean load() {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            Scanner sc = new Scanner(saveFile);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(",");
                switch (data[0]) {
                    case "t":
                        taskList.add(ToDo.load(data));
                        break;
                    case "d":
                        taskList.add(Deadline.load(data));
                        break;
                    case "e":
                        taskList.add(Event.load(data));
                        break;
                    default:
                        break;
                }
            }
            Duke.taskList = new TaskList(taskList);
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public void save(String taskListContent) throws DukeException {
        try {
            // create the file if it does not exist
            saveFile.createNewFile();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

        try (FileWriter fw = new FileWriter(saveFile)) {
            fw.write(taskListContent);
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
