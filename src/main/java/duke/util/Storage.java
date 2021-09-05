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
 * Storage class deals with loading data from the file and saving tasks in the file.
 *
 * @author Chng Zi Hao
 */
public class Storage {
    private static final String DIRECTORY = "./data/";
    private static final String FILE_PATH = DIRECTORY + "data.txt";
    private final File file;
    private boolean isFirstTimeUser;

    /**
     * Constructor for Storage.
     */
    public Storage() {
        this.file = new File(FILE_PATH);
        this.isFirstTimeUser = true;
    }

    /**
     * Returns an ArrayList containing all the tasks in the data.txt file.
     * If data.txt is empty, an Empty ArrayList is returned.
     *
     * @return ArrayList containing tasks.
     * @throws DukeException If there is an I/O exception.
     */
    public ArrayList<Task> retrieveData() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();

        if (!file.exists()) {
            createFile();
            return taskList;
        }

        try {
            extractDataFromFile(taskList);
            System.out.println("YAY! File has been loaded Successfully! :>");
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeIoException();
        } catch (DukeException e) {
            throw e;
        }
    }

    private void extractDataFromFile (ArrayList<Task> taskList) throws FileNotFoundException, DukeException {
        Scanner fileReader = new Scanner(file);
        while (fileReader.hasNextLine()) {
            String data = fileReader.nextLine();

            if (data.equals("1")) {
                isFirstTimeUser = false;
                continue;
            }

            String[] dataBreakdown = data.split(" \\| ");
            Task task;

            switch (dataBreakdown[0]) {
            case "T":
                task = new ToDo(dataBreakdown[2]);
                taskList.add(task);
                break;
            case "D":
                String[] deadlineDateTime = Parser.splitDeadlineDateTime(dataBreakdown[3]);
                task = new Deadline(dataBreakdown[2], LocalDate.parse(deadlineDateTime[0]),
                        LocalTime.parse(deadlineDateTime[1]));
                taskList.add(task);
                break;
            case "E":
                String[] eventDateTime = Parser.splitEventDateTime(dataBreakdown[3]);
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
    }

    private void createFile() throws DukeIoException {
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

    /**
     * Saves data into data.txt files after changes have been made by user.
     *
     * @param data Formatted TaskList to be stored in data.txt.
     * @throws DukeIoException If program is unable to write to file.
     */
    public void save(String data) throws DukeIoException {
        assert file.exists() : "The data.txt file doesn't exist.";
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            String toBeSaved = "1\n" + data;
            fileWriter.write(toBeSaved);
            fileWriter.close();
            System.out.println("File Saved successfully!");
        } catch (IOException e) {
            throw new DukeIoException();
        }
    }

    public boolean getUserStatus() {
        return isFirstTimeUser;
    }
}
