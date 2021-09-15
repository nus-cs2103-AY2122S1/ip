package duke;

import duke.DukeException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                Task task = Parser.parseData(line);
                if (task != null) {
                    list.add(task);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            throw new DukeException(DukeException.Type.LOADING);
        }
        return list;
    }

    public void writeAll(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : tasks.list) {
                writer.write(task.toWrite());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
