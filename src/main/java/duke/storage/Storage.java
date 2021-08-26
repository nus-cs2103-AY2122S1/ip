package duke.storage;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
        File f = new File(filePath);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
        }
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            ArrayList<Task> tasks = new ArrayList<Task>();
            while (s.hasNext()) {
                String item = s.nextLine();
                String[] details = item.split(",");
                boolean isDone = Boolean.parseBoolean(details[1]);
                switch (details[0]) {
                case "T":
                    tasks.add(new ToDo(details[2], isDone));
                    break;
                case "D":
                    tasks.add(new Deadline(details[2], details[3], isDone));
                    break;
                case "E":
                    tasks.add(new Event(details[2], details[3], isDone));
                    break;
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        }

    }

    public void write(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task item : tasks.getList()) {
                String toAdd = item.getText();
                fw.write(toAdd);
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
