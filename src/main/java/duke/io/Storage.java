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
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(saveFile);
            while (sc.hasNextLine()) {
                String[] datas = sc.nextLine().split(",");
                switch (datas[0]) {
                    case "t":
                        tasks.add(ToDo.load(datas));
                        break;
                    case "d":
                        tasks.add(Deadline.load(datas));
                        break;
                    case "e":
                        tasks.add(Event.load(datas));
                        break;
                    default:
                        break;
                }
            }
            Duke.taskList = new TaskList(tasks);
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
