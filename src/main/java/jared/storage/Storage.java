package jared.storage;

import jared.common.DukeException;
import jared.parser.Parser;
import jared.task.Deadline;
import jared.task.Event;
import jared.task.Task;
import jared.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }
    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(fileName);

        try {
            f.createNewFile();
            Scanner reader = new Scanner(f);
            while (reader.hasNext()) {
                String next = reader.nextLine();
                String type = Parser.parseData(next, "type");
                String progress = Parser.parseData(next, "progress");
                String description = Parser.parseData(next, "description");

                Task t;
                String dateStr;
                LocalDate date;
                LocalTime time;

                switch (type) {
                case "T":
                    t = new Todo(description);
                    break;
                case "D":
                    dateStr = Parser.parseData(next, "date");
                    date = LocalDate.parse(dateStr);
                    try {
                        String timeStr = Parser.parseData(next, "time");
                        time = LocalTime.parse(timeStr);
                        t = new Deadline(description, date, time);
                    } catch (DukeException e) {
                        t = new Deadline(description, date);
                    }
                    break;
                case "E":
                    dateStr = Parser.parseData(next, "date");
                    date = LocalDate.parse(dateStr);
                    try {
                        String timeStr = Parser.parseData(next, "time");
                        time = LocalTime.parse(timeStr);
                        t = new Event(description, date, time);
                    } catch (DukeException e) {
                        t = new Event(description, date);
                    }
                    break;
                default:
                    System.out.println("task failed to load");
                    continue;
                }

                if (progress.equals("1")) {
                    t.markDone();
                }
                tasks.add(t);
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error");
        }
    }

    public void saveData(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(fileName);
            String res = "";
            for (Task t : tasks) {
                res += (t.saveFormat() + "\n");
            }
            fw.write(res);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
