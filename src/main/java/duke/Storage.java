package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class that load and writes the tasks.
 */

public class Storage {
    private static String pathName;

    /**
     * Initializes a Storage object.
     * @param pathName   The path to the directory of the file.
     */
    public Storage(String pathName) {
        this.pathName = pathName;

    }

    /**
     * Loads the files and returns an ArrayList of the tasks stored.
     * @return ArrayList of type Task
     * @throws DukeException
     * @throws IOException
     */
    public ArrayList<Task> loadTasks() throws DukeException, IOException {
        File f = new File(pathName);
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
               Files.createDirectories(Paths.get("data/"));
               f.createNewFile();
        }
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String task = sc.nextLine();
            String[] taskArr = task.split("///");
            if (taskArr[0].equals("T")) {
                Todo td = new Todo(taskArr[2]);
                if (taskArr[1].equals("1")) {
                    td.markAsDone();
                }
                taskList.add(td);
            }

            if (taskArr[0].equals("D")) {
                System.out.println(taskArr[3]);
                Deadline d = new Deadline(taskArr[2], taskArr[3]);
                if (taskArr[1].equals("1")) {
                    d.markAsDone();
                }
                taskList.add(d);
            }

            if (taskArr[0].equals("E")) {
                Event e = new Event(taskArr[2], taskArr[3]);
                if (taskArr[1].equals("1")) {
                    e.markAsDone();
                }
                taskList.add(e);
            }
        }
        return taskList;
    }

    /**
     * Updates the current task list with tasks inputted.
     * @param tasks
     * @throws IOException
     */
    public static void writeFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(pathName);
        ArrayList<Task> task = tasks.getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            Task t = task.get(i);
            assert t.label.equals("T") || t.label.equals("D") || t.label.equals("E")
                    : "Task type appears to be invalid.";
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

