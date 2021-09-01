package duke;

import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {
    String filePath;
    PrintWriter writer;
    TaskList ls;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void rewriteFile(TaskList ls) {
        this.ls = ls;
        try {
            FileWriter fw = new FileWriter(filePath, false);
            writer = new PrintWriter(fw);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < ls.getSize(); i++) {
            Task task = ls.getTask(i);
            String type = task.getType();
            String desc = task.getDesc();
            String addOns = task.addOns();
            if (type == "todo") {
                writer.println("T" + (task.isDone() ? " | 1 | " : " | 0 | ") + desc);
            } else if (type == "deadline") {
                writer.println("D" + (ls.getTask(i).isDone() ? " | 1 | " : " | 0 | ")
                        + desc + " | " + addOns);
            } else if (type == "event") {
                writer.println("E" + (task.isDone() ? " | 1 | " : " | 0 | ")
                        + desc + " | " + addOns);
            }
        }
        writer.close();
    }

    public TaskList load() throws IOException, DukeException {
        TaskList ls = new TaskList();
        File directory = new File("duke.txt");
        if (!directory.exists()) {
            directory.mkdir();
        }
        File data = new File(filePath);
        data.createNewFile();
        Scanner s = new Scanner(data);
        while (s.hasNext()) {
            ls.addTask(parseTask(s.nextLine()));
        }
        return ls;
    }

    public Task parseTask(String input) throws DukeException {
        if (input.startsWith("T")) {
            String taskDesc = input.substring(7);
            Todo tTask = new Todo(taskDesc);
            return tTask;
        } else if (input.startsWith("D")) {
            String taskDesc = input.substring(7);
            String taskDate = getDate(input);
            Deadline dTask = new Deadline(taskDesc, taskDate);
            return dTask;
        } else {
            String taskDesc = input.substring(7);
            String taskDate = getDate(input);
            Event eTask = new Event(taskDesc, taskDate);
            return eTask;
        }
    }

    public String getDate(String input) {
        int endIndex = 0;
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '|') {
                if (count == 3) {
                    endIndex = i;
                }
            }
        }
        return input.substring(endIndex);
    }

}
