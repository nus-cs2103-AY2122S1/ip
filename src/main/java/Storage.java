import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;

/** Deals with loading tasks from the file and saving tasks in the file */
public class Storage {
    String filePath;

    /** Constructor */
    public Storage(String filePath) {
        this.filePath = filePath;
    };

    /** Starts up the file reader, creates file if it does not exist. */
    public TaskList load() throws DukeException, IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            Files.createFile(Paths.get(filePath));
        }

        TaskList storedTaskList = new TaskList();
        int index = 1;

        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String unformattedTask = s.nextLine();
            System.out.println(unformattedTask);
            String[] splitTask = unformattedTask.split(" \\| ", 3);
            String command = splitTask[0];
            String isDone = splitTask[1];
            String body = splitTask[2];

            if (command.equals("T")) {
                ToDo t = Parser.validateToDo("todo " + body);
                storedTaskList.add(t);
            } else if (command.equals("D") || command.equals("E")) {
                String[] descAndTime = body.split(" \\| ", 3);

                if (command.equals("D")) {
                    Deadline d = Parser.validateDeadline(
                            "deadline " + descAndTime[0] + " /by " + descAndTime[1]);
                    storedTaskList.add(d);
                } else {
                    Event e = Parser.validateEvent(
                            "event " + descAndTime[0] + " /at " + descAndTime[1]);
                    storedTaskList.add(e);
                }
            }

            if (isDone.equals("1")) {
                Parser.validateDone("done " + index);
            }
            index += 1;
        }
        return storedTaskList;
    }

    /** Write to file. Rewrite everytime there is a done / delete command */
    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /** Append to file. Append when adding a new item*/
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(System.lineSeparator() + textToAppend);
        fw.close();
    }

    /** Rewrites the entire file with the given string. */
    public void rewriteFile(TaskList tasks) throws IOException {
        String req = "";
        for (Task task : tasks.getAll()) {
            if (task instanceof ToDo) {
                req += "T | ";
                req += task.isDone() ? "1 | " : "0 | ";
                req += task.getDescription();
                req += System.lineSeparator();
            } else if (task instanceof Deadline) {
                req += "D | ";
                req += task.isDone() ? "1 | " : "0 | ";
                req += task.getDescription() + " | ";
                req += ((Deadline) task).getDate();
                req += System.lineSeparator();
            } else if (task instanceof Event) {
                req += "E | ";
                req += task.isDone() ? "1 | " : "0 | ";
                req += task.getDescription() + " | ";
                req += ((Event) task).getDate();
                req += System.lineSeparator();
            }
        }
        Storage.writeToFile(filePath, req);
    }

    /** Test */
    public static void run() {
        String filePath = "data/list.txt";
        File f = new File(filePath);
        System.out.println("full path:" + f.getAbsolutePath());
        System.out.println("file exists?:" + f.exists());
        System.out.println("is directory?:" + f.isDirectory());
    }

    public static void main(String[] args) {
    }
}
