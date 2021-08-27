package Duke.Storage;

import Duke.Exceptions.DukeException;
import Duke.Exceptions.NoPreviousFileException;
import Duke.Tasks.*;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    String path;
    public Storage(String path) {
        this.path = path;
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("./tasks.txt"));
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.equals("Tasks:")) {
                    Task task = convertToTask(line);
                    list.add(task);
                }
            }
            br.close();
        } catch (IOException e1) {
            File file = new File("./tasks.txt");
            try {
                file.createNewFile();
            } catch (IOException e2) {
                System.out.println("Error creating txt file");
            }
            throw new NoPreviousFileException("No previous file detected, creating new one.");
        }

        return list;
    }

    public static Task convertToTask(String output) {
        char type = output.charAt(1);
        char done = output.charAt(4);
        String taskAndDate = output.substring(7);

        Task task;
        if (type == 'T') {
            task = new Todo(taskAndDate);
        } else if (type == 'D') {
            // Deadlines
            if (taskAndDate.contains("(by: ")) {
                String name = taskAndDate.split(" \\(by: ")[0];
                String date = taskAndDate.split(" \\(by: ")[1];

                // Chop of last ")"
                date = date.substring(0, date.length()-1);
                task = new Deadline(name, " " + date);
            } else {
                task = new Deadline(taskAndDate, "");
            }
        } else {
            // Events
            if (taskAndDate.contains("(at: ")) {
                String name = taskAndDate.split(" \\(at: ")[0];
                String date = taskAndDate.split(" \\(at: ")[1];

                // Chop of last ")"
                date = date.substring(0, date.length()-1);
                task = new Event(name, " " + date);
            } else {
                task = new Event(taskAndDate, "");
            }
        }
        task.setType(type);
        if (done == 'X') {
            task.setDone();
        }
        return task;
    }

    public void updateTxtFile(TaskList tasklist) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter("./tasks.txt"));
            for (Task t : tasklist.getTaskList()) {
                if (t.getType() == 'D') {
                    Deadline dl = (Deadline) t;
                    bw.write(dl + "\n");
                } else if (t.getType() == 'E') {
                    Event e = (Event) t;
                    bw.write(e + "\n");
                } else {
                    bw.write(t + "\n");
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error reading and writing file.");
        }
    }
}
