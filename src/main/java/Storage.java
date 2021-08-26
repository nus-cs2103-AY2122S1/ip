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

/**
 * Represents a storage which stores the Task List.
 * The storage is read at when the bot starts up and written into when the bot is terminated.
 */

public class Storage {
    private String filePath;

    /**
     * Creates a Storage Object containing the file path of where the user wishes to store the TaskList.
     * @param filePath file path of Storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads and loads the storage into an ArrayList of Tasks.
     * @return ArrayList of tasks stored in the storage.
     * @throws FileNotFoundException if file path does not exist.
     */

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

    /**
     * Writes the TaskList into a text file that is then stored in the file path.
     * @param tasks TaskList of tasks to be written into the text file
     * @throws IOException On Output Error.
     */

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