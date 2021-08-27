package duke.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.Duke;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

public class Storage {
    private final File saveFile;

    public Storage(String filepath) {
        this.saveFile = new File(filepath);
    }

    /**
     * Loads the taskList from save.csv
     *
     * @return false if the file does not exist, i.e. the user is a new user. true otherwise.
     */
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
            Duke.setTaskList(new TaskList(tasks));
            return true;
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    /**
     * Saves the taskList into save.csv
     *
     * @param taskListContent The formatted string representing the tasks in the task list
     * @throws DukeException Any exception caught that has to do with the I/O
     */
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
