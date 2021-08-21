package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Storage {
    protected File f;

    public Storage(String filePath) {
        this.f = new File(filePath);
    }

    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> result = new ArrayList<>();
        // if the directory "data" does not exist, then create the directory
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        // check if "duke.txt" exists. If yes, load the info to taskList. Else, create the file.
        if (!f.createNewFile()) {
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] line = s.nextLine().split(" \\| ");
                Task t;
                switch(line[0]) {
                    case "T":
                        t = new Todo(line[2]);
                        break;
                    case "D":
                        t = new Deadline(line[2], LocalDate.parse(line[3]));
                        break;
                    case "E":
                        t = new Event(line[2], LocalDate.parse(line[3]));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + line[0]);
                }
                if (line[1].equals("1")) {
                    t.markAsDone();
                }
                result.add(t);
            }
        }
        return result;
    }

    public void store(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(f);
        for (Task t : tasks.tasks) {
            String line = "";
            if (t instanceof Todo) {
                line += String.format("T | %d | %s\n", t.isDone ? 1 : 0, t.description);
            } else if (t instanceof Event) {
                line += String.format("E | %d | %s | %s\n", t.isDone ? 1 : 0, t.description, ((Event)t).at);
            } else if (t instanceof Deadline) {
                line += String.format("D | %d | %s | %s\n", t.isDone ? 1 : 0, t.description, ((Deadline)t).by);
            }
            fw.write(line);
        }
        fw.close();
    }
}
