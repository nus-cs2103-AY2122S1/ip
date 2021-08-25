package duke;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<Task>();
        File f = new File(filePath);
        if (f.exists()){
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String toRead = s.nextLine();
                String[] strSplit = toRead.split(" \\| ");
                if (strSplit[0].equals("T")) {
                    ToDo toDo = new ToDo(strSplit[2]);
                    if (strSplit[1].equals("1")) {
                        toDo.complete();
                    }
                    list.add(toDo);
                } else if (strSplit[0].equals("D")) {
                    String[] dateTime = strSplit[3].split(", ");
                    LocalDate date = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    LocalTime time = LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("hh:mm a"));
                    Deadline deadline = new Deadline(strSplit[2], date, time);
                    if (strSplit[1].equals("1")) {
                        deadline.complete();
                    }
                    list.add(deadline);
                } else if (strSplit[0].equals("E")) {
                    String[] dateTime = strSplit[3].split(", ");
                    LocalDate date = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    LocalTime time = LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern("hh:mm a"));
                    Event event = new Event(strSplit[2], date, time);
                    if (strSplit[1].equals("1")) {
                        event.complete();
                    }
                    list.add(event);
                }
            }
            s.close();
        } else {
            throw new FileNotFoundException();
        }
        return list;
    }

    public void write(TaskList tasks) throws IOException {
        File toWrite = new File("../../../data");
        if (!toWrite.exists()) {
            toWrite.mkdir();
        }
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            fw.write(t.getToWrite());
            fw.write(System.getProperty("line.separator"));
        }
        fw.close();
    }
}