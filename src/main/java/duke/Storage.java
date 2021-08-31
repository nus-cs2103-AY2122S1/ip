package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws DukeException {
        File f = new File(filePath);
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException ffe) {
            throw new DukeException("Record not found in " + filePath);
        }
        ArrayList<Task> tasks = new ArrayList<>();
        while (s.hasNext()) {
            String description = s.nextLine();
            tasks.add(Parser.convertRecordToTask(description));
        }

        return tasks;

    }

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

