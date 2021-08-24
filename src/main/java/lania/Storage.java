package lania;

import lania.task.Task;
import lania.task.TaskList;
import lania.task.Deadline;
import lania.task.Event;
import lania.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    public String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList load() throws IOException {
        Files.createDirectories(Paths.get("data/"));
        File f = new File(filePath);
        if (f.createNewFile()) {
            return new TaskList();
        } else {
            TaskList taskList = new TaskList();
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String next = s.nextLine();
                String[] split = next.split("\\|", 4);
                Task t;
                if (split[0].equals("T")) {
                    t = new Todo(split[2]);
                } else if (split[0].equals("D")) {
                    t = new Deadline(split[2], split[3]);
                } else {
                    t = new Event(split[2], split[3]);
                }
                if (split[1].equals("X")) {
                    t.markAsDone();
                }
                taskList.update(t);
            }
            s.close();
            return taskList;
        }
    }

    public void save(TaskList taskArrayList) throws IOException{
        for (int i = 0; i < taskArrayList.size(); i++) {
            Task task = taskArrayList.get(i);
            appendToFile(filePath, task.getStringFormat(), i);
        }
    }

    private void appendToFile(String filePath, String textToAppend, int i) throws IOException {
        FileWriter fw = new FileWriter(filePath, i != 0);
        fw.write(textToAppend);
        fw.close();
    }
}
