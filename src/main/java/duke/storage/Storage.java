package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private File file;
    
    public Storage(String path) {
        try {
            this.file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String [] split = line.split(" \\| ");
                boolean isDone = Integer.parseInt(split[1]) == 1;
                switch (split[0]) { 
                    case "T":
                        tasks.add(new ToDo(split[2], isDone));
                        break;
                    case "D":
                        tasks.add(new Deadline(split[2], LocalDate.parse(split[3]), isDone));
                        break;
                    case "E":
                        tasks.add(new Event(split[2], LocalDate.parse(split[3]), isDone));
                        break;
                }
            }
        } catch (Exception e) {
            throw new DukeException("Unable to read duke.txt");
        }
        return tasks;
    }
    
    public void write(List<Task> tasks) {
        try {
            PrintWriter writer = new PrintWriter(new FileOutputStream(file));
            for (Task t : tasks) {
                writer.println(t.toDataString());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
