package jared.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import jared.common.DukeException;
import jared.parser.Parser;
import jared.task.Deadline;
import jared.task.Event;
import jared.task.Task;
import jared.task.Todo;

/**
 * Deals with data from the file.
 */
public class Storage {
    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Loads data from the file.
     * @return List of tasks from the file.
     * @throws DukeException if file cannot be processed.
     */
    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(fileName);

        try {
            f.createNewFile();
            Scanner reader = new Scanner(f);
            while (reader.hasNext()) {
                String dataFromStorage = reader.nextLine();
                String progress = Parser.parseData(dataFromStorage, "progress");
                Task t;

                t = processTask(dataFromStorage);

                if (isDone(progress)) {
                    t.markDone();
                }
                tasks.add(t);
            }
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error");
        }
    }

    /**
     * Processes data from storage into new Tasks
     * @param dataFromStorage data loaded from file
     * @return Task created
     * @throws DukeException if data cannot be parsed
     */
    public Task processTask(String dataFromStorage) throws DukeException {
        String type = Parser.parseData(dataFromStorage, "type");
        Task t;
        switch (type) {
        case "T":
            String description = Parser.parseData(dataFromStorage, "description");
            t = new Todo(description);
            break;
        case "D":
            t = createNewDeadline(dataFromStorage);
            break;
        case "E":
            t = createNewEvent(dataFromStorage);
            break;
        default:
            throw new DukeException("Task failed to load");
        }
        return t;
    }

    /**
     * Saves data to the file.
     * @param tasks List of tasks to be saved to the file.
     */
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

    /**
     * Creates a new deadline task from data loaded from file.
     * @param dataFromStorage data loaded from file
     * @return Deadline task created
     * @throws DukeException if data cannot be parsed
     */

    public Deadline createNewDeadline(String dataFromStorage) throws DukeException {
        String dateStr = Parser.parseData(dataFromStorage, "date");
        String description = Parser.parseData(dataFromStorage, "description");
        LocalDate date = LocalDate.parse(dateStr);
        Deadline t;

        try {
            String timeStr = Parser.parseData(dataFromStorage, "time");
            LocalTime time = LocalTime.parse(timeStr);
            t = new Deadline(description, date, time);
        } catch (DukeException e) {
            t = new Deadline(description, date);
        }
        return t;
    }

    /**
     * Creates a new event task from data loaded from file.
     * @param dataFromStorage data loaded from file
     * @return Event task created
     * @throws DukeException if data cannot be parsed
     */
    public Event createNewEvent(String dataFromStorage) throws DukeException {
        String dateStr = Parser.parseData(dataFromStorage, "date");
        String description = Parser.parseData(dataFromStorage, "description");
        LocalDate date = LocalDate.parse(dateStr);
        Event t;
        try {
            String timeStr = Parser.parseData(dataFromStorage, "time");
            LocalTime time = LocalTime.parse(timeStr);
            t = new Event(description, date, time);
        } catch (DukeException e) {
            t = new Event(description, date);
        }
        return t;
    }

    /**
     * Checks data progress to see if task is done.
     * @param progressData data about the progress of task stored in database
     * @return true if task is done and false if task is not done
     * @throws DukeException if progress data is invalid
     */
    public boolean isDone(String progressData) throws DukeException {
        if (progressData.equals("1")) {
            return true;
        } else if (progressData.equals("0")) {
            return false;
        } else {
            throw new DukeException("Data invalid");
        }
    }
}
