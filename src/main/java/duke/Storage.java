package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;
    protected File taskList;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskList = new File(filePath);
    }

    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            if (!this.taskList.createNewFile()) {
                Scanner fileReader = new Scanner(this.taskList);
                while (fileReader.hasNextLine()) {
                    String line = fileReader.nextLine();
                    String[] splitString = line.split(" \\| ");
                    if (splitString[0].equals("T")) {
                        Todo t = new Todo(line.substring(8));
                        if (line.substring(4, 5).equals("1")) {
                            t.markAsDone();
                        }
                        list.add(t);
                    } else if (splitString[0].equals("D")) {
                        Deadline d = new Deadline(splitString[2], splitString[3]);
                        if (splitString[1].equals("1")) {
                            d.markAsDone();
                        }
                        list.add(d);
                    } else {
                        Event e = new Event(splitString[2], splitString[3]);
                        if (splitString[1].equals("1")) {
                            e.markAsDone();
                        }
                        list.add(e);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return list;
    }

    public static void save(ArrayList<Task> list, String filePath) {
        File taskList = new File(filePath);
        taskList.delete();
        try {
            FileWriter writer = new FileWriter(filePath);
            for (int i = 0; i < list.size(); i++) {
                File tasks = new File(filePath);
                Task t = list.get(i);
                String isDone = t.isDone ? "1" : "0";
                if (t instanceof Todo) {
                    String line = "T | " + isDone + " | " + t.description;
                    writer.write(line + "\n");
                } else if (t instanceof Deadline) {
                    Deadline d = (Deadline) t;
                    String line = "D | " + isDone + " | " + t.description + " | " + d.by;
                    writer.write(line + "\n");
                } else if (t instanceof Event) {
                    Event e = (Event) t;
                    String line = "E | " + isDone + " | " + t.description + " | " + e.at;
                    writer.write(line + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
