package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;


public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads tasks from filePath and returns as a list
     * @return An ArrayList of tasks
     * @throws DukeException
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        Scanner s;
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
            s = new Scanner(f);
        } catch (FileNotFoundException ffe) {
            throw new DukeException("Record not found in " + filePath);
        } catch (IOException ioe) {
            throw new DukeException(ioe.getMessage());
        }

        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String description = s.nextLine();
            tasks.add(Parser.convertRecordToTask(description));
        }

        return tasks;

    }

    /**
     * Stores tasks in taskList to filePath
     * @param taskList taskList containing all tasks
     * @throws DukeException
     */
    public void saveData(TaskList taskList) throws DukeException {
        ArrayList<Task> lst = taskList.getTasks();
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : lst) {
                fw.write(t.encoding() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to the file");
        }
    }

}

