package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents a storage which stores the Task List.
 * The storage is read at when the bot starts up and written into when the bot is terminated.
 */

public class Storage {
    private String filePath;
    private final String WRITEPATH = "./data";
    private final String DATEFORMAT = "MMM dd yyyy";
    private final String TIMEFORMAT = "hh:mm a";

    /**
     * Creates a Storage Object containing the file path of where the user wishes to store the TaskList.
     *
     * @param filePath file path of Storage.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads and loads the storage into an ArrayList of Tasks.
     *
     * @return ArrayList of tasks stored in the storage.
     * @throws FileNotFoundException if file path does not exist.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        return parseFile(f);
    }

    /**
     * Writes the TaskList into a text file that is then stored in the file path.
     *
     * @param tasks TaskList of tasks to be written into the text file
     * @throws IOException On Output Error.
     */
    public void write(TaskList tasks) throws IOException {
        File toWrite = new File(WRITEPATH);
        checkFilePath(toWrite);
        FileWriter fw = new FileWriter(filePath);
        writeToFile(fw, tasks);
        fw.close();
    }

    private ArrayList<Task> parseFile(File f) throws FileNotFoundException {
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(f);
        return retrieveList(s);
    }

    private ArrayList<Task> retrieveList(Scanner s) {
        ArrayList<Task> list = new ArrayList<Task>();
        addToList(s, list);
        s.close();
        return list;
    }

    private void addToList(Scanner s, ArrayList<Task> list) {
        while (s.hasNext()) {
            String toRead = s.nextLine();
            list.add(parseLine(toRead));
        }
    }

    private Task parseLine(String toRead) {
        String[] strSplit = toRead.split(" \\| ");
        return createTask(strSplit);
    }

    private Task createTask(String[] strSplit) {
        if (strSplit[0].equals("T")) {
            return createToDo(strSplit);
        } else if (strSplit[0].equals("D")) {
            return createDeadline(strSplit);
        }
        return createEvent(strSplit);
    }

    private Task createToDo(String[] strSplit) {
        ToDo toDo = new ToDo(strSplit[2]);
        return checkCompletion(toDo, strSplit);
    }

    private Task createDeadline(String[] strSplit) {
        Task deadline = parseCommand("Deadline", strSplit);
        return checkCompletion(deadline, strSplit);
    }

    private Task createEvent(String[] strSplit) {
        Task event = parseCommand("Event", strSplit);
        return checkCompletion(event, strSplit);
    }

    private Task parseCommand(String type, String[] strSplit) {
        String[] dateTime = strSplit[3].split(", ");
        String frequency = strSplit[4];
        LocalDate date = LocalDate.parse(dateTime[0], DateTimeFormatter.ofPattern(DATEFORMAT));
        LocalDate updatedDate = checkDate(date, frequency);
        boolean isUpdated = !date.isEqual(updatedDate);
        LocalTime time = LocalTime.parse(dateTime[1], DateTimeFormatter.ofPattern(TIMEFORMAT));
        String task = strSplit[2];
        return createTaskWithDateTime(type, task, updatedDate, time, frequency, isUpdated);
    }

    private Task createTaskWithDateTime(String type, String task, LocalDate date, LocalTime time, String frequency,
                                        boolean isUpdated) {
        if (type.equals("Deadline")) {
            return new Deadline(task, date, time, frequency, isUpdated);
        }
        return new Event(task, date, time, frequency, isUpdated);
    }

    private Task checkCompletion(Task task, String[] strSplit) {
        if (strSplit[1].equals("1")) {
            task.complete();
        }
        return task;
    }

    private LocalDate checkDate(LocalDate date, String frequency) {
        if (date.compareTo(LocalDate.now()) > 0) {
            return date;
        }
        return updateDate(date, frequency);
    }

    private LocalDate updateDate(LocalDate date, String frequency) {
        if (frequency.equals("weekly")) {
            return date.plusWeeks(1);
        } else if (frequency.equals("monthly")) {
            return date.plusMonths(1);
        }
        return date;
    }

    private void checkFilePath(File f) {
        if (!f.exists()) {
            f.mkdir();
        }
    }

    private void writeToFile(FileWriter fw, TaskList tasks) throws IOException {
        for (int i = 0; i < tasks.getSize(); i++) {
            Task t = tasks.get(i);
            writeTasks(fw, t);
        }
    }

    private void writeTasks(FileWriter fw, Task t) throws IOException {
        fw.write(t.getToWrite());
        fw.write(System.getProperty("line.separator"));
    }
}