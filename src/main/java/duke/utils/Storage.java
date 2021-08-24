package duke.utils;

import duke.exceptions.DukeException;
import duke.tasks.Task;
import duke.tasks.Deadline;
import duke.tasks.Todo;
import duke.tasks.Event;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        File data = new File(this.filePath);
        List<Task> tasks = new ArrayList<>();

        try {
            data.createNewFile();
            Scanner reader = new Scanner(data);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] info = line.split(" \\| ");
                if (info.length == 1) {
                    continue;
                }
                String command = info[0];
                Task task = null;
                boolean done = info[1].equals("1");
                if (command.equals("T")) {
                    task = new Todo(info[2]);
                } else if (command.equals("D")) {
                    task = new Deadline(info[2], info[3]);
                } else if (command.equals("E")) {
                    task = new Event(info[2], info[3]);
                }
                if (task != null) {
                    if (done) {
                        task.markDone();
                    }
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return tasks;
    }

    public void save(TaskList tasks) {
        // TODO
        String data = tasks.getData();
        try (PrintWriter out = new PrintWriter(this.filePath)) {
            out.println(data);
            out.close();
        } catch (IOException e) {
            // will never occur since the file will always be created first
        }
    }
}
