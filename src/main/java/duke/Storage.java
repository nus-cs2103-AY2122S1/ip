package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static String filePath = "data/list.txt";

    public void save(ArrayList<Task> tasks) {
        FileWriter w, fw;
        try {
            w = new FileWriter(filePath);
            w.write("");
            w.close();

            fw = new FileWriter(filePath);
            for(int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                String taskDesc = t.getType() + (t.isDone() ? " | 1 | " : " | 0 | ") + t.getDetail();
                if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    taskDesc += " | " + d.getTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                } else if (t instanceof Event) {
                    Event e = ((Event) t);
                    taskDesc += " | " + e.getTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                }
                fw.write(taskDesc + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error writing to file : "+ e.getLocalizedMessage());
        }
    }

    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        ArrayList<Task> list = new ArrayList<>();

        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdir();
        } if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error loading file");
            }
        }
        try {
            Scanner sc = new Scanner(f);
            while(sc.hasNextLine()) {
                Task newTask = null;
                //should not have errors if saved in correct format
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                String[] t = sc.nextLine().split("\\|", 4);
                if (t[0].contains("T")) {
                    newTask = new ToDo(t[2].trim());
                } else if (t[0].contains("D")) {
                    newTask = new Deadline(t[2].trim(), LocalDateTime.parse(t[3].trim(), formatter));
                } else if (t[0].contains("E")){
                    newTask = new Event(t[2].trim(), LocalDateTime.parse(t[3].trim(), formatter));
                }

                if (t.length > 1 && t[1].contains("1")) {
                    newTask.markDone();
                }

                if (newTask != null) {
                    list.add(newTask);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found : "+ e.getLocalizedMessage());
        }
        return list;
    }
}
