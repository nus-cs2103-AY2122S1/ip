package duke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * Storage class deals with loading tasks from the file and saving tasks in the file.
 *
 * @author Chng Zi Hao
 */
public class Storage {
    private static final String DIRECTORY = "./data/";
    private static final String FILEPATH = DIRECTORY + "data.txt";
    private File file;

    public Storage() {
        this.file = new File(FILEPATH);
    }

    /**
     * Returns an ArrayList containing all the tasks in the data.txt file.
     * If data.txt is empty, an Empty ArrayList is returned.
     *
     * @return ArrayList containing tasks.
     * @throws DukeException If there is an I/O exception.
     */
    public ArrayList<Task> retrieveData() throws DukeException {
        if (!file.exists()) {
            try {
                System.out.println("data.txt file does not exist. Creating new file...");
                File directory = new File(DIRECTORY);
                if (!directory.exists()) {
                    directory.mkdir();
                }
                file.createNewFile();
                System.out.println("data.txt created successfully! :>");
            } catch (IOException e) {
                throw new DukeIoException();
            }
        }
        ArrayList<Task> taskList = new ArrayList<>();
        if (file.length() == 0) {
            System.out.println("No tasks to load!");
            return taskList;
        }
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                String[] dataBreakdown = data.split(" \\| ");
                Task task;
                switch (dataBreakdown[0]) {
                case "T":
                    task = new ToDo(dataBreakdown[2]);
                    taskList.add(task);
                    break;
                case "D":
                    String[] deadlineDateTime = Parser.deadlineDateTimeSplit(dataBreakdown[3]);
                    task = new Deadline(dataBreakdown[2], LocalDate.parse(deadlineDateTime[0]),
                            LocalTime.parse(deadlineDateTime[1]));
                    taskList.add(task);
                    break;
                case "E":
                    String[] eventDateTime = Parser.eventDateTimeSplit(dataBreakdown[3]);
                    task = new Event(dataBreakdown[2], LocalDate.parse(eventDateTime[0]),
                            LocalTime.parse(eventDateTime[1]), LocalTime.parse(eventDateTime[2]));
                    taskList.add(task);
                    break;
                default:
                    throw new DukeException("File has been corrupted @_@");
                }
                if (dataBreakdown[1].equals("1")) {
                    task.markDone();
                }
            }
            fileReader.close();
            System.out.println("YAY! File has been loaded Successfully! :>");
        } catch (FileNotFoundException e) {
            throw new DukeIoException();
        } catch (DukeException e) {
            throw e;
        } finally {
            return taskList;
        }

    }

    /**
     * Saves data into data.txt files after changes have been made by user.
     *
     * @param data Formatted TaskList to be stored in data.txt.
     * @throws DukeIoException If program is unable to write to file.
     */
    public void save(String data) throws DukeIoException {
        try {
            FileWriter fileWriter = new FileWriter(FILEPATH);
            fileWriter.write(data);
            fileWriter.close();
            System.out.println("File Saved successfully!");
        } catch (IOException e) {
            throw new DukeIoException();
        }
    }
}
