package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String pathName;

    public Storage(String pathName) {
        this.pathName = pathName;

    }

    public ArrayList<Task> loadTasks() throws DukeException, IOException {
        File f = new File(pathName);
        ArrayList<Task> t = new ArrayList<>();
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String task = sc.nextLine();
                String[] taskArr = task.split("//|");
                if (taskArr[0].equals("T")) {
                    Todo td = new Todo(taskArr[2]);
                    if (taskArr[1].equals("1")) {
                        td.markAsDone();
                    }
                    t.add(td);
                }
                if (taskArr[0].equals("D")) {
                    Deadline d = new Deadline(taskArr[2], taskArr[3]);
                    if (taskArr[1].equals("1")) {
                        d.markAsDone();
                    }
                    t.add(d);
                }
                if (taskArr[0].equals("E")) {
                    Event e = new Event(taskArr[2], taskArr[3]);
                    if (taskArr[1].equals("1")) {
                        e.markAsDone();
                    }
                    t.add(e);
                }
            }
        } catch(IOException e){
            System.out.println("OOPS!!! Directory not found. " + e.getMessage());
            System.exit(0);
        }
        return t;
    }

    public static void writeFile( TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(pathName);
        ArrayList<Task> task = tasks.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = task.get(i);
            String text = "";
            if (t.label.equals("T")) {
                text = text.concat("T|");
                if (t.isDone) {
                    text = text.concat("1|");
                } else {
                    text = text.concat("0|");
                }
                text = text.concat(t.description + "\n");
            }
            if (t.label.equals("D")) {
                text = text.concat("D|");
                if (t.isDone) {
                    text = text.concat("1|");
                } else {
                    text = text.concat("0|");
                }
                text = text.concat(t.description + "|" + ((Deadline) t).time + "\n");
            }
            if (t.label.equals("E")) {
                text = text.concat("E|");
                if (t.isDone) {
                    text = text.concat("1|");
                } else {
                    text = text.concat("0|");
                }
                text = text.concat(t.description + "|" + ((Event) t).by + "\n");
            }
            fw.write(text);
        }
        fw.close();
    }
}

